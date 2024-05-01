package application;

import domain.aggregate.Region;
import domain.entity.City;
import domain.repository.RegionRepository;
import plugin.MockDataProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class QueryDataFinder {

    List<Region> regionsList;
    Map<String, Region> allRegionMap = new HashMap<String, Region>();

    public QueryDataFinder(RegionRepository regionRepository) {

        regionsList = regionRepository.getRegions();

        for (Region region : regionsList) {
            allRegionMap.put(region.getName().toLowerCase(Locale.ROOT), region);
        }

    }

    public List<Region> getAllRegionsAsList() {
        return this.regionsList;
    }

    public Region getRegionFromName(String regionName) {

        return this.allRegionMap.get(regionName);

    }

}
