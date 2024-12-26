package com.server;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.ServerException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class server {
 public static void main(String args[]){
    int port = 5000;
    ServerSocket server;
    DataInputStream clientRequest;
    DataOutputStream serverResponse;
    Socket socket;
    
    try{
        server = new ServerSocket(port);
        socket = server.accept();
        clientRequest = new DataInputStream(socket.getInputStream()) ;
        serverResponse = new DataOutputStream(socket.getOutputStream());
        System.out.println("client request a number: "+clientRequest.readDouble());
        System.out.println("server response number is: "+20000);
        serverResponse.writeDouble(20000);


    } catch(ServerException e){
        System.out.println("OOP... server is not runnig! "+e.getMessage());
    }catch(IOException e){
        System.out.println("there is input output error: "+e.getMessage());
    }

    
 }
}
