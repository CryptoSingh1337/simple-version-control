package com.saransh;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author saranshk04
 */
@Getter
@Setter
public class FileSystem {

    private Map<String, File> files;

    public FileSystem() {
        this.files = new HashMap<>();
    }

    public void create(String fileName, String content) {
        files.put(fileName, new File(fileName, content));
    }

    public void appendToAFile(String fileName, String contentToBeAppend) {
        if (files.containsKey(fileName))
            files.get(fileName).append(contentToBeAppend);
    }

    public void trimAFile(String fileName, int n) {
        if (files.containsKey(fileName))
            files.get(fileName).trim(n);
    }

    public void delete(String fileName) {
        this.files.remove(fileName);
    }

    public void displayContents() {
        for (String key : files.keySet())
            System.out.println(files.get(key));
    }
}
