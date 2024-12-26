package com.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.ServerException;
import java.io.IOException;
import com.server.ClientHandling;
public class server {
    public static void main(String args[]) {
        int port = 5000;
        ServerSocket server;
        Socket socket;

        try {
            server = new ServerSocket(port);
            while (true) {

                socket = server.accept();
                new Thread(new ClientHandling(socket)).start();
            }

        } catch (ServerException e) {
            System.out.println("OOP... server is not runnig! " + e.getMessage());
        } catch (IOException e) {
            System.out.println("there is input output error: " + e.getMessage());
        }

    }
}


/*
 * how to use the ablity of java multithreading to handle million clients
 * request at a time???
 * 1. create server using ServerSocket class
 * 2. use loop to listen multiple client
 * 3. use multithreading to run multiple thread at a time.
 * 4. create a class that handle single user request and response so as to make
 * threading.
 * 5. finished!
 */