package com.levi9.internship.TennisScheduler.repository;

import com.levi9.internship.TennisScheduler.enumerations.TennisCourtType;
import com.levi9.internship.TennisScheduler.mapper.tennisCourt.CreateTennisCourtMapper;
import com.levi9.internship.TennisScheduler.model.TennisCourt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
class TennisCourtRepositoryTest {

    @Autowired
    private TennisCourtRepository underTest;

    @Autowired
    private CreateTennisCourtMapper createTennisCourtMapper;


    @Test
    void checkGetTennisCourtByName() {

        String name = "ATP ROME";
        // given
//        TennisCourt t = new TennisCourt(
//                name,
//                TennisCourtType.CARPET_WITH_ROOF,
//                0.8,
//                false);
        //underTest.save(t);
        // when
        TennisCourt expected = underTest.getTennisCourtByName(name);
        // then
        assertThat(expected);
    }

    @Test
    void badNameForGetTennisCourtByName() throws Exception{
        String name = "Asdf22#$";
        //TennisCourt expected = underTest.getTennisCourtByName(name);
        assertThatThrownBy(() -> underTest.getTennisCourtByName(name))
        .isInstanceOf(Exception.class)
        .hasMessage("Name "+ underTest.getTennisCourtByName(name)+" does not exist");
    }

    @Test
    void checkGetTennisCourtById(){
        Long id = Long.valueOf(5);

        TennisCourt expected = underTest.getById(id);

        assertThat(expected).isNotNull();
    }

    @Test
    void badIdForGetTennisCourtById(){
        Long id = Long.valueOf(999);
        TennisCourt expected = underTest.getById(id);
        assertThat(expected).isNull();
    }

    @Test
    void checkGetAllTennisCourts(){
        List<TennisCourt> courts = underTest.getAllCourts();

        assertThat(courts).isNotNull();
    }
}
