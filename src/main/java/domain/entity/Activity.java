package domain.entity;

import domain.valueObject.ActivityLevel;
import domain.valueObject.Address;
import domain.valueObject.Season;

import java.util.Arrays;

public class Activity {

    String name;
    String description;
    Address address;
    City associatedCity;
    // Location

    ActivityLevel activityLevel; // {"Entspannt", "Erholung", "Aktiv", "Sport"}
    Season[] seasons;

    public Activity(String name, String description, Address address, ActivityLevel activityLevel, Season[] seasons) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.activityLevel = activityLevel;
        this.seasons = seasons;
    }

    public Activity(String name, String description, Address address, ActivityLevel activityLevel, Season[] seasons, City associatedCity) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.activityLevel = activityLevel;
        this.seasons = seasons;
        this.associatedCity = associatedCity;

    }

    public City getAssociatedCity() {
        return associatedCity;
    }

    public void setAssociatedCity(City newAssociatedCity) {
        this.associatedCity = newAssociatedCity;
    }

    @Override
    public String toString() {
        return "Activity:\n" +
                String.format("Name: %s\n", this.name);
    }
}
