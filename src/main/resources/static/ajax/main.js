var request = window.location.href;
if(request.indexOf("/movieManagement") != -1){
    if(localStorage.getItem("Authorization") == null || localStorage.getItem("Authorization") == undefined){
        window.location.href = "/unauthorized";
    }
}
