/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.soniir.WebSocketServer;
import java.util.HashMap;
import spark.ModelAndView;
import spark.Spark;
import static spark.Spark.get;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
/**
 *
 * @author slava
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Spark.webSocket("/websocket/echo", EchoWebSocket.class);
        Spark.get("/",(request,response)->{
            return "Hello world";
        });
        Spark.get("/echo",(request,response)->{
            HashMap<String,Object> model = new HashMap();
            return new ThymeleafTemplateEngine().render(new ModelAndView(model,"echoview"));
        });
        Spark.init();
    }
    
}
