/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.soniir.WebSocketServer;

/**
 *
 * @author slava
 */
import org.eclipse.jetty.websocket.api.*;
import org.eclipse.jetty.websocket.api.annotations.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebSocket
public class EchoWebSocket {

    // Store sessions if you want to, for example, broadcast a message to all users
    private static final Queue<Session> sessions = new ConcurrentLinkedQueue<>();
    private Session curSession;
    private boolean running = true;
    public Session getSession(){
        return this.curSession;
    }
    
    public void send(String message) throws IOException{
        if(curSession!=null){
            curSession.getRemote().sendString(message);
        }
    }
        
    @OnWebSocketConnect
    public void connected(Session session) {
        curSession = session;
        sessions.add(session);
        while(running){
            try{
                send(new Date().toString());
                TimeUnit.SECONDS.sleep(1);
            }catch(IOException|InterruptedException ex){
                ex.getMessage();
            }
        }
    }

    @OnWebSocketClose
    public void closed(Session session, int statusCode, String reason) {
        sessions.remove(session);
    }

    @OnWebSocketMessage
    public void message(Session session, String message) throws IOException {
        System.out.println("Got: " + message);   // Print message
        session.getRemote().sendString(message); // and send it back
    }

}
