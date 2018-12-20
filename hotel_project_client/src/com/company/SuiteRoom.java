package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SuiteRoom extends Place {
    private int luxuriousDegree;

        //constractor
    public SuiteRoom(String name, double area, String phoneNumber, int numOfBeds, int luxuriousDegree) {
        super(name, area, phoneNumber, numOfBeds);
        setLuxuriousDegree(luxuriousDegree);
    }

    public int getLuxuriousDegree() {
        return luxuriousDegree;
    }

    public void setLuxuriousDegree(int luxuriousDegree) {
        if (luxuriousDegree >= 1 && luxuriousDegree <= 5)
            this.luxuriousDegree = luxuriousDegree;
    }

    //override to string method
    @Override
    public String toString() {
        return super.toString() + ", Luxurious Degree: " + luxuriousDegree;
    }

    //override equals method
    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null)
            return false;
        if (object instanceof SuiteRoom) {
            SuiteRoom s = (SuiteRoom) object;
            return super.equals(s) && s.luxuriousDegree == this.luxuriousDegree;
        }
        return false;
    }

    public SuiteRoom(InputStream inputStream)throws IOException {
        super(inputStream);
        read(inputStream);
    }

    //override hashcode method
    @Override
    public int hashCode() {
        return super.hashCode() * 7 ^ luxuriousDegree * 19 * 17;
    }

    //override write methods from place & WriteAndRead classes
    @Override
    public void write(OutputStream outputStream) throws IOException {
        writePlace(outputStream);
        WriteAndRead.writeInt(outputStream, luxuriousDegree);
    }

    //override read method from WriteAndRead class
    @Override
    public void read(InputStream inputStream) throws IOException {
        luxuriousDegree = WriteAndRead.readInt(inputStream);
    }
}
