package ch.gauthey.alain.bean;

import java.io.Serializable;

public class PatternFiles implements Serializable {
    private static final long serialVersionUID = 2947071558366741715L;
    private String id;
    private String fileName;
    private String description;
    private String content;
    private Boolean executable;

    public PatternFiles(String id, String fileName, String description, Boolean executable, String content) {
        this.id = id;
        this.fileName = fileName;
        this.description = description;
        this.content = content;
        this.executable = executable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getExecutable() {
        return executable;
    }

    public void setExecutable(Boolean executable) {
        this.executable = executable;
    }
}
