package ch.gauthey.alain.common.files;

import java.net.URI;

public class File extends java.io.File {

    private static final long serialVersionUID = -2548683020392251201L;
    private Boolean isExecutable=false;

    public File(String pathname) {
        super(pathname);
    }

    public File(URI uri) {
        super(uri);
    }

    public File(String parent, String child) {
        super(parent, child);
    }

    public File(java.io.File parent, String child) {
        super(parent, child);
    }

    public Boolean isExecutable() {
        return isExecutable;
    }

    public void setIsExecutable(Boolean isExecutable) {
        this.isExecutable = isExecutable;
    }

}
