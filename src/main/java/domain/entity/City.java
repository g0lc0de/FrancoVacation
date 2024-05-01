package domain.entity;

import domain.entity.auxilliary.ActivityBuilder;
import domain.entity.auxilliary.PhysicalLocation;

import java.util.ArrayList;
import java.util.List;

public class City implements PhysicalLocation {

    private String name;
    private List<Activity> cityActivities = new ArrayList<>();

    public City(String name) {
        this.name = name;
    }

    public void addActivity(ActivityBuilder activityBuilder) {
        ActivityBuilder activityBuilder1 = activityBuilder.buildAssociatedCity(this);

        cityActivities.add(activityBuilder1.build());
    }

    public List<Activity> getCityActivities() {
        return cityActivities;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("City: {%s}", this.name);
    }
}
