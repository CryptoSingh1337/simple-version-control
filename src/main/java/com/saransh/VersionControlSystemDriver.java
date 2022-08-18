package com.saransh;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class VersionControlSystemDriver {

    private final Map<Integer, Commit> commits;
    private static int currentCommitId = 1;

    public VersionControlSystemDriver() {
        this.commits = new HashMap<>();
    }

    public Commit addCommit(String message, FileSystem currentFileSystem) {
        if (commits.isEmpty()) {
            Commit commit = new Commit(currentCommitId, message, null, currentFileSystem);
            commits.put(currentCommitId, commit);
            currentCommitId++;
            return commit;
        }
        FileSystem previousFileSystem = createFileSystem(currentCommitId - 1);
        Commit commit = new Commit(currentCommitId, message, previousFileSystem, currentFileSystem);
        commits.put(currentCommitId, commit);
        currentCommitId++;
        return commit;
    }

    public FileSystem checkout(int commitId) {
        return createFileSystem(commitId);
    }

    public void status() {
        commits.get(currentCommitId - 1).display();
    }

    public void log(int n) {
        if (n == -1) {
            for (int i = currentCommitId - 1; i >= 1; i--) {
                System.out.println();
                commits.get(i).display();
            }
            return;
        }
        for (int i = currentCommitId - 1; i >= currentCommitId - n; i--) {
            System.out.println();
            commits.get(i).display();
        }
    }

    private FileSystem createFileSystem(int commitId) {
        FileSystem fs = new FileSystem();
        for (int i = 1; i <= commitId; i++) {
            Commit commit = commits.get(i);
            for (Diff diff : commit.getDiffs()) {
                switch (diff.getOperationType()) {
                    case CREATE -> fs.create(diff.getFileName(), diff.getContentChange());
                    case APPEND -> fs.appendToAFile(diff.getFileName(), diff.getContentChange());
                    case TRIM -> fs.trimAFile(diff.getFileName(), diff.getContentChange().length());
                    case DELETE -> fs.delete(diff.getFileName());
                }
            }
        }
        return fs;
    }

    public static void main(String[] args) {
        VersionControlSystemDriver vcs = new VersionControlSystemDriver();
        FileSystem fileSystem = new FileSystem();

        System.out.println("\n\nTask - 1 : Create a File-1, File-2");
        fileSystem.create("File-1", "This is the file one.");
        fileSystem.create("File-2", "This is the file two.");
        Commit commit1 = vcs.addCommit("Create File-1, File-2", fileSystem);
        commit1.display();

        System.out.println("\nFile directory content - After 1st Commit");
        fileSystem.displayContents();

        System.out.println("\n\nTask - 2 : Create File-3, append to File-2 and trim last 4 characters of File-1");
        fileSystem.create("File-3", "This is the file three.");
        fileSystem.trimAFile("File-1", 4);
        fileSystem.appendToAFile("File-2", "This is the appended text");
        Commit commit2 = vcs.addCommit("Create File-3, append to File-2 and trim last 4 characters of File-1", fileSystem);
        commit2.display();

        System.out.println("\nFile directory content - After 2nd Commit");
        fileSystem.displayContents();

        System.out.println("\n\nTask - 3 : Delete File-1 and append to File-3");
        fileSystem.delete("File-1");
        fileSystem.appendToAFile("File-3", "Append text");
        Commit commit3 = vcs.addCommit("Delete File-1 and append to File-3", fileSystem);
        commit3.display();
        System.out.println("\nFile directory content - After 3rd Commit");
        fileSystem.displayContents();

        System.out.println("\n-----------Status-----------");
        vcs.status();

        System.out.println("\n-----------Checkout-----------");
        FileSystem fs = vcs.checkout(commit2.getId());
        System.out.println("\nContents of the File System on commit - 2");
        fs.displayContents();

        fs = vcs.checkout(commit3.getId());
        System.out.println("\nContents of the File System on commit - 3");
        fs.displayContents();

        System.out.println("\n\nTask - 4 : Create new files only and usage of log");
        fileSystem.create("File-4", "This is file four");
        fileSystem.create("File-5", "This is file five");
        Commit commit4 = vcs.addCommit("Create new files only and usage of log", fileSystem);
        commit4.display();
        System.out.println("\nFile directory content - After 4rd Commit");
        fileSystem.displayContents();

        System.out.println("\n-----------Log-----------");
        vcs.log(-1);

        System.out.println("\n-----------Checkout-----------");
        for (int i = 1; i < currentCommitId; i++) {
            fs = vcs.checkout(i);
            System.out.println("\nCommit message: " + vcs.getCommits().get(i).getMessage());
            System.out.println("Contents of the File System on commit - " + i);
            fs.displayContents();
        }
    }
}