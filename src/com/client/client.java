package com.client;

import java.net.Socket;
import java.net.SocketException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class client {
    public static void main(String[] args) {
        String IP_address = "127.0.0.1"; //this is localhost IP address, I have used Ip address to let DNS be happy
        int port = 5000;

        
        Socket socket = null;
        DataOutputStream request = null;
        DataInputStream response = null;

        try {
            socket = new Socket(IP_address, port);
            request = new DataOutputStream(socket.getOutputStream());
            response = new DataInputStream(socket.getInputStream());

            request.writeDouble(223);
            double serverResponse = response.readDouble();
            System.out.println("Server is responding with data: " + serverResponse);
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
