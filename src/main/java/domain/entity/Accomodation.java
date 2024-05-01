package domain.entity;

import domain.entity.auxilliary.FoodOptions;
import domain.valueObject.Address;
import domain.valueObject.RatingType;

public class Accomodation {

    private String name;
    private FoodOptions foodOption;
    private RatingType rating;
    private Address address;

    public Accomodation(String name, FoodOptions foodOption, RatingType rating, Address address) {
        this.name = name;
        this.foodOption = foodOption;
        this.rating = rating;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public FoodOptions getFoodOption() {
        return foodOption;
    }

    public RatingType getRating() {
        return rating;
    }

    public Address getAddress() {
        return address;
    }
}
