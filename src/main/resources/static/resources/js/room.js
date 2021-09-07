var size = 5;
var currentPage = 0;
var totalPages = 0;
var token = localStorage.getItem('Authorization');

$( document ).ready(function() {
    // click Room Management Menu
    $(document).on("click","#roomManagement",function() {
        // $('#roomManagement').toggleClass('active')
        $.get({
            url: "http://localhost:8080/views/cinemaRoom/list.html",
            success: function (result) {

                $("#main-content").html(result);
                $("#entries").val(size);
            },
            headers: {
                "Authorization":token,
            },

        })
        showList(size,0,"");
    })
    // click create button
    $(document).on("click","#addBtn",function() {
        $.get({
            url: "/views/cinemaRoom/addRoom.html",
            headers: {
                "Authorization":token,
            },
            success: function(result){
                $("#entries").val(size);
                $("#main-content").html(result);
            }});
    })
    //

    $(document).on("change","#entries",function() {
        size = $(this).val()
        showList(size, 0, $("#searchNameRoom").val());
    });
    // insert room into DB
    $(document).on("click","#addRoom",function() {

        let cinemaRoom = {};
        let roomName = $("#roomName").val();
        let seatQuantity = $("#seatQuantity").val();
        let normalPrice = $("#normalPrice").val();
        let vipPrice = $("#vipPrice").val();

        console.log(checkValidate(roomName,normalPrice,vipPrice));

        if(checkValidate(roomName,normalPrice,vipPrice)==true){
            cinemaRoom.roomName = roomName;
            cinemaRoom.seatQuantity = seatQuantity;
            cinemaRoom.normalPrice = normalPrice;
            cinemaRoom.vipPrice = vipPrice;

            $.ajax({
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                    "Authorization":token,
                },

                type: "POST",
                data: JSON.stringify(cinemaRoom),
                url: "http://localhost:8080/api/cinemaRoom/addRoom",
                success: function(result){
                    $.get({
                        url: "http://localhost:8080/views/cinemaRoom/list.html",
                        headers: {
                            "Authorization":token,
                        },
                        success: function (result) {

                            $("#main-content").html(result);
                            $("#entries").val(size);
                        }
                    })
                    showList(size,0,"");
                }
            })

        }

    })
    // back to list
    $(document).on("click","#backToList",function() {
        $.get({
            url: "http://localhost:8080/views/cinemaRoom/list.html",
            headers: {
                "Authorization":token,
            },
            success: function (result) {

                $("#main-content").html(result);
                $("#entries").val(size);
            }
        })
        showList(size,0,"");
    })
});

// delete room
function deleteRoom(id){

    $('#deleteModal').modal('show');

    $(document).on("click","#btnDelConfirm",function() {
        let settings = {
            "url": `http://localhost:8080/api/cinemaRoom/deleteRoom/${id}`,
            "method": "DELETE",
            "timeout": 0,
            "headers": {
                'Authorization':token,
            },
        };
        $.ajax(settings).done(function (response) {
            $(".modal-backdrop").remove();
            $.get({
                url: "http://localhost:8080/views/cinemaRoom/list.html",
                headers: {
                    'Authorization':token,
                },
                success: function (result) {

                    $.get({
                        url: "http://localhost:8080/views/cinemaRoom/list.html",
                        headers: {
                            'Authorization':token,
                        },
                        success: function (result) {

                            $("#main-content").html(result);
                            $("#entries").val(size);
                            showList(size,0,"");
                        }
                    })
                }
            })

        })
    })



}



function showList(size,page,searchNameRoom) {
    let trHTML = '';
    if (searchNameRoom == null) searchNameRoom = "";

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization':token,
        },

        type: "GET",

        url: `http://localhost:8080/api/cinemaRoom/listRoom?size=${size}&&page=${page}&&searchNameRoom=${searchNameRoom}` ,

        success: function (response) {
            console.log("on loaded");
            let listRoom = response['content'];
            console.log(listRoom);
            for (room of listRoom) {
                let id = room.id;
                let roomName = room.roomName;
                let seatQuantity = room.seatQuantity;


                trHTML += '<tr>';
                trHTML += '<td>' + id + '</td>';
                trHTML += '<td>' + roomName + '</td>';
                trHTML += '<td>' + seatQuantity + '</td>';
                trHTML += '<td>' + '<button onclick="showSeatDetail('+id+')" class="btn btn-fill btn-info btn-sm" > <i class="fa fa-info-circle"></i>' +
                    '                                ' +
                    '                             Detail</button>'+ '</td>';
                trHTML += '<td>'+'<button onclick="updateRoom('+id +')" class="btn btn-fill btn-warning btn-sm"> <i class="fas fa-edit">' +                            '                                ' +
                    '                              </i>\n' +
                    '                              </button>'
                    + ' <button onclick="deleteRoom('+id+')"   class="btn btn-fill btn-danger btn-sm" data-target="#deleteModal">\n' +
                    '                              <span class="material-icons">\n' +
                    '                                delete\n' +
                    '                              </span></button>' + '</td>';
                trHTML += '</tr>';
            }

            $('#listRoom').html(trHTML);
            updatePageNavigator(response);
            handlePageNavigation(searchNameRoom);

        },
        error: function (content) {
            console.log(content);
        }
    });


}

let listSeats=[];

function showSeatDetail(id) {

    $.get({
        url: "/views/cinemaRoom/detailSeats.html",
        headers: {
            "Authorization":token,
        },
        success: function(result){

            $("#main-content").html(result);

            $.ajax({
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                    "Authorization":token,
                },
                type: "GET",

                url: `http://localhost:8080/api/cinemaRoom/${id}`,
                success: function (data) {
                    listSeats = data.seats;
                    console.log(listSeats)
                    var content = '';
                    listSeats.forEach((value => {
                        if(value.seatType==1){
                            content+=`<checkbox class="seatBtn selecting">${value.seatRow}${value.seatColumn}</checkbox>`
                        }else {
                            content+=`<checkbox class="seatBtn choose vip">${value.seatRow}${value.seatColumn}</checkbox>`
                        }

                    }))
                    $('#seatContent').html(content);
                },
                error: function (content) {
                    console.log(content);
                }
            });


        }});
}
function updateRoom(id ) {
    console.log(id);
    $.get({
        url: "/views/cinemaRoom/editRoom.html",
        headers: {
            "Authorization":token,
        },
        success: function(result){
            $("#main-content").html(result);
            $.ajax({
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                    "Authorization":token,

                },
                type: "GET",

                url: `http://localhost:8080/api/cinemaRoom/${id}`,
                success: function (data) {
                    listSeats = data.seats;
                    let roomName = data.roomName;
                    let seatQuantity = data.seatQuantity;
                    let vipPrice =0 ;
                    let normalPrice =0;
                    console.log(listSeats);

                    listSeats.forEach((value => {
                        console.log(value)
                        if(value.seatType ==1){
                            normalPrice =value.seatPrice;
                        }else {
                            vipPrice = value.seatPrice;
                        }

                    }))
                    document.getElementById("roomName").value=roomName;
                    document.getElementById("normalPrice").value=normalPrice;
                    document.getElementById("vipPrice").value=vipPrice;
                    document.getElementById("seatQuantity").value=seatQuantity;
                    $(document).on("click","#updateRoom",function() {

                        let cinemaRoom = {};
                        let roomName = document.getElementById("roomName").value;
                        let seatQuantity = document.getElementById("seatQuantity").value;
                        let normalPrice = document.getElementById("normalPrice").value;
                        let vipPrice = document.getElementById("vipPrice").value;
                        if(checkValidate(roomName,normalPrice,vipPrice)==true){
                            cinemaRoom.roomName = roomName;
                            cinemaRoom.id = id;
                            cinemaRoom.seatQuantity = seatQuantity;
                            cinemaRoom.normalPrice = normalPrice;
                            cinemaRoom.vipPrice = vipPrice;
                            console.log(cinemaRoom);

                            $.ajax({
                                headers: {
                                    'Accept': 'application/json',
                                    'Content-Type': 'application/json',
                                    "Authorization":token,
                                },
                                type: "POST",
                                data: JSON.stringify(cinemaRoom),
                                url: "http://localhost:8080/api/cinemaRoom/editRoom",
                                success: function(result){
                                    $.get({
                                        url: "http://localhost:8080/views/cinemaRoom/list.html",
                                        headers: {
                                            "Authorization":token,
                                        },
                                        success: function (result) {

                                            $("#main-content").html(result);
                                            $("#entries").val(size);
                                        }
                                    })
                                    showList(size,0,"");
                                }
                            })

                        }
                    })

                },
                error: function (content) {
                    console.log(content);
                }
            });
        }});
}
//Pagination
function handlePageNavigation(searchKey) {
    $(".pagination li:first-child").unbind().click(function () {
        console.log("previous clicked")
        if (currentPage > 0) {
            showList( size,currentPage - 1, searchKey);
        }
    })
    $(".pagination li:last-child").unbind().click(function () {
        console.log("next clicked")
        if (currentPage < totalPages - 1) {
            showList( size,currentPage + 1, searchKey);
        }
    })

    $(".pagination .num").unbind().click(function () {
        let pageNum = $(this).children().first().text();
        console.log(pageNum);
        showList( size,pageNum - 1, searchKey);
    })
}

function updatePageNavigator(page) {
    totalPages = page["totalPages"];
    currentPage = page["number"];

    $(".pagination li").removeClass("disabled");

    if (page.first) {
        $(".pagination li:first-child").addClass("disabled");
    }

    if (page.last) {
        $(".pagination li:last-child").addClass("disabled");
    }

    $(".pagination .num").remove();
    for (let i = 1; i <= totalPages; i++) {
        $(".next").before(
            `
            <li class="page-item num">
                <a class="page-link" href="#">${i}</a>
            </li>
            `
        )
    }

    $(".pagination .num").removeClass("active");
    $(".pagination .num").eq(currentPage).addClass("active");
}

$( document ).ready(function() {

    $(document).on("change","#searchNameRoom",function() {
        let searchNameRoom= $("#searchNameRoom").val();
        if(searchNameRoom!=""){
            showList(size,0,searchNameRoom);
        }
        document.getElementById("searchNameRoom").value=searchNameRoom;
    })
})
function checkValidate(roomName,normalPrice,vipPrice) {
    let checkValid = false;

    if(roomName == ""){
        checkValid = false;
        document.getElementById("nameRoomError").innerHTML="<p  style='color: red'>Invalid Name Room! please try again</p>";
        $("#roomName").css('border-bottom','1px solid red');
    }else{
        $("#roomName").css('border-bottom','');
        document.getElementById("nameRoomError").innerHTML="";
    }

    if(normalPrice==""){
        checkValid = false;
        document.getElementById("normalPriceError").innerHTML="<p  style='color: red'>Invalid  Normal Price! please try again</p>";
        $("#normalPrice").css('border-bottom','1px solid red');
    }else {
        $("#normalPrice").css('border-bottom','');
        document.getElementById("normalPriceError").innerHTML="";

    }

    if(vipPrice==""){
        checkValid = false;
        document.getElementById("vipPriceError").innerHTML="<p  style='color: red'>Invalid  Vip Price! please try again</p>";
        $("#vipPrice").css('border-bottom','1px solid red')
    }else{
        $("#vipPrice").css('border-bottom','');
        document.getElementById("vipPriceError").innerHTML="";

    }
    if(roomName !=""&&vipPrice!=""&&normalPrice!=""){
        return true;
    }
    return checkValid;
}