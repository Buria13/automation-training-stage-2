package by.epam.learn.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IoDemo {

    public static void main(String[] args) {
        String str = args[0];
        File file = new File(str);

        if (file.isDirectory()) {
            FileManager.tree(file);
        } else {
            FileManager.tree(str);
        }
    }

}

