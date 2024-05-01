package adapter;

import application.QueryDataFinder;
import domain.aggregate.Region;
import domain.entity.Country;
import domain.repository.RegionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class RegionsAdapter {

    private List<Region> regions;
    private QueryDataFinder queryDataFinder;

    public RegionsAdapter(List<Region> regions, RegionRepository regionRepository) {
        this.regions = regions;
        this.queryDataFinder = new QueryDataFinder(regionRepository);
    }


    public List<String> getRegionsNameList() {
        return regions.stream().map(Region::getName).collect(Collectors.toList());
    }

    public Region getRegionDetails(String regionName) {

        // Find Region
        Region region = queryDataFinder.getRegionFromName(regionName);

        return Objects.
                requireNonNullElseGet(region,
                        () -> new Region("Keine Region gefunden", new ArrayList<>()));


    }
}
