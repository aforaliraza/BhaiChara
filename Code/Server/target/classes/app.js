var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/startwebsocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/driver/' + $("#groupcode").val(), function (msg) {
        	console.log(msg);
        	showGreeting(JSON.stringify(msg));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

//function sendName() {
  //  stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
   
//}

function sendGroupCode() {
	//stompClient.send("/app/hello", {}, JSON.stringify({'groupcode': $("#groupcode").val(),'name': $("#name").val()}));
	

	stompClient.send("/websocket/getdriverinfo/" + $("#groupcode").val(), {}, JSON.stringify({'groupcode': $("#groupcode").val(),'name': $("#name").val()}));
}
/*function sendCurrentLat() {
    stompClient.send("/app/hello", {}, JSON.stringify({'currentLat': $("#name").val()}));
}
function sendCurrentLong() {
    stompClient.send("/app/hello", {}, JSON.stringify({'currentLong': $("#name").val()}));
}
function sendSeatsAvailable() {
    stompClient.send("/app/hello", {}, JSON.stringify({'seatsAvailable': $("#name").val()}));
}
function sendDriversDetails() {
	stompClient.send("/app/hello", {}, JSON.stringify({'currentLat': $("#name").val()}));
	stompClient.send("/app/hello", {}, JSON.stringify({'currentLong': $("#name").val()}));
	stompClient.send("/app/hello", {}, JSON.stringify({'seatsAvailable': $("#name").val()}));
}*/


function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
   // $( "#send" ).click(function() {sendDriversDetails() });
  //  $( "#send" ).click(function() { sendCurrentLat(); sendCurrentLong(); sendSeatsAvailable() });
   $( "#send" ).click(function() {sendGroupCode();});
    
    
});