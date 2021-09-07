$(document).ready(function () {
    $("#link_promotion").click(function () {
        console.log("promotion-list");
        $(".card-title").text("Promotion list")
        $(".card-body").load("../views/promotion/promotion-list.html")
    })
})