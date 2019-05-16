package com.aca.filedb;

import com.aca.filedb.filter.FilterByKey;
import com.aca.filedb.filter.FilterByValue;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class StringStringRepository implements Repository<String, String> {

    private static final String KEY_VALUE_DIVIDER = " - ";
    private static final String LINE_DIVIDER = "\n";

    private Scanner scanner;
    private FileWriter fileWriter;
    private String filePath;

    StringStringRepository(String filePath) {
        this.filePath = filePath;
    }

    public void open() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        fileWriter = new FileWriter(file, true);
        scanner = new Scanner(file);
    }

    @Override
    public void put(String key, String value) throws IOException {
        String s = key + KEY_VALUE_DIVIDER + value + LINE_DIVIDER;
        fileWriter.write(s);
        fileWriter.flush();
    }

    @Override
    public String get(String key) {
        while (scanner.hasNextLine()) {
            String next = scanner.nextLine();
            String[] split = next.split(KEY_VALUE_DIVIDER);
            if (split[0].equals(key)) {
                return split[1];
            }
        }
        return null;
    }

    @Override
    public List<String> getAll() {
        List<String> list = new LinkedList<>();
        while (scanner.hasNextLine()) {
            list.add(scanner.nextLine().split(KEY_VALUE_DIVIDER)[1]);
        }
        return list;
    }

    @Override
    public List<String> get(FilterByKey<String> filter) throws IOException {
        List<String> list = new LinkedList<>();
        while (scanner.hasNextLine()) {
            String[] split = scanner.nextLine().split(KEY_VALUE_DIVIDER);
            if (filter == null || filter.filter(split[0])) {
                list.add(split[1]);
            }
        }
        return list;
    }

    @Override
    public List<String> get(FilterByValue<String> filter) throws IOException {
        List<String> list = new LinkedList<>();
        while (scanner.hasNextLine()) {
            String[] split = scanner.nextLine().split(KEY_VALUE_DIVIDER);
            String value = split[1];
            if (filter == null || filter.filter(value)) {
                list.add(value);
            }
        }
        return list;
    }

    @Override
    public void close() throws IOException {
        fileWriter.close();
        scanner.close();
    }
}
