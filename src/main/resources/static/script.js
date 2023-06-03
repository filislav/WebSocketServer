console.log("Script attached");
var ws = new WebSocket("ws://localhost:4567/websocket/echo");
ws.onopen = function(event){
    var i =0;
    console.log(event);
    let timer = setInterval(()=>{
            ws.send(i++);
            if(i===101){
                clearInterval(timer);
            }
        },1000);   
}
ws.onclose = function(event){
    console.log(event);
}

ws.onmessage = function(event){
    console.log(event);
    document.getElementById("date").innerHTML = event.data; 
}