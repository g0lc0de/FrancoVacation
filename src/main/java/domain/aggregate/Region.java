package domain.aggregate;

import domain.entity.Activity;
import domain.entity.City;
import domain.entity.Country;
import domain.entity.Entity;
import domain.entity.auxilliary.PhysicalLocation;

import java.util.ArrayList;
import java.util.List;

public class Region implements PhysicalLocation, Entity {

    private String name;
    private List<Country> countries;

    private final List<City> cities = new ArrayList<>();
    private List<Activity> regionActivities = new ArrayList<>();

    public Region(String name, List<Country> countries) {
        this.name = name;
        this.countries = countries;
    }

    public void addCity(City c) {
        cities.add(c);
    }

    public void addActivity(Activity a) {
        regionActivities.add(a);
    }

    public List<Country> getCountries() {
        return this.countries;
    }

    public List<City> getCities() {
        return cities;
    }

    public List<Activity> getRegionActivities() {
        return regionActivities;
    }

    @Override
    public String getName() {
        return name;
    }
}
