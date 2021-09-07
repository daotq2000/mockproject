$(document).ready(function () {
    $('#registerNow').on('click',function (){
        $.ajax({
            type: "GET",
            url: "/views/login&register/register.html",
            success: function (data) {
                $('#body').html(data);
            },
            error: function (errMsg) {
                alert(errMsg);
            }
        });
    })
    $('#loginBtn').on('click', function () {
        var isValid = true;
        var userName = $('#username');
        var password = $('#password');
        if (userName.val() == '') {
            $('#usernameLabelError').html('Please enter Username')
            userName.addClass('errorInput')
            isValid = false;
        } else {
            $('#usernameLabelError').html('')
            userName.addClass('form-control form-control-user')
        }
        if (password.val() == '') {
            $('#passwordLabelError').html('Please enter password')
            password.addClass('errorInput')
            isValid = false;
        } else {
            password.addClass('form-control form-control-user')
            $('#passwordLabelError').html('')
        }
        var remember = $('#rememberMe').checked;
        var user = {
            username: userName.val(),
            password: password.val()
        }
        if (isValid) {

            $.ajax({
                type: "POST",
                url: "/auth",
                data: JSON.stringify(user),
                contentType: "application/json",
                success: function (data, textStatus, jQxhr) {
                    console.log(jQxhr)
                    if (jQxhr.status == 200) {
                        var token = jQxhr.getResponseHeader('authorization');
                        localStorage.setItem('Authorization', token);

                        $.ajax({
                            type: "GET",
                            url: "/movieManagement",
                            headers: {
                                "Authorization": token,
                            },
                            success: function (data) {
                                console.log(data);
                                window.location.href="/movieManagement"
                            },
                            failure: function (errMsg) {
                                console.log(errMsg);

                            }
                        });
                    }
                },
                error: function (jqXhr, textStatus, errorThrown) {
                    console.log(jqXhr);
                    if (jqXhr.status == 401) {
                        $('#errorMessage').html('Username or password Incorrect');
                    }else if (jqXhr.status == 403){
                        window.location.href="/unauthorized"
                    }
                }
            });
        }

    })
})