package com.company;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DiningRoom extends Place {
    private int maxPeoples;
            //constractor
    public DiningRoom(String name, double area, String phoneNumber,int maxPeoples) {
        super(name, area, phoneNumber, 0);
        this.maxPeoples = maxPeoples;
    }
    public DiningRoom(InputStream inputStream) throws IOException{
        super(inputStream);
        read(inputStream);
    }

            //get & set
    public int getMaxPeoples() {
        return maxPeoples;
    }

    public void setMaxPeoples(int maxPeoples) {
        this.maxPeoples = maxPeoples;
    }


            //override to string method
    @Override
    public String toString() {
        return super.toString() + ", Maximum peoples: " + maxPeoples;
    }

                //override equals method
    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
       if (object == null)
            return false;
        if (object instanceof DiningRoom) {
            DiningRoom d = (DiningRoom) object;
            return super.equals(d) && d.maxPeoples == this.maxPeoples;
        }
        return false;
    }

                //override hashcode method
    @Override
    public int hashCode() {
        return super.hashCode() * 3 * 3 ^ maxPeoples * maxPeoples * 17;
    }


            //override write methods from place & WriteAndRead classes
    @Override
    public void write(OutputStream outputStream) throws IOException {
        writePlace(outputStream);
        WriteAndRead.writeInt(outputStream, maxPeoples);
    }
            //override read method from WriteAndRead class
    @Override
    public void read(InputStream inputStream) throws IOException {
        maxPeoples = WriteAndRead.readInt(inputStream);
    }
}
