package snappii.snappiitest.models;

import org.parceler.Parcel;

@Parcel
public class Name {
    String title;
    String first;
    String last;

    public String getNamePreview() {
        String result = "";
        if (title != null) {
            result = result + title;
        }
        if (first != null) {
            result = result + " " + first;
        }
        if (last != null) {
            result = result + " " + last;
        }
        return result.trim();
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }
}
