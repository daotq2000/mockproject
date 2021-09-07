var account = null;
var movie = {};
var token = localStorage.getItem('Authorization');
var seats = ''
$(document).ready(function () {

    var data = JSON.parse(sessionStorage.getItem('data'));
    var dateSelect = data.scheduleShow;
    var scheduleSeats = JSON.parse(sessionStorage.getItem('scheduleSeats'));
    var time = data.time;
    var total = 0;
    var dateBooking = new Date().toISOString().split("T")[0];
    $.ajax({
        type: "GET",
        headers: {
            "Authorization":token,
        },
        url: `/api/movies/${data.movieId}`,
        success: function (data) {
            movie = data;

            let content = `
              <div class="amy-movie-item-front">
                                    <div class="amy-movie-item-poster">
                                        <a href="amy_tvshow/the-arrows/index.html">
                                            <img width="196" height="336" src="${movie.image}"
                                                 class="attachment-196x336 size-196x336" alt=""
                                                 srcset="${movie.image}"
                                                 sizes="(max-width: 196px) 100vw, 196px">
                                        </a>

                                    </div>
                                </div>
                                <div class="amy-movie-item-back" id="amy-movie-item-22252">
                                    <div class="amy-movie-item-back-inner">
                                        <div class="amy-movie-item-content">
                                            <h3><a class="title-film" href="amy_tvshow/the-arrows/index.html">
                                            ${movie.nameEng}: ${movie.nameVN}
</a></h3>
                                            <hr>
                                            <div class="row">
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-text">Screen: </label>
                                                </div>
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-info">Scm02</label>
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="row">
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-text">Date: </label>
                                                </div>
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-info">${dateSelect}</label>
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="row">
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-text">Time: </label>
                                                </div>
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-info">${time}</label>
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="row">
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-text">Seat: </label>
                                                </div>
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                <label class="label-info">
                                                ${scheduleSeats.map((value) => {
                return `${value.seatRow}${value.seatColumn} `
            })}
                                               </label>     
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="row">
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-text">Price: </label>
                                                </div>
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                ${scheduleSeats.map((value) => {
                seats += `${value.seatRow}${value.seatColumn} `
                total += parseInt(value.seatPrice)
                return `<p class="label-info">${value.seatRow}${value.seatColumn}: <b class="price_ticket">${value.seatPrice}đ</b></p>`
            })}
                                                   
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="row">
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-text">Score for ticket converting: </label>
                                                </div>
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-info">0</label>
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="row">
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-text">Total: </label>
                                                </div>
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-total">${total}đ</label>
                                                </div>
                                            </div>
                                            <br>
                                            <h3><a class="title-film" href="amy_tvshow/the-arrows/index.html">Member</a>
                                            </h3>
                                            <div class="row">

                                                <div class="col-md-6 col-lg-6 col-xs-12">
                                                    <div class="form-group">

                                                        <input  type="text"
                                                               class="form-control"
                                                               placeholder="Member ID or Identity Card" name="" id="memberId"
                                                               aria-describedby="helpId" placeholder="">

                                                    </div>
                                                </div>
                                                <div onclick="checkingMember()" id="checkButton" class="col-md-6 col-lg-6 col-xs-12">
                                                    <button style="height: 39px;font-size: medium;"
                                                            class="btn btn-round btn-fill btn-info">
                                                        Check
                                                    </button>
                                                </div>
                                               
                                            </div>
                           
                                        </div>
                                        <div id="member-content" class="amy-movie-item-content">
                                           

                                        </div>
                                    </div>
                                </div>
            
            `
            $('#main-content').html(content);
        },
        failure: function (errMsg) {
            alert(errMsg);
        }
    });
    $('#confirmTicket').on('click', function () {
        var scheduleSeats = JSON.parse(sessionStorage.getItem('scheduleSeats'));
        var data = JSON.parse(sessionStorage.getItem('data'));
        var scheduleseatsDTO = [];
        scheduleSeats.forEach(value => {
            var scheduleSeatEntity = {
                movie: value.movie,
                schedule: value.schedule,
                seatColumn: value.seatColumn,
                seatId: value.seatId,
                seatPrice: value.seatPrice,
                seatRow: value.seatRow,
                seatType: value.seatType,
                status: value.status
            }
            scheduleseatsDTO.push(scheduleSeatEntity);
        })
        var invoiceDTO = {
            account: account,
            addScore: 0,
            bookingDate: null,
            movieName: movie.nameVN,
            status: 1,
            totalMoney: total,
            userScore: 0,
            scheduleSeats: scheduleseatsDTO,
            scheduleShow: data.scheduleShow,
            scheduleShowTime: data.time,
            userScore: 0,
            seat: seats
        }
        if(account != null){
            invoiceDTO.customerName= account.fullName;
            invoiceDTO.customerPhone= account.phoneNumber;
            invoiceDTO.customerIdentityCard= account.identityCard;
        }

        console.log(invoiceDTO)
        console.log(JSON.stringify(invoiceDTO))

        $.ajax({
            type: "POST",
            url: "/api/invoice/",
            headers: {
                "Authorization":token,
            },
            data: JSON.stringify(invoiceDTO),
            contentType: "application/json",
            dataType: "json",
            success: function (data) {
                sessionStorage.setItem('idInvoice', data.id);
                var content = `
    <link href="../../resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../../resources/css/sb-admin-2.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../../resources/css/ticketInfomation.css">
    <link rel="stylesheet" href="../../resources/css/style8a54.css">
         <style>
         body {
        background: #eff0f3;
         }
        </style>
              <div class="main-page">
        <div class="container">
            <h3 class="text-center">TICKET INFORMATION</h3>
            <div class="main-info">
                <div class="amy-movie-grid amy-movie-grid-2 ">
                    <div class="amy-movie-list">
                        <div class="amy-movie-items row" data-column="2">
                            <div class="amy-movie-item col-md-12  col-sm-6 col-xs-12">
                                <div class="amy-movie-item-front">
                                    <div class="amy-movie-item-poster">
                                        <a href="amy_tvshow/the-arrows/index.html">
                                            <img width="196" height="336" src="${movie.image}"
                                                class="attachment-196x336 size-196x336" alt=""
                                                srcset="${movie.image}"
                                                sizes="(max-width: 196px) 100vw, 196px">
                                        </a>

                                    </div>
                                </div>
                                <div class="amy-movie-item-back" id="amy-movie-item-22252">
                                    <div class="amy-movie-item-back-inner">
                                        <div class="amy-movie-item-content">
                                            <h3><a class="title-film" href="amy_tvshow/the-arrows/index.html">${data.movieName}</a></h3>
                                            <hr>
                                            <div class="row">
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-text">Screen: </label>
                                                </div>
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-info">Scm02</label>
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="row">
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-text">Date: </label>
                                                </div>
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-info">${data.scheduleShow}</label>
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="row">
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-text">Time: </label>
                                                </div>
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-info">22:00</label>
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="row">
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-text">Seat: </label>
                                                </div>
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-info">${data.seat}</label>
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="row">
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-text">Price: </label>
                                                </div>
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                     ${scheduleSeats.map((value) => {
                    seats += `${value.seatRow}${value.seatColumn} `
                    total += parseInt(value.seatPrice)
                    return `<p class="label-info">${value.seatRow}${value.seatColumn}: <b class="price_ticket">${value.seatPrice}đ</b></p>`
                })}
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="row">
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-text">Score for ticket converting: </label>
                                                </div>
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-info">0</label>
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="row">
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-text">Total: </label>
                                                </div>
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-total">${data.totalMoney}đ</label>
                                                </div>
                                            </div>
                                            <br>
                                            <h3><a style="${data.account != null ? "display:''" : "display:none"}" class="title-film" href="amy_tvshow/the-arrows/index.html">Member</a>
                                            </h3>
                                            <hr>
                                            <div style="${data.account != null ? "display:''" : "display:none"}" class="row">
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-text">Member ID:  </label>
                                                </div>
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-info">${data.account != null ? data.account.id : ''}</label>
                                                </div>
                                            </div>
                                            <hr>
                                            <div style="${data.account != null ? "display:''" : "display:none"}" class="row">
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-text">Identity Card: </label>
                                                </div>
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-info">${data.account != null ? data.account.identityCard : ''}</label>
                                                </div>
                                            </div>
                                            <hr>
                                            <div style="${data.account != null ? "display:''" : "display:none"}" class="row">
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-text">Full Name: </label>
                                                </div>
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-info">${data.account != null ? data.account.fullName : ''}</label>
                                                </div>
                                            </div>
                                            <hr>
                                            <div style="${data.account != null ? "display:''" : "display:none"}" class="row">
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-text">Phone Number: </label>
                                                </div>
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-info">${data.account != null ? data.account.phoneNumber : ''}</label>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
             `
                $('#content').html(content);
            },
            failure: function (errMsg) {
                alert(errMsg);
            }
        })
    })

})

function checkingMember() {
    let memberId = $('#memberId');
    $.ajax({
        type: "GET",
        url: `/api/member/${memberId.val()}`,
        headers: {
            "Authorization":token,
        },
        success: function (data) {
            account = data.account;
            var content =
                `
                                         <div class="row">
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-text">MemberId: </label>
                                                </div>
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-info">${data.memberId}</label>
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="row">
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-text">Identity Card: </label>
                                                </div>
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-info">${data.account.identityCard}</label>
                                                </div>
                                            </div>
                                             <hr>
                                            <div class="row">
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-text">FullName: </label>
                                                </div>
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-info">${data.account.fullName}</label>
                                                </div>
                                            </div>
                                             <hr>
                                            <div class="row">
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-text">Phone number: </label>
                                                </div>
                                                <div class="col-xs-12 col-md-6 col-lg-6">
                                                    <label class="label-info">${data.account.phoneNumber}</label>
                                                </div>
                                            </div>
            `
            $('#member-content').html(content);
        },
        failure: function (errMsg) {
            alert(errMsg);
        }
    });
}