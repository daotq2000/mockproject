$(document).ready(function () {
    var token = localStorage.getItem('Authorization');

    $("#btnSave").click(function () {
        var settings = {
            "url": `http://localhost:8080/api/promotion/save`,
            "method": "POST",
            "headers": {
                "Content-Type": "application/json",
                "Authorization":token,
            },
            "data": formArrayToJson($("#formSave").serializeArray())
        }
        $.ajax(settings).done(function (response) {
            console.log(response);
            $("span.error").remove();
            $(".success-message").show();
            showToast($("#toast-success"))
        }).fail(function (response) {
            console.log(response);
            $("span.error").remove();
            let errors = response["responseJSON"].errors;
            let validMsg = response.message;
            toastError(errors, validMsg);
        });
    })

    function formArrayToJson(formArray) {
        console.log(formArray)
        let ans = {};
        formArray.forEach(element => {
            ans[element['name']] = element['value'];
        });
        return JSON.stringify(ans);
    }
})

function toastError(errors, validMsg) {
    errors.forEach((error) => {
        $(`input[name= ${error["field"]}]`)
            .after(`<span class="error text-danger">${error["defaultMessage"]}</span>`);

        $(`textarea[name= ${error["field"]}]`)
            .after(`<span class="error text-danger">${error["defaultMessage"]}</span>`);
    });
    $("#toast-error .toast-body").append(".").append(validMsg);
    showToast($("#toast-error"));
}

function showToast(toast) {
    toast.show();
    window.setTimeout(function () {
        toast.fadeTo(500, 0).slideUp(500, function () {
            toast.hide();
        });
    }, 1000);
}

var formFile = $("#formFile")

function encodeImgToBase64(fileInput) {
    console.log(fileInput);
    let img = fileInput.files[0];
    let reader = new FileReader();
    reader.onloadend = function () {
        console.log(reader.result);
        $("#base64Holder").val(reader.result);
        $("#pic").attr("src", reader.result);
    }
    reader.readAsDataURL(img);
}
