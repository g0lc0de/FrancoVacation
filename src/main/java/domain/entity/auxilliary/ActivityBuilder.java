package domain.entity.auxilliary;

import domain.entity.Activity;
import domain.entity.City;
import domain.valueObject.ActivityLevel;
import domain.valueObject.Address;
import domain.valueObject.Season;

public class ActivityBuilder implements Builder {

    private String name;
    private String description;
    private ActivityLevel activityLevel;
    private Address address;
    private Season[] seasons;
    private City associatedCity;

    public ActivityBuilder() {

    }

    public ActivityBuilder buildActivityDetails(String name, String description) {
        this.name = name;
        this.description = description;
        return this;
    }

    public ActivityBuilder buildActivityLevel(ActivityLevel activityLevel) {
        this.activityLevel = activityLevel;
        return this;
    }

    public ActivityBuilder buildActivityAddress(Address address) {
        this.address = address;
        return this;
    }

    public ActivityBuilder buildSeasons(Season[] seasons) {
        this.seasons = seasons;
        return this;
    }

    public ActivityBuilder buildAssociatedCity(City associatedCity) {
        this.associatedCity = associatedCity;
        return  this;
    }

    @Override
    public Activity build() {

        if (null==associatedCity) {
            return new Activity(name, description, address, activityLevel, seasons);
        } else {
            return new Activity(name, description, address, activityLevel, seasons);
        }

    }

}
