$(document).ready(function () {
    let movies = [];
    let totalpages = 1;
    let totalElements = 0;
    let currentPage = 1;

    $.ajax({
        type: "GET",
        url: "/api/movie",
        success: function (data) {
            console.log(data)
            movies = data.movies;
            totalpages = data.totalPages;
            totalElements = data.totalElement;
            currentPage = data.currentPage;
            $('#list-movie').html(
                movies.map((movie => {
                    return [
                        `  <div class="amy-movie-grid amy-movie-grid-2 ">
                <div class="amy-movie-list">
                    <div class="amy-movie-items row" data-column="2">
                        <div class="amy-movie-item col-md-12  col-sm-6 col-xs-12">
                            <div class="amy-movie-item-front">
                                <div class="amy-movie-item-poster">
                                    <a href="amy_tvshow/the-arrows/index.html">
                                        <img width="196" height="336" src="${movie.avatar}"
                                             class="attachment-196x336 size-196x336" alt=""
                                             srcset="${movie.avatar}"
                                             sizes="(max-width: 196px) 100vw, 196px">
                                    </a>

                                </div>
                            </div>
                            <div class="amy-movie-item-back" id="amy-movie-item-22252">
                                <div class="amy-movie-item-back-inner">
                                    <div class="amy-movie-item-content">
                                         <h3><a class="title-film" href="amy_tvshow/the-arrows/index.html">${movie.nameVN}</a></h3>
                                        <p>${movie.nameEng}</p>
                                        ${movie.scheduleMovies.map((val)=>{

                            return [`<button class="timeBtn"><b class="timeText">${val.schedule.time}</b></button>`]

                        })}
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <hr>`
                    ];
                }))
            )
        },
        failure: function (errMsg) {
            alert(errMsg);
        }
    });
});