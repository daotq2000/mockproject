function handlePageNavigation(searchKey) {
    $(".pagination li:first-child").unbind().click(function () {
        console.log("previous clicked")
        if (currentPage > 0) {
            loadPage(currentPage - 1, pageSize, searchKey);
        }
    })
    $(".pagination li:last-child").unbind().click(function () {
        console.log("next clicked")
        if (currentPage < totalPages - 1) {
            loadPage(currentPage + 1, pageSize, searchKey);
        }
    })

    $(".pagination .num").unbind().click(function () {
        let pageNum = $(this).children().first().text();
        console.log(pageNum);
        loadPage(pageNum - 1, pageSize, searchKey);
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

// function updatePageNavigator(response) {
//     totalPages = response.totalPages;
//     currentPage = response.number;
//
//     if (response.first) {
//         $(".pagination li:first-child").addClass("disabled");
//     } else {
//         $(".pagination li:first-child").removeClass("disabled");
//     }
//
//     if (response.last) {
//         $(".pagination li:last-child").addClass("disabled");
//     } else {
//         $(".pagination li:last-child").removeClass("disabled");
//     }
//
//     $(".pagination li").removeClass("active");
//
//     $(`.pagination li:nth-child(${currentPage % 3 + 2})`).addClass("active");
//
//     if ((currentPage + 1) % 3 === 1) {
//         $(`.pagination li:nth-child(2) a`).text(currentPage + 1);
//         $(`.pagination li:nth-child(3) a`).text(currentPage + 2);
//         $(`.pagination li:nth-child(4) a`).text(currentPage + 3);
//     } else if ((currentPage + 1) % 3 === 0) {
//         $(`.pagination li:nth-child(2) a`).text(currentPage - 1);
//         $(`.pagination li:nth-child(3) a`).text(currentPage);
//         $(`.pagination li:nth-child(4) a`).text(currentPage + 1);
//     }
//     $(`.num:gt(${totalPages - 1})`).remove();
// }
