package com.client;

import java.net.Socket;
import java.net.SocketException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class client {
    public static void main(String[] args) {
        String IP_address = "127.0.0.1"; //this is localhost IP address, I have used Ip address to let DNS be happy
        int port = 5000;

        
        String[] userData = new String[4];
        Socket socket = null;
        ObjectOutputStream request = null;
        ObjectInputStream response = null;
        
            System.out.println("Enter User First name:");
            userData[0] = System.console().readLine();
            System.out.println("Enter user Last name: ");
            userData[1] =System.console().readLine();
            System.out.println("Enter user phone: ");
            userData[2] =System.console().readLine();
            System.out.println("Enter user password: ");
            userData[3] =System.console().readLine();
            
        

        try {
            socket = new Socket(IP_address, port);
            request = new ObjectOutputStream(socket.getOutputStream());
            response = new ObjectInputStream(socket.getInputStream());

            request.writeObject(userData);
            double serverResponse = response.readDouble();
            System.out.println("Server is responding with Object: " + serverResponse);
        } 
        catch (SocketException e) {
            System.out.println("Socket error: " + e.getMessage());
        } 
        catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        } 
       finally {
            // close client socket so as to reduce resource leak.
            try {
                if (response != null) response.close();
                if (request != null) request.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            } 
        }
    }
}
