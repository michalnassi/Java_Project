package com.company;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.Map;

public class WriteAndRead {
    public static final int DINING_ROOM = 1;
    public static final int STANDARD_ROOM = 2;
    public static final int SUITE_ROOM = 3;

            // write int to file and socket method
    public static void writeInt(OutputStream outputStream, int x) throws IOException {

        byte[] bytes = new byte[4];
        ByteBuffer.wrap(bytes).putInt(x);
        outputStream.write(bytes);

    }

            // read int from file and socket method
    public static int readInt(InputStream inputStream) throws IOException {
        byte[] bytes = new byte[4];
        int actuallyRead = inputStream.read(bytes);
        if (actuallyRead != 4)
            throw new IOException("Not 4 bytes for int");
        int x = ByteBuffer.wrap(bytes).getInt();
        return x;
    }

            // write short string to file and socket method
    public static void writeStringShortString(OutputStream outputStream, String string) throws IOException {
        outputStream.write(string.length());
        outputStream.write(string.getBytes());
    }
           // read short string from file and socket method
    public static String readStringShortString(InputStream inputStream) throws IOException {
        int stringLength = inputStream.read();
        if (stringLength == -1)
            throw new IOException("it have to get some string!!");
        byte[] buffer = new byte[stringLength];
        int actuallyRead = inputStream.read(buffer);
        if (actuallyRead != stringLength)
            throw new IOException("Wrong length of string");
        return new String(buffer);

    }

            //write double to file and socket method
    public static void writeDouble(OutputStream outputStream, double x) throws IOException {
        byte[] bytes = new byte[8];
        ByteBuffer.wrap(bytes).putDouble(x);
        outputStream.write(bytes);
    }

            //read double from file and socket method
    public static double readDouble(InputStream inputStream) throws IOException {
        byte[] bytes = new byte[8];
        int actuallyRead = inputStream.read(bytes);
        if (actuallyRead != 8)
            throw new IOException("Not 8 bytes for Double");
        double x = ByteBuffer.wrap(bytes).getDouble();
        return x;
    }

                //write type bed to file and socket method
    public static void writeTypeBed(OutputStream outputStream, TypeBed typeBed) throws IOException {
        int type = 0;
        switch (typeBed) {
            case SINGLE:
                type = 1;
                break;
            case DOUBLE:
                type = 2;
                break;
            case TRIPLE:
                type = 3;
                break;
            case KING_SIZE:
                type = 4;
                break;
        }
        writeInt(outputStream, type);
    }

                //read type bed from file and socket method
    public static TypeBed readTypeBed(InputStream inputStream) throws IOException {
        int type = readInt(inputStream);
        if (type < 1 || type > 4)
            throw new IOException();
        switch (type) {
            case 1:
                return TypeBed.SINGLE;
            case 2:
                return TypeBed.DOUBLE;
            case 3:
                return TypeBed.TRIPLE;
            case 4:
                return TypeBed.KING_SIZE;
        }
        return null;
    }

            //write direction to file and socket method
    public static void writeDirection(OutputStream outputStream, Direction direction) throws IOException {
        int type = 0;
        switch (direction) {
            case WEST:
                type = 1;
                break;
            case NORTH:
                type = 2;
                break;
            case SOUTH:
                type = 3;
                break;
            case EAST:
                type = 4;
                break;
            case SEA_SIDE:
                type = 5;
                break;
        }
        writeInt(outputStream, type);
    }

                //read direction from file and socket method
    public static Direction readDirection(InputStream inputStream) throws IOException {
        int type = readInt(inputStream);
        if (type < 1 || type > 5)
            throw new IOException();
        switch (type) {
            case 1:
                return Direction.WEST;
            case 2:
                return Direction.NORTH;
            case 3:
                return Direction.SOUTH;
            case 4:
                return Direction.EAST;
            case 5:
                return Direction.SEA_SIDE;
        }
        return null;
    }

                //read all information from file
    public static void readPlaceFromFile(Map<Integer, Place> placeMap, File file) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            int type;
            while ((type = readInt(inputStream)) !=  -1){
                switch (type) {
                    case DINING_ROOM:
                        DiningRoom diningRoom = new DiningRoom(inputStream);
                        placeMap.put(diningRoom.getNumOfPlace(), diningRoom);
                        break;
                    case STANDARD_ROOM:
                        StandardRoom standardRoom = new StandardRoom(inputStream);
                        placeMap.put(standardRoom.getNumOfPlace(), standardRoom);
                        break;
                    case SUITE_ROOM:
                        SuiteRoom suiteRoom = new SuiteRoom(inputStream);
                        placeMap.put(suiteRoom.getNumOfPlace(), suiteRoom);
                        break;
                }
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
        }
    }

    //write all information to file
    public static void writePlaceToFile(Map<Integer, Place> placeMap,File file) {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            for (Map.Entry<Integer, Place> entry : placeMap.entrySet()) {
                Place  place = entry.getValue();
                if (place instanceof DiningRoom){
                    DiningRoom diningRoom = (DiningRoom)place;
                    writeInt(outputStream, DINING_ROOM);
                    diningRoom.write(outputStream);
                }
                if (place instanceof StandardRoom){
                    StandardRoom standardRoom = (StandardRoom) place;
                    writeInt(outputStream, STANDARD_ROOM);
                    standardRoom.write(outputStream);
                }
                if (place instanceof SuiteRoom){
                    SuiteRoom suiteRoom = (SuiteRoom) place;
                    writeInt(outputStream, SUITE_ROOM);
                    suiteRoom.write(outputStream);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
