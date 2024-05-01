package adapter;

import domain.aggregate.Region;

import java.util.List;
import java.util.stream.Collectors;

public class RegionsAdapter {

    private List<Region> regions;

    public RegionsAdapter(List<Region> regions) {
        this.regions = regions;
    }


    public List<String> getRegionsNameList() {
        return regions.stream().map(Region::getName).collect(Collectors.toList());
    }

    public Region getRegionDetails(String regionName) {

        // Find Region
        Region filteredRegion = regions.stream().filter(region -> region.getName().equals(regionName))
                .findFirst()
                .orElse(null);

        // Return Region UI Ressource!

        return filteredRegion;
    }



}
