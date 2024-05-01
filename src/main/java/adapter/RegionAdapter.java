package adapter;

import domain.aggregate.Region;

import java.util.List;
import java.util.stream.Collectors;

public class RegionAdapter {

    private List<Region> regions;

    public RegionAdapter(List<Region> regions) {
        this.regions = regions;
    }


    public List<String> getRegionNameList() {
        return regions.stream().map(Region::getName).collect(Collectors.toList());
    }



}
