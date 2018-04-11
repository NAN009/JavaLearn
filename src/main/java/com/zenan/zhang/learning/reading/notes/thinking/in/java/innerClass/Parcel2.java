package com.zenan.zhang.learning.reading.notes.thinking.in.java.innerClass;

public class Parcel2 {
    class Contents {
        private int i = 11;

        public int value() {
            return i;
        }
    }

    class Destination {
        private String label;

        Destination(String whereTo) {
            label = whereTo;
        }

        String readLable() {
            return label;
        }

    }

    public Destination to(String s) {
        return new Destination(s);
    }

    public Contents contents() {
        return new Contents();
    }

    public void ship(String dest) {
        Contents c = new Contents();
        Destination d = to(dest);
        System.out.println(d.readLable());
    }

    public static void main(String[] args) {
        Parcel2 p = new Parcel2();
        p.ship("ZeNan Zhang");
        Parcel2 q = new Parcel2();
        Parcel2.Contents c = q.contents();
        Parcel2.Destination d = q.to("lalala");
    }
}
