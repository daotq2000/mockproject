var token = localStorage.getItem('Authorization');
$(document).ready(function () {
    $('#search').attr('placeholder','Enter title then hit [Enter]...')
    listMovies()
});
function listMovies() {
    // if ($('tr').length==0){
    //     $('table').DataTable();
    // }
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/movies",
        dataType: "json",
        headers: {
            "Authorization":token,
        },
        success: function (result) {
            $("tbody tr").remove();
            $.each(result, function(i, item) {
                $('tbody').append(
                    `
                                <tr>
                                    <td>${item.id}</td>
                                    <td>${item.nameEng}</td>
                                    <td>${item.nameVN}</td>
                                    <td>${item.fromDate}</td>
                                    <td>${item.movieCompany}</td>
                                    <td>${item.duration}</td>
                                    <td>${item.version}</td>
                                    <td><button  class="btn btn-fill btn-info btn-sm" onclick="showMovie(${item.id})"><i class="fa fa-info-circle"></i> Detail</button></td>
                                    <td><button onclick="deleteMovie(${item.id})"  class="btn btn-fill btn-danger btn-sm">
                                    <span class="material-icons">  
                                                    delete  
                                                  </span>
                                    </button></td>
                                </tr>
                            `
                );
            });
            $('table').DataTable( {
                retrieve: true,
                "lengthMenu": [ 5, 10, 15]
            } );
        },
        error: function (xhr, status, error) {
            alert("an error has occur")
        }
    });
}
function deleteMovie(movieId) {
    var confirmation = confirm("are you sure you want to delete this record?");
    if (confirmation) {
        var settings = {
            "url": `http://localhost:8080/api/movies/${movieId}`,
            "method": "DELETE",
            "timeout": 0,
        };
        $.ajax(settings).done(function (response) {
            console.log("delete successfully!");
            $.get({
                headers: {
                    "Authorization":token,
                },
                url: "http://localhost:8080/views/movie-management/list-movie.html",
                success: function (response) {
                    $("#main-content").html(response);
                },
            });
        })
    }
}
function showMovie(movieId) {
    $("#main-content").load("/views/movie-management/movie-detail.html")
    var settings = {
        "url": `http://localhost:8080/api/movies/${movieId}`,
        "method": "GET",
        "timeout": 0,
        "headers": {
            "Authorization":token,
        },
    };
    $.ajax(settings).done(function (response) {
        console.log(response)
        for (const [key, value] of Object.entries(response)) {
            // console.log(`${key}: ${value}`);
            $(`input[name=${key}]`).val(`${value}`);
        }
        $('textarea').val(response.content)
        $(`#hiddenCinemaRoom`).val(response.cinemaRoom.roomName);
        $(`#hiddenScheduleMovies`).val(response.scheduleMovies.map(function (el) { return el.schedule; }).map(function (el) { return el.time; }));
        $(`#hiddenTypeMovies`).val(response.typeMovies.map(function (el) { return el.type; }).map(function (el) { return el.name; }));
        console.log(response);
        $("#img").attr("src", response.image)
    });
}