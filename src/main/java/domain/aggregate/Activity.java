package domain.aggregate;

import domain.entity.City;
import domain.valueObject.ActivityLevel;
import domain.valueObject.ActivityRating;
import domain.valueObject.Address;
import domain.valueObject.Season;

import java.util.Arrays;
import java.util.List;

public class Activity {

    String name;
    String description;
    Address address;
    City associatedCity;
    ActivityRating rating;
    // Location

    ActivityLevel activityLevel; // {"Entspannt", "Erholung", "Aktiv", "Sport"}
    List<Season> seasons;

    public Activity(String name, String description, Address address, ActivityLevel activityLevel, List<Season> seasons) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.activityLevel = activityLevel;
        this.seasons = seasons;
    }

    public Activity(String name, String description, Address address, ActivityLevel activityLevel, List<Season> seasons, City associatedCity) {
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

    public ActivityLevel getActivityLevel() {
        return activityLevel;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Activity:\n" +
                String.format("Name: %s\n", this.name);
    }
}
