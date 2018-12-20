package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {

    public static final String HOST = "127.0.0.1";
    public static final int PORT = 3000;
    public static final int ADD_DINING_ROOM = 100;
    public static final int ADD_STANDARD_ROOM = 101;
    public static final int ADD_SUITE_ROOM = 102;
    public static final int GET_ROOM = 103;
    public static final int OKAY = 200;
    public static final int FAILURE = 201;

    public static final int DINING_ROOM = 55;
    public static final int STANDARD_ROOM = 56;
    public static final int SUITE_ROOM = 57;

    //send the adding to server
    public static void addDiningRoom(final DiningRoom diningRoom){
        connect(new ConnectionListener() {
            @Override
            public void onConnect(InputStream inputStream, OutputStream outputStream) throws IOException {
                outputStream.write(ADD_DINING_ROOM);
                diningRoom.write(outputStream);
                if (inputStream.read() != OKAY)
                    throw new IOException("failed\n");
                System.out.println("\nAdd success");
                System.out.println(new DiningRoom(inputStream));

            }
        });
    }

    //send the adding to server
    public static void addStandardRoom(final StandardRoom standardRoom){
        connect(new ConnectionListener() {
            @Override
            public void onConnect(InputStream inputStream, OutputStream outputStream) throws IOException {
                outputStream.write(ADD_STANDARD_ROOM);
                standardRoom.write(outputStream);
                if (inputStream.read() != OKAY)
                    throw new IOException("failed\n");
                System.out.println("\nAdd success");
                System.out.println(new StandardRoom(inputStream));
            }
        });
    }

    //send the adding to server
    public static void addSuiteRoom(final SuiteRoom suiteRoom){
        connect(new ConnectionListener() {
            @Override
            public void onConnect(InputStream inputStream, OutputStream outputStream) throws IOException {
                outputStream.write(ADD_SUITE_ROOM);
                suiteRoom.write(outputStream);
                if (inputStream.read() != OKAY)
                    throw new IOException("failed\n");
                System.out.println("\nAdd success");
                System.out.println(new SuiteRoom(inputStream));
            }
        });
    }

    //get the information from the server
    public static void getRoom(final int getRoom){
        connect(new ConnectionListener() {
            @Override
            public void onConnect(InputStream inputStream, OutputStream outputStream) throws IOException {
                outputStream.write(GET_ROOM);
                WriteAndRead.writeInt(outputStream, getRoom);
                int isExist = inputStream.read();
                if (isExist != OKAY && isExist != FAILURE)
                    throw new IOException("something wrong");
                if (isExist == OKAY) {
                    int type = inputStream.read();
                    switch (type){
                        case DINING_ROOM:
                            DiningRoom diningRoom = new DiningRoom(inputStream);
                            System.out.println(diningRoom);
                            break;
                        case STANDARD_ROOM:
                            StandardRoom standardRoom = new StandardRoom(inputStream);
                            System.out.println(standardRoom);
                            break;
                        case SUITE_ROOM:
                            SuiteRoom suiteRoom = new SuiteRoom(inputStream);
                            System.out.println(suiteRoom);
                            break;
                    }
                }else
                    System.out.println("Room is not exist.");
            }
        });
    }

            //client connect to server
    public static void connect(ConnectionListener listener){
        Socket socket = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try{
            socket = new Socket(HOST, PORT);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            listener.onConnect(inputStream, outputStream);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    interface ConnectionListener{
       void onConnect(InputStream inputStream, OutputStream outputStream) throws IOException;
    }
}
