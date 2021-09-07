package fa.appcode.web.dto;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        int seatQuantity = 9;
        int movieId = 1;
        int scheduleId = 1;
        int count= 0;
        List<ScheduleSeatDTO> list = new ArrayList<>();
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movieId);
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(scheduleId);
        char c;
        for (int i = 1; i <=seatQuantity ; i++) {

            for(c = 'A'; c <= 'F'; ++c){
                count++;
                System.out.println("Seat" + "["+i+c+"]");
                ScheduleSeatDTO scheduleSeatDTO = new ScheduleSeatDTO();
                scheduleSeatDTO.setMovie(movieDTO);
                scheduleSeatDTO.setSchedule(scheduleDTO);
                scheduleSeatDTO.setSeatId(count);
                scheduleSeatDTO.setSeatColumn(c+"");
                scheduleSeatDTO.setSeatRow(i);
                scheduleSeatDTO.setSeatType(1);
                list.add(scheduleSeatDTO);
            }
        }
        list.forEach(scheduleSeatDTO -> {
            System.out.println(scheduleSeatDTO.toString());
        });
        System.out.println(list.size());


    }
}
