package com.company;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

        //enums objects
enum TypeBed{
    SINGLE, DOUBLE, TRIPLE, KING_SIZE
}
enum Direction{
    WEST, NORTH, SOUTH, EAST, SEA_SIDE,
}

public class StandardRoom extends Place{

    private TypeBed typeBed;
    private  Direction direction;
            //constractor
    public StandardRoom(String name, double area, String phoneNumber, int numOfBeds, TypeBed typeBed, Direction direction) {
        super(name, area, phoneNumber, numOfBeds);
        this.typeBed = typeBed;
        this.direction = direction;
    }
    public StandardRoom(InputStream inputStream)throws IOException{

        super(inputStream);
        read(inputStream);
    }
            //get & set
    public TypeBed getTypeBed() {
        return typeBed;
    }

    public void setTypeBed(TypeBed typeBed) {
        this.typeBed = typeBed;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    //override to string method
    @Override
    public String toString() {
        return super.toString() + ", Type of bed: " + typeBed + ", Direction: " + direction;
    }

    //override equals method
    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null)
            return false;
        if (object instanceof StandardRoom) {
            StandardRoom s = (StandardRoom) object;
            return super.equals(s) && s.typeBed == this.typeBed && s.direction == this.direction;
        }
        return false;
    }

    //override hashcode method
    @Override
    public int hashCode() {
        return super.hashCode() * 7 ^ typeBed.hashCode() * 7 * 17 ^ direction.hashCode() * 29 * 17;
    }

    //override write methods from place & WriteAndRead classes

    @Override
    public void write(OutputStream outputStream) throws IOException {
        writePlace(outputStream);
        WriteAndRead.writeTypeBed(outputStream, typeBed);
        WriteAndRead.writeDirection(outputStream, direction);
    }

    //override read method from WriteAndRead class
    @Override
    public void read(InputStream inputStream) throws IOException {
        typeBed = WriteAndRead.readTypeBed(inputStream);
        direction =  WriteAndRead.readDirection(inputStream);
    }


}
