package data;

public class DataSet {

    private final Profile[] profiles;

    public DataSet(Profile[] profiles) {
        this.profiles = profiles;
    }


    public int size() {
        return profiles.length;
    }

    public Profile get(int i) {
        return profiles[i];
    }

}
