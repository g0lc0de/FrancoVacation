package application;

import domain.aggregate.Region;
import domain.entity.City;
import plugin.MockDataProvider;

import java.util.List;

public class QueryDataFinder {

    public List<Region> getRegions() {

        // Refactor DI
        List<Region> regions = MockDataProvider.INSTANCE.getRegions();

        return regions;
    }

}
