package com.levi9.internship.TennisScheduler.serviceImpl;

import com.levi9.internship.TennisScheduler.enumerations.TennisCourtType;
import com.levi9.internship.TennisScheduler.mapper.tennisCourt.CreateTennisCourtMapper;
import com.levi9.internship.TennisScheduler.mapper.tennisCourt.TennisCourtMapper;
import com.levi9.internship.TennisScheduler.mapper.tennisCourt.UpdateTennisCourtMapper;
import com.levi9.internship.TennisScheduler.mapper.timeSlot.TimeSlotMapper;
import com.levi9.internship.TennisScheduler.model.TennisCourt;
import com.levi9.internship.TennisScheduler.modelDTO.tennisCourt.CreateTennisCourtDTO;
import com.levi9.internship.TennisScheduler.repository.TennisCourtRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class TennisCourtServiceImplTest {

    @Mock
    private TennisCourtRepository tennisCourtRepository;
    @Mock
    private TennisCourtMapper tennisCourtMapper;
    @Mock
    private CreateTennisCourtMapper createTennisCourtMapper;
    @Mock
    private UpdateTennisCourtMapper updateTennisCourtMapper;
    @Mock
    private TimeSlotMapper timeSlotMapper;

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
        underTest.getAllCourts();
        verify(tennisCourtRepository).getAllCourts();
    }

    @Test
    void canAddTennisCourt(){

        String name = "Australian Open";
        CreateTennisCourtDTO t = new CreateTennisCourtDTO(
                name,
                TennisCourtType.CARPET_WITH_ROOF.toString(),
                0.8);

        underTest.addTennisCourt(t);

        ArgumentCaptor<CreateTennisCourtDTO> courtArgumentCaptor = ArgumentCaptor.forClass(CreateTennisCourtDTO.class);

        TennisCourt tc = createTennisCourtMapper.map(courtArgumentCaptor.capture());

        verify(tennisCourtRepository).save(tc);

        CreateTennisCourtDTO capturedCourt = courtArgumentCaptor.getValue();

        assertThat(capturedCourt).isEqualTo(t);
    }
}
