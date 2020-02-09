package data;

public class File {

    public final String format;
    public final String location;

    public File(String file) {
        int index = file.indexOf(':');
        this.format = file.substring(0, index);
        this.location = file.substring(index + 2, file.length() - 1);
    }

}
