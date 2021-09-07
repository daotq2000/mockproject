function isEmail(inputEmail) {
    let regex = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return regex.test(inputEmail);
}

$(document).ready(function (){


    $('#loginBtn').on('click',function (){
        window.location.href= 'http://localhost:8080/';
    })


})
function  validate(){
    var isVlid = true;
    if ($("#userName").val().length == 0) {
        $(".userNameError").html("User Name should not be blank");
        isVlid = false;
    }else{
        $(".userNameError").html("");
    }

    let password = $("#password").val();
    if (password.length == 0) {
        $(".passwordError").html("Password should not be blank");
        isVlid = false;
    }else{
        $(".passwordError").html("");
    }


    let confirm = $("#confirmPassword").val();
    if (confirm.length == 0) {
        $(".confirmPasswordError").html("Confirm password should not be blank");
        isVlid = false;
    } else if(confirm !=password){
        $(".confirmPasswordError").html("Confirm password not match");
        isVlid = false;
    }else{
        $(".confirmPasswordError").html("");
    }

    if ($("#fullName").val().length == 0) {
        $(".fullNameError").html("Full Name should not be blank");
        isVlid = false;
    }else{
        $(".fullNameError").html("");
    }

    if ($("#identityCard").val().length == 0) {
        $(".identityCardError").html("Identity card should not be blank");
        isVlid = false;
    }else{
        $(".identityCardError").html("");
    }

    let email = $("#email").val();
    if (email.length == 0) {
        $(".emailError").html("Email should not be blank");
        isVlid = false;
    }else if(!isEmail(email)){
        $(".emailError").html("Email not correct");
        isVlid = false;
    }
    else{
        $(".emailError").html("");
    }

    if ($("#address").val().length == 0) {
        $(".addressError").html("Address should not be blank");
        isVlid = false;
    }else{
        $(".addressError").html("");
    }

    if ($("#phoneNumber").val().length == 0) {
        $(".phoneNumberError").html("Phone number should not be blank");
        isVlid = false;
    }else{
        $(".phoneNumberError").html("");
    }
    return isVlid;
}