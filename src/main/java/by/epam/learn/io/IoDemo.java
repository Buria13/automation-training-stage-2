package by.epam.learn.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IoDemo {

    public static void main(String[] args) {
        String str = args[0];
        File file = new File(str);

        if (file.isDirectory()) {
            tree(file);
        } else {
            tree(str);
        }
    }

    private static void tree(String str) {
        System.out.println("второй трее");
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

        System.out.println("Количество папок: " + folders);
        System.out.println("Количество файлов: " + files);
        System.out.println("Среднее количество файлов в папке: "
                + ((double) files) / folders);
        System.out.println("Средняя длина названия файла: "
                + nameLengthOfFiles / files);
    }


    private static void tree(File directory) {
        System.out.println("Результат программы находиться " +
                "в текущей дериктории resources/tree.txt");
        reassignSystemOutToFileOutputStream(directory);
    }

    private static void reassignSystemOutToFileOutputStream(File directory) {
        File tree = new File ("src/main/java/by/epam/learn/io/resources/tree.txt");
        try (PrintStream stream = new PrintStream(
                new FileOutputStream(tree))) {

            System.setOut(stream);
            writeTreeToFile(directory, "");

        } catch (FileNotFoundException e) {
            System.out.println("Ошибка чтения файла");
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
            System.out.println("Ошибка чтения файла: " + e);
        }

        return stringsList;
    }
}

