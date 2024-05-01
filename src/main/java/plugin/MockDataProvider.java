package plugin;

import domain.aggregate.Region;
import domain.entity.City;
import domain.entity.Country;
import domain.entity.auxilliary.ActivityBuilder;
import domain.valueObject.ActivityLevel;
import domain.valueObject.ActivityLevelType;
import domain.valueObject.Address;
import domain.valueObject.Season;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum MockDataProvider implements DatabaseConnection {

    INSTANCE;

    List<Region> regions = new ArrayList<>();

    ActivityLevel sportiveActivityLevel = new ActivityLevel(ActivityLevelType.SPORT);
    ActivityLevel leisureActivityLevel = new ActivityLevel(ActivityLevelType.LEISURE);


    @Override
    public void ConnectToDatabase() {
        System.out.println("Connect to Database Method called!");
    }

    @Override
    public void QueryData() {
        createRegions();
    }

    public void createRegions() {

        Country germany = new Country("Deutschland");
        Region black_forest = new Region("Schwarzwald", Arrays.asList(germany));
        City freiburg = new City("Freiburg");

        regions.add(black_forest);
        black_forest.addCity(freiburg);

        ActivityBuilder feldbergSkiingActivityBuilder = new ActivityBuilder()
            .buildActivityAddress(new Address("Dr-Pilet-Spur", "17", "79868", "Feldberg(Schwarzwald)"))
            .buildActivityLevel(sportiveActivityLevel)
            .buildSeasons(new ArrayList<>(Arrays.asList(Season.WINTER)))
            .buildActivityDetails("Feldberg Skilift", "Skilift detaillierte Beschreibung");

        ActivityBuilder badeparadisSchwarzwald = new ActivityBuilder()
            .buildActivityAddress(new Address("Am Badeparadies", "1", "79822", "Titisee-Neustadt"))
            .buildActivityLevel(leisureActivityLevel)
            .buildSeasons(new ArrayList<>(Arrays.asList(Season.WINTER, Season.FALL, Season.SPRING, Season.SUMMER)))
            .buildActivityDetails("Badeparadis Schwarzwald", "Thermen und Saunalandschaft");

        black_forest.addActivity(feldbergSkiingActivityBuilder.build());
        black_forest.addActivity(badeparadisSchwarzwald.build());

//        black_forest.getCities().forEach(city -> System.out.println(city.getCityActivities()));
//        black_forest.getRegionActivities().forEach(activity -> System.out.println(activity.getAssociatedCity()));

    }

    public List<Region> getRegions() {
        return regions;
    }
}
