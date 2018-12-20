package com.company;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

//Server
public class Main {

    public static final int PORT = 3000;

    public static void main(String[] args) {
        Map<Integer , Place> rooms = new HashMap<>();
        File file = new File("hotel_project.txt");
        WriteAndRead.readPlaceFromFile(rooms,file);
            //connection to client
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(PORT);
            while (true){
                Socket socket = serverSocket.accept();
                new ClientThread(socket, rooms, file).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}