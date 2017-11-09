package snappii.snappiitest.models;

import org.parceler.Parcel;

@Parcel
public class User {
    public static final String KEY = "User";
    Name name;
    Picture picture;
    Address location;
    String email;
    String phone;
    String nat;

    public String getNamePreview() {
        if (name != null)
            return name.getNamePreview();
        return null;
    }

    public String getName() {
        if (name != null)
            return name.getFirst();
        return null;
    }

    public String getSurname() {
        if (name != null)
            return name.getLast();
        return null;
    }

    public String getAvatarPreview() {
        if (picture != null)
            return picture.thumbnail;
        return null;
    }

    public String getAddressPreview() {
        if (location != null)
            return location.geAddressPreview();
        return null;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getNationality() {
        return nat;
    }
}
