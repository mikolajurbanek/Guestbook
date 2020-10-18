package com.codecool.krk;

import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;


public class App {
    public static void main(String[] args) throws Exception {

        // create a server on port 8002
        HttpServer server = HttpServer.create(new InetSocketAddress(8002), 0);

        // set route
        server.createContext("/guestbook", new Guestbook());


        // creates a default executor
        server.setExecutor(null);

        // start listening
        System.out.println("server started at port " + server.getAddress() );
        server.start();
    }
}
