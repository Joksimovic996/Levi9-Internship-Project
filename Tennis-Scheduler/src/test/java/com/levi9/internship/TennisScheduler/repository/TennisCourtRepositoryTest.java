package com.levi9.internship.TennisScheduler.repository;

import com.levi9.internship.TennisScheduler.mapper.tennisCourt.CreateTennisCourtMapper;
import com.levi9.internship.TennisScheduler.model.TennisCourt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TennisCourtRepositoryTest {

    @Autowired
    private TennisCourtRepository underTest;

    @Autowired
    private CreateTennisCourtMapper createTennisCourtMapper;


    @Test
    void checkGetTennisCourtByName() {

        String name = "Rod Laver Arena";
        TennisCourt expected = underTest.getTennisCourtByName(name);
        System.out.println(expected.getName());
        assertThat(expected).isNotNull();
        //assertEquals(expected, underTest.getTennisCourtByName(name));
    }

    @Test
    void badNameForGetTennisCourtByName() throws Exception{
        String name = "Asdf22#$";
        //String value = underTest.getTennisCourtByName(name).getName();
        //assertEquals(name, value);
        TennisCourt expected = underTest.getTennisCourtByName(name);
        assertThat(expected).isNull();
    }

    @Test
    @Transactional
    void checkGetTennisCourtById(){
        Long id = Long.valueOf(2);

        //Long value = underTest.getById(id).getId();
        //assertEquals(2, value);
        TennisCourt expected = underTest.getTennisCourtById(id);
        System.out.println(underTest.getTennisCourtById(id).getName());
        assertThat(expected).isNotNull();
    }

    @Test
    @Transactional
    void badIdForGetTennisCourtById(){
        Long id = Long.valueOf(999);
        //Long expected = underTest.getById(id).getId();
        assertEquals(999, underTest.getTennisCourtById(id).getId());
    }

    @Test
    void checkGetAllTennisCourts(){
        List<TennisCourt> courts = underTest.getAllCourts();

        for(TennisCourt court : courts){
            System.out.println(court.getName());
        }
        assertThat(courts).isNotNull();
    }
}
