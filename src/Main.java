import controller.LzwDecompressor;

import java.io.*;

public class Main {

    public static void main(String[] args) {

        new LzwDecompressor("test.txt").run();

        //byte b = (byte) 'î';
        //byte b2 = (byte) 140;

        //System.out.println(b);
        //System.out.println(b2);

        /*
        int ih1 = 53;
        int ih2 = 48;
        int ih3 = 48;
        int ih4 = 10;
        int i1 = 97;
        int i2 = 49;
        int i3 = 28;
        int i4 = 140;
        int i5 = 38;
        int i6 = 49;
        int i7 = 136;
        int i8 = 201;
        int i9 = 0;
        int i10 = 128;
        int i11 = 128;


        try {
            DataOutputStream writer = new DataOutputStream(new FileOutputStream("test.txt"));
            writer.write(ih1);
            writer.write(ih2);
            writer.write(ih3);
            writer.write(ih4);

            writer.write(i1);
            writer.write(i2);
            writer.write(i3);
            writer.write(i4);
            writer.write(i5);
            writer.write(i6);
            writer.write(i7);
            writer.write(i8);
            writer.write(i9);
            writer.write(i10);
            writer.write(i11);


        } catch (IOException e) {
            e.printStackTrace();
        }
        */


        /*
        byte ih1 = 53;
        byte ih2 = 48;
        byte ih3 = 48;
        byte ih4 = 10;
        byte i1 = 97;
        byte i2 = 98;
        byte i3 = 57;
        byte i4 = 24;
        byte i5 = 76;
        byte i6 = 102;
        byte i7 = 19;
        byte i8 = 36;
        byte i9 = 2;
        byte i10 = 4;
        //byte i11 = (byte) 128;


        try {
            DataOutputStream writer = new DataOutputStream(new FileOutputStream("test.txt"));
            writer.writeByte(ih1);
            writer.writeByte(ih2);
            writer.writeByte(ih3);
            writer.writeByte(ih4);

            writer.writeByte(i1);
            writer.writeByte(i2);
            writer.writeByte(i3);
            writer.writeByte(i4);
            writer.writeByte(i5);
            writer.writeByte(i6);
            writer.writeByte(i7);
            writer.writeByte(i8);
            writer.writeByte(i9);
            writer.writeByte(i10);
            //writer.writeByte(i11);


        } catch (IOException e) {
            e.printStackTrace();
        }


        */



    }
}
