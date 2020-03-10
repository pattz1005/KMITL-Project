package L10_2;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class PostalAddress {
    private String city;
    private String addressOne;
    private String addressTwo;
    private String zipCode;
    private String state;

    public PostalAddress(String c, String a1, String a2, String z, String s){
        this.city = c;
        this.addressOne = a1;
        this.addressTwo = a2;
        this.zipCode = z;
        this.state = s;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddressOne() {
        return addressOne;
    }

    public void setAddressOne(String addressOne) {
        this.addressOne = addressOne;
    }

    public String getAddressTwo() {
        return addressTwo;
    }

    public void setAddressTwo(String addressTwo) {
        this.addressTwo = addressTwo;
    }

    public String getZipCode() {
        return zipCode;
    }

    @Enumerated(EnumType.STRING)
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
