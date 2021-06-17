package com.levi9.internship.TennisScheduler.validation;

import com.levi9.internship.TennisScheduler.exceptions.TennisException;
import com.levi9.internship.TennisScheduler.modelDTO.timeSlot.CreateTimeSlotDTO;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.DayOfWeek;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CreateTimeSlotDTOValidator implements ConstraintValidator<ValidCreateTimeSlotDTO, Set<CreateTimeSlotDTO>> {

    @Override
    public boolean isValid(Set<CreateTimeSlotDTO> timeSlots, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        if (timeSlots.isEmpty()) {
            customMessageForValidation(context, "List of Time Slots is empty!");
            return false;
        } else {
            List<CreateTimeSlotDTO> slots = new ArrayList<>(timeSlots);
            if(Boolean.TRUE.equals(isOverlapping(slots))){
                customMessageForValidation(context, "Time slots cannot overlap with another!");
                return false;
            }
            for (CreateTimeSlotDTO timeSlot : timeSlots) {
                if (timeSlot.getStartDateAndTime() == null)
                    throw new TennisException(HttpStatus.BAD_REQUEST, "Start date and time cannot be null!");
                if (timeSlot.getEndDateAndTime() == null)
                    throw new TennisException(HttpStatus.BAD_REQUEST, "End date and time cannot be null!");
                if (Boolean.TRUE.equals(isStartBeforeEndDate(timeSlot))) {
                    if (Boolean.TRUE.equals(isDurationOfTimeSlotValid(timeSlot))) {
                        if (Boolean.TRUE.equals(isWorkingDay(timeSlot))) {
                            if (Boolean.FALSE.equals(isWorkTimeValid(timeSlot, 18,23 ))) {
                                customMessageForValidation(context, "On working days, you can register the slot from 18h to 23h!");
                                return false;
                            }
                        } else {
                            if (Boolean.FALSE.equals(isWorkTimeValid(timeSlot, 17, 22))) {
                                customMessageForValidation(context, "Working time on weekends is from 17h to 22h!");
                                return false;
                            }
                        }
                    } else {
                        customMessageForValidation(context, "Period cannot be longer than 2 hours. Period cannot be less than 30 minutes!");
                        return false;
                    }
                } else {
                    customMessageForValidation(context, "Start of the time slot must be before end of the time slot!");
                    return false;
                }
            }
            return true;
        }
    }

    private void customMessageForValidation(ConstraintValidatorContext context, String message) {
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }

    private Boolean isOverlapping(List<CreateTimeSlotDTO> slots) {
        List<LocalDate> listOfDates = new ArrayList<>();
        for(CreateTimeSlotDTO slot : slots) {
            if (slot.getStartDateAndTime() == null)
                throw new TennisException(HttpStatus.BAD_REQUEST, "Start date and time cannot be null!");
            if (slot.getEndDateAndTime() == null)
                throw new TennisException(HttpStatus.BAD_REQUEST, "End date and time cannot be null!");
            listOfDates.add(slot.getStartDateAndTime().toLocalDate());
        }
        Set<LocalDate> setOfDates = new HashSet<>(listOfDates);
        return setOfDates.size() < listOfDates.size();
        }

    private Boolean isStartBeforeEndDate(CreateTimeSlotDTO timeSlot) {
        return timeSlot.getStartDateAndTime().isBefore(timeSlot.getEndDateAndTime());
    }

    private Boolean isDurationOfTimeSlotValid(CreateTimeSlotDTO timeSlot) {
        int minutesToPlay = (timeSlot.getEndDateAndTime().getHour() * 60 + timeSlot.getEndDateAndTime().getMinute()) - (timeSlot.getStartDateAndTime().getHour() * 60 + timeSlot.getStartDateAndTime().getMinute());
        return minutesToPlay >= 30 && minutesToPlay <= 120;
    }

    private Boolean isWorkingDay(CreateTimeSlotDTO timeSlot) {
        return (timeSlot.getStartDateAndTime().getDayOfWeek() != DayOfWeek.SATURDAY) && (timeSlot.getStartDateAndTime().getDayOfWeek() != DayOfWeek.SUNDAY);
    }

    private Boolean isWorkTimeValid(CreateTimeSlotDTO timeSlot, int start, int end) {
        return timeSlot.getStartDateAndTime().getHour() >= start && timeSlot.getEndDateAndTime().getHour() <= end;
    }

}
