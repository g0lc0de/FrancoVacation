package domain.repository;

import domain.aggregate.Region;

import java.util.List;

public interface RegionRepository {

    List<Region> getRegions();
    boolean addRegion(Region region);
}
