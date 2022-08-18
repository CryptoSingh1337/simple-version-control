package com.saransh;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author saranshk04
 */
@Getter
@Setter
@AllArgsConstructor
public class Diff {

    private String fileName;
    private OperationType operationType;
    private String contentChange;

    public static Diff create(File oldFile, File newFile) {
        if (oldFile == null)
            return new Diff(newFile.getName(), OperationType.CREATE, newFile.getContent());
        else if (newFile == null)
            return new Diff(oldFile.getName(), OperationType.DELETE, oldFile.getContent());
        else if (oldFile.getContent().length() < newFile.getContent().length()) {
            String change = newFile.getContent().substring(oldFile.getContent().length());
            return new Diff(newFile.getName(), OperationType.APPEND, change);
        } else if (oldFile.getContent().length() > newFile.getContent().length()) {
            String change = oldFile.getContent().substring(newFile.getContent().length());
            return new Diff(oldFile.getName(), OperationType.TRIM, change);
        } else if (oldFile.getContent().length() == newFile.getContent().length()) {
            if (oldFile.getContent().equals(newFile.getContent()))
                return new Diff(oldFile.getName(), OperationType.NO_CHANGE, "");
            return new Diff(newFile.getName(), OperationType.APPEND, newFile.getContent());
        }
        return null;
    }

    @Override
    public String toString() {
        String change = switch (operationType) {
            case CREATE, APPEND -> "+" + this.contentChange;
            case DELETE, TRIM -> "-" + this.contentChange;
            case NO_CHANGE -> this.contentChange;
        };
        return String.format("FileName: %s\n%s\n%s", this.fileName, this.operationType, change);
    }
}
