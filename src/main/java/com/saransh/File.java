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
public class File {

    private String name;
    private String content;

    public void append(String appendContentToEnd) {
        this.content += appendContentToEnd;
    }

    public void trim(int n) {
        this.content = this.content.substring(0, this.content.length() - n);
    }

    @Override
    public String toString() {
        return String.format("%s\n%s", this.name, this.content);
    }
}