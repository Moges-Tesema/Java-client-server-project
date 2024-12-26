package com.server;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
class ClientHandling implements Runnable {
    private Socket socket;
    DataInputStream clientRequest;
    DataOutputStream serverResponse;

    public ClientHandling(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            clientRequest = new DataInputStream(socket.getInputStream());
            serverResponse = new DataOutputStream(socket.getOutputStream());
            System.out.println("client request a number: " + clientRequest.readDouble());
            System.out.println("server response number is: " + 20000);
            serverResponse.writeDouble(20000);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}