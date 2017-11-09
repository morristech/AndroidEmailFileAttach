package snappii.snappiitest.models;

import org.parceler.Parcel;

@Parcel
public class Address {
    String street;
    String city;
    String state;
    String postcode;

    public String geAddressPreview() {
        String result = "";
        if (postcode != null) {
            result = result + postcode;
        }
        if (state != null) {
            result = result + " " + state;
        }
        if (city != null) {
            result = result + " " + city;
        }
        if (street != null) {
            result = result + " " + street;
        }
        return result.trim();
    }
}
