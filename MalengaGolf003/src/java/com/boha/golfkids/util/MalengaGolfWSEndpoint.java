/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.golfkids.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author aubreyM
 */
@ServerEndpoint("/mg")
public class MalengaGolfWSEndpoint {
    
     private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    @OnMessage
    public String onMessage(String message) {        
        log.log(Level.INFO, "Message received on socket - {0} peers: {1}", 
                new Object[]{message, peers.size()});
        
        return "Returning something to somebody";
    }
    
  
    public void echo(String message) {
        log.log(Level.INFO, "Message echo - {0}", message);
    }
    @OnOpen
    public void onOpen (Session peer) {
        peers.add(peer);
        log.log(Level.INFO, "Session peer added: \nNegotiatedSubprotocol{0} \nProtocolVersion: "
                + "{1} \nqueryString: {2}", new Object[]{peer.getNegotiatedSubprotocol(), 
                    peer.getProtocolVersion(), peer.getQueryString()});
    }

    @OnClose
    public void onClose (Session peer) {
        peers.remove(peer);
        log.log(Level.INFO, "Session peer removed: \nNegotiatedSubprotocol{0} \nProtocolVersion: "
                + "{1} \nqueryString: {2}", new Object[]{peer.getNegotiatedSubprotocol(), 
                    peer.getProtocolVersion(), peer.getQueryString()});
    }
    
   

    @OnError
    public void onError(Throwable t) {
        log.log(Level.SEVERE, "WebSocket Fail", t);
    }
    
     static final Logger log = Logger.getLogger("MalengaGolfWSEndpoint");
}
