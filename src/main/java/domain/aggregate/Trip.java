package domain.aggregate;

import domain.entity.Accomodation;
import domain.entity.City;
import domain.valueObject.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Trip {

    private Region region;
    private List<City> citiesOnIntineryList;
    private Accomodation accomodation;
    private List<Activity> activitiesThatMatchQuery = new ArrayList<>();
    private Query query;

    public Trip(Region region, List<Activity> activitiesThatMatchQueryArg, Query query, List<City> citiesOnIntineryList) {
        this.region = region;
        this.query = query;
        this.citiesOnIntineryList = citiesOnIntineryList;

        if (null != activitiesThatMatchQueryArg) {
            this.activitiesThatMatchQuery = activitiesThatMatchQueryArg;
        }

    }

    public Region getRegion() {
        return region;
    }

    public Accomodation getAccomodation() {
        return accomodation;
    }

    public List<Activity> getActivitiesThatMatchQuery() {
        return activitiesThatMatchQuery;
    }

    public void addCityToIntinery(City c) {
        citiesOnIntineryList.add(c);
    }

    public void removeCityFromIntinery(City c) {
        citiesOnIntineryList.remove(c);
    }

    public Query getQuery() {
        return query;
    }
}
