package fa.appcode.web;

import fa.appcode.web.entities.ScheduleSeat;
import fa.appcode.web.repository.ScheduleSeatRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;

import java.util.List;

@TestComponent
class MainApplicationTests {


    @Autowired
    ScheduleSeatRepository scheduleSeatRepository;
    @Test
    public void Test01(){
        List<ScheduleSeat> list = scheduleSeatRepository.getByMovieIdAndScheduleId(1,1);
        System.out.println(list.size());
    }
}
