package javaTest;

import java.util.ArrayList;
import java.util.List;

public class For {
    public static void main(String[] args) {
        // 100 numbers
        int number = 100;
        for (int i = 0; i < number; i++){
            if (i == 50){
                System.out.println(i);
            }
        }

        List<String> students = new ArrayList<String>();
        students.add("Vu Trong Bach");
        students.add("Yu Trump Beck");
        students.add("Zu Zam Zack");

        // Classic
        for (int i = 0; i < students.size() ; i++) {
            System.out.println(students.get(i));
        }

        // Advanced/ For each
        for (String student : students){
            System.out.println(student);
        }
    }
}
