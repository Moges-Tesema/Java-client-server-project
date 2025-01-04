package com.server;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import com.database.BankDB;
class ClientHandling implements Runnable {
    private Socket socket;
   ObjectInputStream clientRequest;
   ObjectOutputStream serverResponse;
    BankDB database;

    public ClientHandling(Socket socket, BankDB database) {
        this.socket = socket;
        this.database = database;
        
    }
   
    @Override
    public void run() {
        try {
            clientRequest = new ObjectInputStream(socket.getInputStream());
            serverResponse = new ObjectOutputStream(socket.getOutputStream());
           String[] clientRequest =clientRequest.readObject();
            System.out.println("client request an object: " +clientRequest );
            serverResponse.writeUTF("server is responding. statuscode: 200, OK");
        
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found exception: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}