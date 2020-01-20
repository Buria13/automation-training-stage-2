package by.epam.learn.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public static void tree(String str) {
        List<String> stringList = readStringsFromFileToList(str);
        int folders = 0;
        int files = 0;
        double nameLengthOfFiles = 0;

        for (String line : stringList) {
            if (line.contains("|-----")) {
                folders++;
            } else {
                files++;
                nameLengthOfFiles += line.replaceAll("\\|", "").trim().length();
            }
        }

        System.out.println("Number of folders: " + folders);
        System.out.println("Number of files: " + files);
        System.out.println("Average number of files in a folder: "
                + ((double) files) / folders);
        System.out.println("Average length of the file name: "
                + nameLengthOfFiles / files);
    }


    public static void tree(File directory) {
        System.out.println("The result of the program is located in the folder resources/tree.txt");
        reassignSystemOutToFileOutputStream(directory);
    }

    private static void reassignSystemOutToFileOutputStream(File directory) {
        File tree = new File ("src/main/resources/tree.txt");
        try (PrintStream stream = new PrintStream(
                new FileOutputStream(tree))) {

            System.setOut(stream);
            writeTreeToFile(directory, "");

        } catch (FileNotFoundException e) {
            System.out.println("File read error: " + e.getMessage());
        }
    }

    private static void writeTreeToFile(File directory, String indent) {
        File[] files = directory.listFiles();

        if (files != null) {
            for (File i : files) {
                if (i.isFile()) {
                    System.out.println(indent + "|     " + i.getName());
                }
            }

            for (File j : files) {
                if (j.isDirectory()) {
                    System.out.println(indent + "|-----" + j.getName());
                    writeTreeToFile(j, indent + "|     ");
                }
            }
        }
    }

    private static List<String> readStringsFromFileToList(String filePath) {
        List<String> stringsList = new ArrayList<>();
        String line;

        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader(filePath))) {

            while ((line = bufferedReader.readLine()) != null) {
                stringsList.add(line);
            }

        } catch (IOException e) {
            System.out.println("File read error: " + e.getMessage());
        }

        return stringsList;
    }
}
