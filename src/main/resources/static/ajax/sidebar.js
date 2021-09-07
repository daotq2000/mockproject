var token = localStorage.getItem('Authorization');
$(document).ready(function (){
    $('#ticketSelling').on('click',function (){

        $.ajax({
            type: "GET",
            headers: {
                "Authorization": token,
            },
            url: "http://localhost:8080/views/ticketSelling/index.html",
            success: function (data) {
                console.log(data);
                $('#body-content').html(data);
            },
            error: function (errMsg) {
                alert(errMsg);
            }
        });
    })
    $('#logOutBtn').on('click',function (){
        console.log("clear")
        localStorage.removeItem("Authorization");
        window.location.href = "http://localhost:8080/";

    })
    $("#link_promotion").click(function (){
        // $('#link_promotion').parent().toggleClass('active');


        // $(".card-title").text("Promotion list")
        // $(".card-body").load("../views/promotion/promotion-list.html")

        $.ajax({
            type: "GET",
            headers: {
                "Authorization":token,
            },
            url: `http://localhost:8080/views/promotion/promotion-list.html`,
            success: function (data) {
                $('#main-content').html(data);
            },
            failure: function (errMsg) {
                alert(errMsg);
            }
        });

    })
    $("#movie-link").on('click',function () {
        console.log('movie  Click')
        $.get({
            url: "http://localhost:8080/views/movie-management/list-movie.html",
            success: function (response) {
                $("#main-content").html(response);
            },
        });
    });

    $("#link_account_management").on('click',function () {
        console.log('movie  Click')
        $.get({
            url: "http://localhost:8080/views/movie-management/list-movie.html",
            success: function (response) {
                $("#main-content").html(response);
            },
        });
    });
})