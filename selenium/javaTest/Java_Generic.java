package javaTest;

import java.util.ArrayList;

public class Java_Generic {
    public static void main(String[] args) {
        // Non-generic ( can use any type of data type )
        ArrayList student = new ArrayList<>();
        student.add("Nguyen Van A");
        student.add(9);
        student.add(24.1);
        student.add(true);

        // Generic ( can only add value that the same as the one declared as parameter )
        ArrayList<String> std = new ArrayList<String>();
        std.add("Nguyen Van B");
        std.add("Gollock the Destroyer");
    }
}
