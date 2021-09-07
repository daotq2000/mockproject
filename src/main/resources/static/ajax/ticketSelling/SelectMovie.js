var token = localStorage.getItem('Authorization');
var pageNumber = 1;
var sizeNumber = 6;
var daySearch = "SAR";
var totalPage = 0;
var totalElement= 0;
$(document).ready(function () {
    let page = 1;
    let size = 10;
    let order = "";
    let field = "";
    let search = "";
    daySearch = "SAT";
    renderMovie(pageNumber,sizeNumber,daySearch);

    $('.circle').on('click', function () {
        var listItem = $('.circle');
        listItem.removeClass('activeBtn');
        $(this).addClass('activeBtn');
        daySearch = $(this).attr('data');

        console.log($(this).attr('data'))
        renderMovie(pageNumber,sizeNumber,daySearch);
    })
    renderMovie(pageNumber,sizeNumber,daySearch);


    $(document).on('keypress', function (e) {
        if (e.which == 13) {
            search = $('#inputSearch').val();
            console.log(search)
            $.ajax({
                type: "GET",
                headers: {
                    "Authorization": token,
                },
                url: `http://localhost:8080/api/movie?search=${search}`,
                success: function (data) {
                    console.log(data)
                    $('#showMovie').html(renderFilm(data));
                },
                failure: function (errMsg) {
                    alert(errMsg);
                }
            });
        }
    });
    $('#inputSearch').on('change',function (){
        console.log($('#amy-loading'))
        $('#amy-loading').remove();
    })

})
function paging(e){
    console.log($(e).attr('data'))
    pageNumber = $(e).attr('data');
    renderMovie(pageNumber,sizeNumber,daySearch)
}

function renderSeat(e) {

    var idSchedule = $(e).attr('idSchedule');
    ;
    var idMovie = $(e).attr('idMovie');
    var idRoom = $(e).attr('idRoom');
    var timeSchedule = $(e).attr('scheduleTime');
    var fromDate = $(e).attr('fromDate');
    var toDate = $(e).attr('toDate');
    var listSeat = [];
    var movie = {};


    var datas = {
        movieId: idMovie,
        roomId: idRoom,
        idSchedule: idSchedule,
        time: timeSchedule,
        fromDate: fromDate,
        toDate: toDate
    }

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/views/ticketSelling/dSeat.html",
        headers: {
            "Authorization": token,
        },
        success: function (data) {
            sessionStorage.setItem('data', JSON.stringify(datas));
            $('#content').html(data);
        },
        failure: function (errMsg) {
            alert(errMsg);
        }
    });


}

const renderFilm = (data) => {

    return (
        data.map((val) => {
            let content = '';
            val.scheduleMovies.map((value) => {
                content += `<button onclick="renderSeat(this)" fromDate="${val.fromDate}" toDate="${val.toDate}" idRoom="${val.cinemaRoom.id}" idMovie="${val.id}" idSchedule="${value.schedule.id}" scheduleTime="${value.schedule.time}" class="timeBtn"><b class="timeText">${value.schedule.time}</b></button>`
            })

            return (
                `
        <div class="amy-movie-item col-md-6  col-sm-6 col-xs-12">
                                                            <div class="amy-movie-item-front">
                                                                <div class="amy-movie-item-poster">
                                                                    <a href="amy_tvshow/the-arrows/index.html">
                                                                        <img width="196" height="336" src=${val.image == null?'https://media1.tenor.com/images/556e9ff845b7dd0c62dcdbbb00babb4b/tenor.gif?itemid=5300368':val.image == ''?'https://media1.tenor.com/images/556e9ff845b7dd0c62dcdbbb00babb4b/tenor.gif?itemid=5300368':val.image} class="attachment-196x336 size-196x336" alt="" srcset=${val.avatar == null?'https://media1.tenor.com/images/556e9ff845b7dd0c62dcdbbb00babb4b/tenor.gif?itemid=5300368':val.avatar == ''?'https://media1.tenor.com/images/556e9ff845b7dd0c62dcdbbb00babb4b/tenor.gif?itemid=5300368':val.avatar} sizes="(max-width: 196px) 100vw, 196px">
                                                                    </a>
                                                                    <span class="amy-movie-field-imdb">8.6</span>
                                                                </div>
                                                            </div>
                                                            <div class="amy-movie-item-back" id="amy-movie-item-22252">
                                                                <div class="amy-movie-item-back-inner">
                                                                    <div class="amy-movie-item-content">
                                                                        <h3 class="amy-movie-field-title"><a href="">${val.nameVN}</a></h3>
                                                                        <div class="amy-movie-field-rating">
                                                                            <div class="amy-movie-field-rating-inner">
\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span class="rating-stars"><span class="current-rating" style="width: 88%"></span></span>
                                                                                <span class="user-rating"><a href="#">(22
\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tVotes)</a></span>
                                                                            </div>
                                                                        </div>
                                                                        <div class="amy-movie-item-meta">
                                                                            ${val.nameEng}
                                                                            <span class="amy-movie-field-duration"><i class="fa fa-clock-o"></i>${val.duration} minutes</span>
                                                                        </div>
                                                                         
                                                                        <div class="amy-movie-custom-field-group amy-movie-field-release_date">
                                                                            <label class="amy-movie-custom-field-label">Release
                                                                                Date:</label>
                                                                            <div class="amy-movie-custom-field-content">
                                                                                ${val.fromDate}
                                                                            </div>
                                                                        </div>
                                                                      
                                                                        <div class="amy-movie-custom-field-group amy-movie-field-amy_actor">
                                                                            <label class="amy-movie-custom-field-label">Actor:</label>
                                                                            <div class="amy-movie-custom-field-content">
                                                                                <a href="">${val.actor}</a>
                                                                            </div>
                                                                        </div>
                                                                        <div class="amy-movie-custom-field-group amy-movie-field-amy_director">
                                                                            <label class="amy-movie-custom-field-label">Movie Company:</label>
                                                                            <div class="amy-movie-custom-field-content">
                                                                                <a href="">${val.movieCompany}</a>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                     <div  id="renderTime" class="amy-movie-item-content">
                                                                        ${content}
                                                                      </div>
                                     
                                                            </div>
                                                        </div>
        `
            );
        })

    )

}
const renderMovie =(page,size,search)=>{
   $.ajax({
        type: "GET",
        headers: {
            "Authorization": token,
        },
        url: `http://localhost:8080/api/movies/showDate?page=${page}&size=${size}&search=${search}`,
        success: function (data) {
            console.log(page,size,search)
            var movies = data.dateMovies;
            totalPage = data.totalPages;
            currentPage = data.currentPage;
            totalElement= data.totalElements;
            console.log(totalPage,currentPage,totalElement)
            var moviesList = [];
            movies.map(val =>{
                moviesList.push(val.movie);
            })
            $('#showMovie').html(renderFilm(moviesList));
            var totalPageContent = ``;
            for (let i = 1; i <=totalPage ; i++) {
                if(i == currentPage){
                    totalPageContent += `<span onclick="paging(this)" data="${currentPage}" class="page-numbers current">${currentPage}</span>`
                }else{
                    totalPageContent += `<a  onclick="paging(this)" data="${i}"  class="page-numbers">${i}</a>`
                }

            }
            var contentPagination = `
               <a onclick="paging(this)" id="preBtn" data="${currentPage>1?currentPage-1:1}" class="prev page-numbers" >Previous</a>
               ${totalPageContent}
               <a id="nextBtn" onclick="paging(this)" data="${currentPage+1}" class="next page-numbers">Next</a>
        `;
            $('#PaginationMovie').html(contentPagination);
        },
        failure: function (errMsg) {
            alert(errMsg);
        }

    });
}