package javaTest;

public class Propertises {
    public static void main(String[] args) {
        String osName = System.getProperty("os.name");
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
        System.out.println(osName);
    }
}
