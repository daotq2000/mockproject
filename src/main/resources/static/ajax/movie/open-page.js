var token = localStorage.getItem('Authorization');
$(document).ready(function () {
    $("#movie-link").click(function () {
        $.get({
            url: "http://localhost:8080/views/movie-management/list-movie.html",
            success: function (response) {
                $("#main-content").html(response);
            },
            headers: {
                "Authorization":token,
            },
        });
    });
    $("#btnadd").click(function () {
        $.get({
            url: "http://localhost:8080/views/movie-management/movie-detail.html",
            success: function (response) {
                $("#main-content").html(response);
            },
            headers: {
                "Authorization":token,
            },
        });
    });

});