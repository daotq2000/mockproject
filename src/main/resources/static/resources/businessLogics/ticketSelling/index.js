$(document).ready(function (){
  $('.timeBtn').on('click',function (){

      let  scheduleMovie = {
          idMovie: 1,
          idSchedule:2
      }
      sessionStorage.setItem("scheduleMovie",scheduleMovie);
      $.ajax({
          type: "GET",
          url: "/views/ticketSelling/dSeat.html",
          success: function(data) {
            $('#main-content').html(data);
          },
          failure: function(errMsg) {
              alert(errMsg);
          }
      });
  })
})