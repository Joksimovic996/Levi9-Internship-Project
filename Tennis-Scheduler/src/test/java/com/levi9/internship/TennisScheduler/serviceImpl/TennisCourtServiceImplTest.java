package com.levi9.internship.TennisScheduler.serviceImpl;

import com.levi9.internship.TennisScheduler.enumerations.TennisCourtType;
import com.levi9.internship.TennisScheduler.mapper.tennisCourt.CreateTennisCourtMapper;
import com.levi9.internship.TennisScheduler.mapper.tennisCourt.TennisCourtMapper;
import com.levi9.internship.TennisScheduler.mapper.tennisCourt.UpdateTennisCourtMapper;
import com.levi9.internship.TennisScheduler.mapper.timeSlot.TimeSlotMapper;
import com.levi9.internship.TennisScheduler.model.TennisCourt;
import com.levi9.internship.TennisScheduler.modelDTO.tennisCourt.CreateTennisCourtDTO;
import com.levi9.internship.TennisScheduler.modelDTO.tennisCourt.TennisCourtDTO;
import com.levi9.internship.TennisScheduler.repository.TennisCourtRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class TennisCourtServiceImplTest {

    @MockBean
    private TennisCourtRepository tennisCourtRepository;
    @MockBean
    private TennisCourtMapper tennisCourtMapper;
    @MockBean
    private CreateTennisCourtMapper createTennisCourtMapper;
    @MockBean
    private UpdateTennisCourtMapper updateTennisCourtMapper;
    @MockBean
    private TimeSlotMapper timeSlotMapper;


    @Autowired
    private TennisCourtServiceImpl underTest;

    @BeforeEach
    void setUp(){
        underTest = new TennisCourtServiceImpl(tennisCourtRepository,
                                                tennisCourtMapper,
                                                createTennisCourtMapper,
                                                updateTennisCourtMapper,
                                                timeSlotMapper);
    }

    @Test
    void canGetAllCourts() {
        when(tennisCourtRepository.getAllCourts()).thenReturn(Stream.of(
                new TennisCourt("Australian Open",TennisCourtType.CARPET_WITH_ROOF, 0.7, false)
        ).collect(Collectors.toList()));
//        underTest.getAllCourts();
//        verify(tennisCourtRepository).getAllCourts();
        assertEquals(1, underTest.getAllCourts().size());

    }

    @Test
    void canAddTennisCourt(){

        String name = "Australian Open";
        CreateTennisCourtDTO t = new CreateTennisCourtDTO(
                name,
                TennisCourtType.CARPET_WITH_ROOF.toString(),
                0.8);

        TennisCourt court = createTennisCourtMapper.map(t);
        underTest.addTennisCourt(t);

        //ArgumentCaptor<CreateTennisCourtDTO> courtArgumentCaptor = ArgumentCaptor.forClass(CreateTennisCourtDTO.class);
        ArgumentCaptor<TennisCourt> courtArgumentCaptor = ArgumentCaptor.forClass(TennisCourt.class);

        //TennisCourt tc = createTennisCourtMapper.map(courtArgumentCaptor.capture());

        verify(tennisCourtRepository).save(courtArgumentCaptor.capture());

        TennisCourt capturedCourt = courtArgumentCaptor.getValue();

        assertThat(capturedCourt).isEqualTo(court);
    }
}
