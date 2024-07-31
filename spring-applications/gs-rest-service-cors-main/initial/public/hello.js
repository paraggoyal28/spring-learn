$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/greeting-javaconfig"
    }).then(function(data, status, jqxhr) {
       $('.greeting-id').append(data.id);
       $('.greeting-content').append(data.content);
       console.log(jqxhr);
    });
});