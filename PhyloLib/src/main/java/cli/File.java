package cli;

public class File {

    public final String format;
    public final String location;

    public File(String file) {
        String[] values = file.split(":", 2);
        this.format = values[0];
        this.location = values[1];
    }

}
