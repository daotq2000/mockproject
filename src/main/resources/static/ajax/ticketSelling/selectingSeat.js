let scheduleSeats = [];
var data = JSON.parse(sessionStorage.getItem('data'));
let timeMovieChoose;
var token = localStorage.getItem('Authorization');
$(document).ready(function (){


    var listSeats=[];
    var now = new Date().toISOString().split("T")[0];

    var getDates = function (startDate, endDate) {
        var dates = [],
            currentDate = startDate,
            addDays = function (days) {
                var date = new Date(this.valueOf());
                date.setDate(date.getDate() + days);
                return date;
            };
        while (currentDate <= endDate) {
            dates.push(currentDate);
            currentDate = addDays.call(currentDate, 1);
        }
        return dates;
    };

    // Usage
if(data.fromDate != null && data.toDate != null){
    var dates = getDates(new Date(data.fromDate), new Date(data.toDate));
    var contentDates = '';
    let count = 0;
    dates.forEach(value => {
        if(count == 0){
            contentDates += `<option checked value="${value.toISOString().split("T")[0]}">${value.toISOString().split("T")[0]}</option>`
        }else{
            contentDates += `<option value="${value.toISOString().split("T")[0]}">${value.toISOString().split("T")[0]}</option>`
        }
        count++;
    })

    $('#dateBooking').html(contentDates);

}
    var date = $('#dateBooking').val();
    var seatSolid = [];
    $.ajax({
        type: "GET",
        headers: {
            "Authorization":token,
        },
        url: `/api/scheduleSeat/checkBooking/${date}/${data.movieId}/${data.idSchedule}`,
        success: function (data) {
            seatSolid = data;
        },
        failure: function (errMsg) {
            alert(errMsg);
        }
    });
    $.ajax({
        type: "GET",
        headers: {
            "Authorization":token,
        },
        url: `/api/cinemaRoom/${data.roomId}`,
        success: function (data) {

            listSeats = data.seats;
            listSeats.forEach(value => {
                seatSolid.forEach(value1 => {
                    if(value.seatId == value1.seatId){
                        value.status = 0;
                    }
                })
            })
            var seats =''
            listSeats.map((value => {
                if(value.status == 0){
                    seats+=`<checkbox seatPrice="${value.seatPrice}"  seatRow="${value.seatRow}" seatColumn="${value.seatColumn}" seatId="${value.seatId}"  seatType="${value.seatType}" status="${value.status}" class="seatBtn solid">${value.seatRow}${value.seatColumn}</checkbox>`
                }else{
                    seats+=`<checkbox seatPrice="${value.seatPrice}" seatRow="${value.seatRow}" seatColumn="${value.seatColumn}" seatId="${value.seatId}" onClick="changeColor(this)" seatType="${value.seatType}" status="${value.status}" class="seatBtn choose">${value.seatRow}${value.seatColumn}</checkbox>`
                }

            }))

            $('#seatContent').html(seats);
        },
        failure: function (errMsg) {
            alert(errMsg);
        }
    });
    $('#continue').on('click',function (){
        if(scheduleSeats.length == 0){
            alert("Please choose seats")
            return;
        }
        var dataBooking = data;
        dataBooking.scheduleShow = $('#dateBooking').val();
        sessionStorage.setItem('data',JSON.stringify(dataBooking));
        $.ajax({
            type: "GET",
            headers: {
                "Authorization":token,
            },
            url: "/views/ticketSelling/confirmTicket.html",
            success: function(data){
                sessionStorage.setItem('scheduleSeats',JSON.stringify(scheduleSeats));
                $('#content').html(data);
            },
            failure: function(errMsg) {
                alert(errMsg);
            }
        });
    })
    $('#dateBooking').on('change',function (){
        scheduleSeats = [];
        date = $('#dateBooking').val();
        timeMovieChoose = date;
        var seatSolid = [];
        $.ajax({
            type: "GET",
            url: `/api/scheduleSeat/checkBooking/${date}/${data.movieId}/${data.idSchedule}`,
            success: function (data) {
                seatSolid = data;
            },
            failure: function (errMsg) {
                alert(errMsg);
            }
        });
        $.ajax({
            type: "GET",
            headers: {
                "Authorization":token,
            },
            url: `/api/cinemaRoom/${data.roomId}`,
            success: function (data) {

                listSeats = data.seats;
                listSeats.forEach(value => {
                    seatSolid.forEach(value1 => {
                        if(value.seatId == value1.seatId){
                            value.status = 0;
                        }
                    })
                })
                var seats =''
                listSeats.map((value => {
                    if(value.status == 0){
                        seats+=`<checkbox seatPrice="${value.seatPrice}"  seatRow="${value.seatRow}" seatColumn="${value.seatColumn}" seatId="${value.seatId}"  seatType="${value.seatType}" status="${value.status}" class="seatBtn solid">${value.seatRow}${value.seatColumn}</checkbox>`
                    }
                    else if(value.status == 2) {
                        seats+=`<checkbox seatPrice="${value.seatPrice}"  seatRow="${value.seatRow}" seatColumn="${value.seatColumn}" seatId="${value.seatId}"  onClick="changeColor(this)"  seatType="${value.seatType}" status="${value.status}" class="seatBtn vip">${value.seatRow}${value.seatColumn}</checkbox>`
                    }
                    else if(value.status ==1) {
                        seats+=`<checkbox seatPrice="${value.seatPrice}" seatRow="${value.seatRow}" seatColumn="${value.seatColumn}" seatId="${value.seatId}" onClick="changeColor(this)" seatType="${value.seatType}" status="${value.status}" class="seatBtn choose">${value.seatRow}${value.seatColumn}</checkbox>`
                    }

                }))
                $('#seatContent').html(seats);
            },
            failure: function (errMsg) {
                alert(errMsg);
            }
        });
    })


})
let count =0;

function changeColor(e){



        e.setAttribute('class','seatBtn selecting')
        count++;

    var scheduleSeat = {
        movie:{
            id:data.movieId
        },
        schedule:{
            id:data.idSchedule
        },
        seatId:$(e).attr('seatId'),
        seatPrice:$(e).attr('seatPrice'),
        seatRow:$(e).attr('seatRow'),
        seatColumn:$(e).attr('seatColumn'),
        seatType:$(e).attr('seatType'),
        status:$(e).attr('status'),
        dateBooking:new Date().toISOString().split("T")[0]
    };
    var idSeat = scheduleSeat.seatId;
    let isDupplicate =checkDuplicate(scheduleSeats,idSeat);

    if(!isDupplicate){
        scheduleSeats.push(scheduleSeat);
    }

}
const  checkDuplicate=(array,seatId) =>{
    let isDuplicate= false;
    for (let i = 0; i < array.length; i++) {

        if(array[i].seatId == seatId){
            isDuplicate = true;
            break;
        }
    }
    return isDuplicate;

}

