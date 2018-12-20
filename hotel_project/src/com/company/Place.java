package com.company;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class Place implements Writable {
    private static int counter = 0;
    private String name;
    private double area;
    private String phoneNumber;
    private int placeNumber;
    private int numOfBeds;

                //constractor
    public Place(String name, double area, String phoneNumber, int numOfBeds) {
        counter++;
        this.name = name;
        this.area = area;
        this.phoneNumber = phoneNumber;
        this.placeNumber = counter;
        setNumOfBeds(numOfBeds);
    }
    public Place(InputStream inputStream) throws IOException{
        readPlace(inputStream);
        counter++;
        this.placeNumber = counter;
    }

            //get & set
    public String getNameOfPlace() {
        return name;
    }

    public void setNameOfPlace(String nameOfPlace) {
        this.name = nameOfPlace;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getNumOfPlace() {
        return placeNumber;
    }

    public int getNumOfBeds() {
        return numOfBeds;
    }

    public void setNumOfBeds(int numOfBeds) {
        if (numOfBeds >= 0 && numOfBeds <= 5)
            this.numOfBeds = numOfBeds;
    }

            //override to string method
    @Override
    public String toString() {
        return toString(true) + ", Number Of Beds: " + numOfBeds;
    }

    public String toString(boolean b) {
        return "Number Of Place: " + placeNumber + ", " +
                "Name Of place: " + name + ", " +
                "Area: " + area + ", " +
                "Phone Number: " + phoneNumber;
    }

            //override equals method
    @Override
    public boolean equals(Object object) {
       if (this == object)
           return true;
       if (object == null)
           return false;
       if (object instanceof Place){
           Place p = (Place) object;
           return p.area == this.area && p.placeNumber == this.placeNumber && p.numOfBeds == this.numOfBeds &&
                   p.name.equals(this.name) && p.phoneNumber.equals(this.phoneNumber);
       }
       return false;

    }

            //override hashcode method
    @Override
    public int hashCode() {
        return name.hashCode()* 7  ^ phoneNumber.hashCode() * 7 * 7^ (int)(area) *7*7 ^ placeNumber * placeNumber * 57 ^ numOfBeds * 7 * 57 ;
    }


            //write to file and socket
    public void writePlace(OutputStream outputStream) throws IOException {
        WriteAndRead.writeStringShortString(outputStream, name);
        WriteAndRead.writeDouble(outputStream, area);
        WriteAndRead.writeStringShortString(outputStream, phoneNumber);
        WriteAndRead.writeInt(outputStream, placeNumber);
        WriteAndRead.writeInt(outputStream, numOfBeds);
    }

                //read from file and socket
    public void readPlace(InputStream inputStream) throws IOException{
        name = WriteAndRead.readStringShortString(inputStream);
        area = WriteAndRead.readDouble(inputStream);
        phoneNumber = WriteAndRead.readStringShortString(inputStream);
        WriteAndRead.readInt(inputStream);
        setNumOfBeds(WriteAndRead.readInt(inputStream));
    }
}