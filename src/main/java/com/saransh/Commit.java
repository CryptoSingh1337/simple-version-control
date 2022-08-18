package com.saransh;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author saranshk04
 */
@Getter
public class Commit {

    private final int id;
    private final String message;
    private final List<Diff> diffs;

    public Commit(int id, String message, FileSystem previousFileSystem, FileSystem currentFileSystem) {
        this.id = id;
        this.message = message;
        this.diffs = new ArrayList<>();
        initializeDiffs(previousFileSystem, currentFileSystem);
    }

    private void initializeDiffs(FileSystem previousFileSystem, FileSystem currentFileSystem) {
        if (previousFileSystem == null) {
            Map<String, File> files = currentFileSystem.getFiles();
            for (String fileName : files.keySet())
                diffs.add(Diff.create(null, files.get(fileName)));
        } else {
            Map<String, File> previousFiles = previousFileSystem.getFiles();
            Map<String, File> currentFiles = currentFileSystem.getFiles();

            for (String fileName : previousFiles.keySet()) {
                diffs.add(Diff.create(previousFiles.get(fileName),
                        currentFiles.getOrDefault(fileName, null)));
            }

            for (String fileName : currentFiles.keySet())
                if (!previousFiles.containsKey(fileName))
                    diffs.add(Diff.create(null, currentFiles.get(fileName)));
        }
    }

    public void display() {
        String format = """
                CommitId: %d
                Message: %s
                Diff:
                """;
        System.out.printf(format, this.id, this.message);
        for (Diff diff : diffs)
            if (diff.getOperationType() != OperationType.NO_CHANGE)
                System.out.println(diff);
    }
}
