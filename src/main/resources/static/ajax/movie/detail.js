jQuery.validator.addMethod("greaterThan",
    function(value, element, params) {

        if (!/Invalid|NaN/.test(new Date(value))) {
            return new Date(value) > new Date($(params).val());
        }

        return isNaN(value) && isNaN($(params).val())
            || (Number(value) > Number($(params).val()));
    },'End date must be greater than start date.');
$(document).ready(function () {

    $("#btnback").click(function () {
        $.get({
            url: "http://localhost:8080/views/movie-management/list-movie.html",
            success: function (response) {
                $("#main-content").html(response);
            },
        });
    });



    var id= $("input[name='id']").val()
    // if ($('#id').val().length!==0){
    //     $('.card-title').text('Detail movie ' + $('#nameEng').val())
    // }else {
    //     $('.card-title').text("Add movie")
    // }
    $('.card-title').text("Add or Edit movie")
    function formArrayToJson(formArray) {
        // console.log(formArray)
        let ans = {};
        formArray.forEach(element => {
            ans[element['name']] = element['value'];
        });
        return JSON.stringify(ans);
    }

    $("#detailForm").validate({
        rules: {
            nameEng: {
                required: true,
            },
            nameVN: {
                required: true,
            },
            fromDate: {
                required: true,
            },
            toDate: {
                required: true,
                greaterThan: "#fromDate"
            },
            actor: {
                required: true,
            },
            movieCompany: {
                required: true,
            },
            director: {
                required: true,
            },
            duration: {
                required: true,
                number: true,
                // min: 1,
            },
            version: {
                required: true,
            },
            cinemaRoom: {
                required: true,
            },
            content: {
                required: true,
            },
        },
        messages: {
            nameEng: {
                required: "Please enter english name",
            },
            nameVN: {
                required: "Please enter vietnamese name",
            },
            fromDate: {
                required: "Please enter start date",
            },
            toDate: {
                required: "please enter end date",
            },
            actor: {
                required: "please enter actor",
            },
            movieCompany: {
                required: "please enter production company",
            },
            director: {
                required: "please enter direction",
            },
            duration: {
                required: "please enter duration",
            },
            version: {
                required: "please enter version",
            },

            cinemaRoom: {
                required: "please enter cinemaroom",
            },
            content: {
                required: "please enter content",
            },
        },
        submitHandler: function () {
            // var data = $('form').serializeArray();
            var nameEng = $('#nameEng').val()
            var nameVN = $('#nameVN').val()
            var fromDate = $('#fromDate').val()
            var toDate = $('#toDate').val()
            var actor = $('#actor').val()
            var movieCompany = $('#movieCompany').val()
            var director = $('#director').val()
            var duration = $('#duration').val()
            var version = $('#version').val()
            var content = $('#content').val()
            var cinemaRoom = $('#cinemaRoom').val()
            var id = $('#id').val()
            var image = $('#image').val()


            var typeMovies = `[`;
            $('#typeMovies :checkbox:checked').each(function(i){
                typeMovies+= `{"movie":null,"type":` + $(this).val()+`},`
            });
            typeMovies+=`]`;
            typeMovies=typeMovies.slice(0,-2)+`]`

            var scheduleMovies = `[`;
            $('#scheduleMovies :checkbox:checked').each(function(i){
                scheduleMovies+= `{"movie":null,"schedule":` + $(this).val()+`},`
            });
            scheduleMovies+=`]`;
            scheduleMovies=scheduleMovies.slice(0,-2)+`]`

            if (scheduleMovies===']') scheduleMovies="null"
            if (typeMovies===']') typeMovies="null"
            fromDate = new Date(fromDate).toISOString().split("T")[0];
            toDate = new Date(toDate).toISOString().split("T")[0];
            console.log(fromDate,toDate)
            var data=`{
                "id" : "${id}",
                "nameEng" : "${nameEng}",
                "nameVN" : "${nameVN}",
                "fromDate" : "${fromDate}",
                "toDate" : "${toDate}",
                "actor" : "${actor}",
                "movieCompany" : "${movieCompany}",
                "director" : "${director}",
                "duration" : "${duration}",
                "version" : "${version}",
                "content" : "${content}",
                "cinemaRoom" : ${cinemaRoom},
                "typeMovies" : ${typeMovies},
                "scheduleMovies" : ${scheduleMovies},
                "image": "${image}"
            }`
            console.log(data)
            if (id.length!==0){
                $.ajax({
                    url: `http://localhost:8080/api/movies/${id}`,
                    "headers": {
                        "Content-Type": "application/json"
                    },
                    type: 'PUT',
                    "data": data,
                    success: function (response) {
                        $.get({
                            url: "http://localhost:8080/views/movie-management/list-movie.html",
                            success: function (response) {
                                $("#main-content").html(response);
                            },
                        });
                    },
                    error: function (response) {
                        $("span.error").remove();
                        let errors = response["responseJSON"].errors;
                        let validMsg = response["responseJSON"].message;
                        toastError(errors, validMsg);
                    }
                });
            }else {
                $.post({
                    url: "http://localhost:8080/api/movies",
                    "headers": {
                        "Content-Type": "application/json"
                    },
                    "data": data,
                    success: function (response) {
                        $.get({
                            url: "http://localhost:8080/views/movie-management/list-movie.html",
                            success: function (response) {
                                $("#main-content").html(response);
                                $(".card-title").val("Add movie")
                            },
                        });
                    },
                    error: function (response) {
                        $("span.error").remove();
                        let errors = response["responseJSON"].errors;
                        let validMsg = response["responseJSON"].message;
                        toastError(errors, validMsg);
                    }
                });
            }

        },
    });

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/types",
        dataType: "json",
        success: function (result) {
            var listTypeMovie = $('#hiddenTypeMovies').val().split(",")
            $("#typeMovies .col-sm-2").remove();
            $.each(result, function(i, item) {
                var typeId = item.name.replace(/ /g,'');
                $('#typeMovies').append(
                    `
                        <label class="col-sm-2"
                        ><input id="${typeId}" type="checkbox" value='${JSON.stringify(item)}' /> ${item.name}</label
                        >
                    `
                );
                for (i in listTypeMovie){
                    if (listTypeMovie[i]===item.name){
                        $(`#${typeId}`).prop('checked', true);
                        break
                    }
                }

            });
        },
        error: function (xhr, status, error) {
            alert("an error has occur")
        }
    });
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/schedules",
        dataType: "json",
        success: function (result) {
            var listScheduleMovie = $('#hiddenScheduleMovies').val().split(",")
            $("#scheduleMovies .col-sm-2").remove();
            $.each(result, function(i, item) {
                var shcheduleId = item.time.replace(/ /g,'').replace(/:/g,'');
                $('#scheduleMovies').append(
                    `
                         <label class="col-sm-2"
                        ><input id="${shcheduleId}" type="checkbox" value='${JSON.stringify(item)}' /> ${item.time}</label
                        >
                    `
                );
                for (i in listScheduleMovie){
                    if (listScheduleMovie[i]===item.time){
                        $(`#${shcheduleId}`).prop('checked', true);
                        break
                    }
                }


            });


        },
        error: function (xhr, status, error) {
            alert("an error has occur")
        }
    });
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/cinemaRoom",
        dataType: "json",
        success: function (result) {
            console.log(result)
            $("#cinemaRoom option").remove();
            $.each(result, function(i, item) {
                $('#cinemaRoom').append(
                    `
                        <option value='${JSON.stringify(item)}'>${item.roomName}</option>
                    `
                );
                if (item.roomName===$('#hiddenCinemaRoom').val()){
                    $(`select option[value='${JSON.stringify(item)}']`).attr("selected","selected");
                }

            });
        },
        error: function (xhr, status, error) {

        }
    });

    function readFile() {

        if (this.files && this.files[0]) {

            var FR= new FileReader();

            FR.addEventListener("load", function(e) {
                document.getElementById("img").src = e.target.result;
                $('#image').val(e.target.result);
            });
            FR.readAsDataURL( this.files[0] );
        }

    }
    document.getElementById("inp").addEventListener("change", readFile);



});
function toastError(errors, validMsg) {
    $(".error-message").text(validMsg).show();
    errors.forEach((error) => {
        $(`#${error["field"]}`)
            .after(`<span class="error text-danger">${error["defaultMessage"]}</span>`);
    });
    // $("#toast-error .toast-body").append(".").append(validMsg);
    // showToast($("#toast-error"));
}