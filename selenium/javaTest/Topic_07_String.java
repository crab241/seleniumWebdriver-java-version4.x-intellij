package javaTest;

import org.testng.annotations.Test;

public class Topic_07_String {

    @Test
    public void TC_01(){
        String text = "Convert RGBA color value.";

        // Kieerm tra chuỗi / Check string
        // Tuyệt đối phân biệt hoa thường
        System.out.println(text.equals("Convert RGBA color value."));
        System.out.println(text.equals("Convert RGBA color value.."));

        // Tuyệt đối k phân biệt hoa thường ( Tương đối )
        System.out.println(text.equalsIgnoreCase("convert rgba color value."));
        System.out.println(text.contains("Convert RGBA"));
        System.out.println(text.contains("color"));
        System.out.println(text.contains("value"));

        System.out.println(text.startsWith("Convert"));
        System.out.println(text.startsWith("Convert RGBA"));

        System.out.println(text.endsWith("value."));
        System.out.println(text.endsWith("color value."));


        // Xử lí chuỗi
        String[] textArray = text.split("RGBA");

        for (String temp : textArray) {
            System.out.println(temp);
        }

        // Gộp chung với 1 String khác
        System.out.println(text.concat("format with alpha channel support"));

        System.out.println(text.toLowerCase());
        System.out.println(text.toUpperCase());

        // Print chữ cái tại vị trí index
        System.out.println(text.charAt(0));
        System.out.println(text.charAt(1));
        System.out.println(text.charAt(10));
        System.out.println(text.charAt(text.length() - 1));
        System.out.println(text.length());

        // Similar to equals function
        System.out.println(text.compareTo("Convert RGBA color value."));

        // Trả về index của kí tự
        System.out.println(text.indexOf("R"));

        System.out.println(text.isBlank());
        System.out.println(text.isEmpty());

        // Thay thế giá trị target với giá trị replacement
        System.out.println(text.replace("RGBA", "Hexa"));

        text = "          \nConvert RGBA color value.\n         ";
        System.out.println(text);
        System.out.println(text.length());
        System.out.println(text.trim().length());

    }
}
