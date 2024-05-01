package domain.valueObject;

import java.util.Objects;

public final class Address {

    private final String street;
    private final String houseNumber;
    private final String zipCode;
    private final String city;


    public Address(String street, String houseNumber, String zipCode, String city) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.city = city;
    }

    public String toString() {
        return String.format("%s %s\n %s, %s", street, houseNumber, zipCode, city);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return street.equals(address.street) && houseNumber.equals(address.houseNumber) && zipCode.equals(address.zipCode) && city.equals(address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, houseNumber, zipCode, city);
    }
}
