var pageSize = 5;
var currentPage = 0;
var totalPages = 0;
var token = localStorage.getItem('Authorization');
$(document).ready(function () {
    $("#btnAdd").click(function () {
        // $(".card-title").text("Add promotion")
        $("#main-content").load("../../views/promotion/promotion-add.html");
    })
    loadPage(currentPage, pageSize);
    $("#search").keyup(function (e) {
        if (e.keyCode === 13) {
            console.log("search key up");
            loadPage(0, pageSize, $(this).val());
        }
    })

    $(".entries").change(function () {
        pageSize = $(this).val()
        loadPage(currentPage, pageSize, $("#search").val());
    });
})

function loadPage(page, size, searchKey) {
    if (searchKey == null) searchKey = "";

    var settings = {
        "url": `http://localhost:8080/api/promotion/list?page=${page}
                &&size=${size}&&title=${searchKey}  `,
        "method": "GET",
        "timeout": 0,
        headers: {
            "Authorization":token,
        },
    };

    $.ajax(settings).done(function (response) {
        console.log("on loaded")
        $("tbody tr").remove();
        let promotions = response['content'];
        promotions.forEach((promotion, index) => {
            $("tbody").append(
                `
                <tr>
                    <td>${index + 1}</td>
                    <td>${promotion.id}</td>
                    <td>${promotion.title}</td>
                    <td>${promotion.startTime}</td>
                    <td>${promotion.endTime}</td>
                    <td>${promotion.discountLevel}</td>
                    <td>${promotion.detail}</td>
                    <td>
                        <button type="button" id="btnEdit" class="btn btn-sm btn-warning" onclick="onEditBtnClick(${promotion.id})"><i
                                class="fas fa-edit"></i></button>
                    </td>
                    <td>
                        <button type="button" id="btnDel" class="btn btn-sm btn-danger" data-toggle="modal" data-target="#deleteDialog" 
                        onclick="onDeleteBtnClick(${promotion.id})"><i
                                class="fas fa-trash"></i></button>
                    </td>
                    </tr>
                `
            )
        });
        updatePageNavigator(response)
        handlePageNavigation(searchKey)
    });

}

function onEditBtnClick(promotionId) {
    // $(".card-title").text("Update promotion")
    $("#main-content").load("../../views/promotion/promotion-update.html", function () {
        var settings = {
            "url": "http://localhost:8080/api/promotion/" + promotionId,
            "method": "GET",
            headers: {
                "Authorization":token,
            },
            "timeout": 0,
        };

        $.ajax(settings).done(function (response) {
            for (const [key, value] of Object.entries(response)) {
                console.log(`${key}: ${value}`);
                $(`input[name=${key}]`).val(`${value}`);
            }
            $("#pic").attr("src", $("#base64Holder").val())
        });
    });
}

function onDeleteBtnClick(promotionId) {
    console.log("on delete button click")
    $("#deleteDialog").show()
    $("#btnDelConfirm").click(function () {
        var settings = {
            "url": `http://localhost:8080/api/promotion/delete/${promotionId}`,
            "method": "DELETE",
            headers: {
                "Authorization":token,
            },
            "timeout": 0,
        };

        $.ajax(settings).done(function (response) {
            console.log("delete successfully!");
            loadPage(currentPage, pageSize, $("#search").val())
        })
    })
}
