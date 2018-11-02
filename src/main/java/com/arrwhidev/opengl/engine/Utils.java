package com.arrwhidev.opengl.engine;

import java.io.InputStream;
import java.util.Scanner;

public class Utils {

    public static String loadResource(String fileName) {
        try {
            String result;
            try (InputStream in = Class.forName(Utils.class.getName()).getResourceAsStream(fileName);
                 Scanner scanner = new Scanner(in, "UTF-8")) {
                 result = scanner.useDelimiter("\\A").next();
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}