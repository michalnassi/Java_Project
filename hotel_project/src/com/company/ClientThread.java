package com.company;

import com.sun.org.apache.bcel.internal.generic.FLOAD;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;

public class ClientThread extends Thread {
    public static final int ADD_DINING_ROOM = 100;
    public static final int ADD_STANDARD_ROOM = 101;
    public static final int ADD_SUITE_ROOM = 102;
    public static final int GET_ROOM = 103;
    public static final int OKAY = 200;
    public static final int FAILURE = 201;
    public static final int DINING_ROOM = 55;
    public static final int STANDARD_ROOM = 56;
    public static final int SUITE_ROOM = 57;
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private Map<Integer, Place> rooms;
    private File file;
        //constractor
    public ClientThread(Socket socket, Map<Integer, Place> rooms, File file) {
        this.socket = socket;
        this.rooms = rooms;
        this.file = file;
    }
    // actions
    @Override
    public void run() {
        try{
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            int action = inputStream.read();
            switch (action){
                case ADD_DINING_ROOM:
                    addDiningRoom();
                    break;
                case ADD_STANDARD_ROOM:
                    addStandardRoom();
                    break;
                case ADD_SUITE_ROOM:
                    addSuiteRoom();
                    break;
                case GET_ROOM:
                    getRoom();
                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

            //add dining room method, and write to file
    private void addDiningRoom() throws IOException{
        DiningRoom diningRoom = new DiningRoom(inputStream);
        rooms.put(diningRoom.getNumOfPlace(), diningRoom);
        outputStream.write(OKAY);
        diningRoom.write(outputStream);
        WriteAndRead.writePlaceToFile(rooms, file);

    }
            //add standard room method and write to file
    private  void  addStandardRoom()throws IOException{
        StandardRoom standardRoom = new StandardRoom(inputStream);
        rooms.put(standardRoom.getNumOfPlace(), standardRoom);
        outputStream.write(OKAY);
        standardRoom.write(outputStream);
        WriteAndRead.writePlaceToFile(rooms, file);
    }
            //add suite room and write to file
    private  void addSuiteRoom() throws IOException{
        SuiteRoom suiteRoom = new SuiteRoom(inputStream);
        rooms.put(suiteRoom.getNumOfPlace(), suiteRoom);
        outputStream.write(OKAY);
        suiteRoom.write(outputStream);
        WriteAndRead.writePlaceToFile(rooms, file);
    }
        // receive information by room number
    private  void getRoom() throws  IOException{
        int getRoom = WriteAndRead.readInt(inputStream);
        Place place = rooms.get(getRoom);
        if (place == null) {
            outputStream.write(FAILURE);
        }
        else {
            outputStream.write(OKAY);
            if (place instanceof DiningRoom){
                outputStream.write(DINING_ROOM);
            }else
                if (place instanceof StandardRoom){
                    outputStream.write(STANDARD_ROOM);
                }else{
                    outputStream.write(SUITE_ROOM);
                }
            place.write(outputStream);
        }
    }

}
