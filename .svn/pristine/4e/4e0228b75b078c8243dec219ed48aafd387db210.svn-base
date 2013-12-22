/*
  Script sets:
    - text tag with id "position" to: "It appears you are in: " + city + ", " + postcode IF position is found, else it puts to "It was not possible to retrieve position"
    - text tag with id "error" to: <<error message>>
    - input tag with id "where" to: zip
 */



var error = document.getElementById("error");
var pos = document.getElementById("position");
var zip = document.getElementById("where");


if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(showPosition,showError);
} else {
    x.innerHTML="Geolocation non &egrave; supportata da questo browser.";
}

if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
    xmlhttp=new XMLHttpRequest();
} else {// code for IE6, IE5
    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
}

function showPosition(position){
    xmlhttp.open("GET",("http://nominatim.openstreetmap.org/reverse?format=xml&lat=" + position.coords.latitude + "&lon=" + position.coords.longitude + "&zoom=18&addressdetails=3"),false);
    xmlhttp.send();
    xmlDoc=xmlhttp.responseXML;
    var x = xmlDoc.getElementsByTagName("addressparts");
    var city = x[0].getElementsByTagName("city")[0].childNodes[0].nodeValue;
    var postcode = x[0].getElementsByTagName("postcode")[0].childNodes[0].nodeValue;
    pos.innerHTML= "Pare tu sia a: " + city + ", " + postcode + ".<br>Se vuoi cambiare posizione, cambia il CAP qui sotto.";
    zip.value = postcode;
}

function showError(id) {
    pos.innerHTML= "&nbsp;<br>Non &egrave; stato possibile localizzarti.";
    switch(id.code) {
        case id.PERMISSION_DENIED:
            error.innerHTML="Non hai acconsentito al rilevamento della posizione.";
            break;
        case id.POSITION_UNAVAILABLE:
            error.innerHTML="Non &egrave; stato possibile localizzarti.";
            break;
        case id.TIMEOUT:
            error.innerHTML="The request to get user location timed out.";
            break;
        case id.UNKNOWN_ERROR:
            error.innerHTML="An unknown error occurred.";
            break;
    }
}