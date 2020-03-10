package proxy;

import java.util.Date;

public class Client {

    private static String fileName;
    private final static int COUNT = 10000;

    public static void main(String[] args) {

        // Determine file name for properties file.
        String sep = System.getProperty("file.separator");
        if (sep.equals("/")) {
            // assume we are on Unix.
            fileName = "/tmp/key_values";
        }
        else {
            // Assume we are on Windows.
            fileName = "C:\\Users\\viray\\Documents\\Projects\\SDA Lab 12\\data\\data.properties";
        }

        System.out.println("Notice that file " + fileName + " is used to store key-value pairs.\n");

        // Performance test.
        AbstractMap map;
        String key = "name";
        String value = "Eric Dubuis";

        try {
            System.out.println("Accessing " + COUNT + " times a Map object:");
            map = new Map(fileName);
            map.add("name", value);
            System.out.println("  Starting loop at: " + new Date().toString());
            for (int i = 0; i < COUNT; i++) {
                map.find("name");
            }
            System.out.println("  Finished loop at: " + new Date().toString() + "\n");

            System.out.println("Accessing " + COUNT + " times a Map proxy object:");
            map = new MapProxy(fileName);
            map.add("name", value);
            System.out.println("  Starting loop at: " + new Date().toString());
            for (int i = 0; i < COUNT; i++) {
                map.find("name");
            }
            System.out.println("  Finished loop at: " + new Date().toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
