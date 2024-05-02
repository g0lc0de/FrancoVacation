package domain.valueObject;

import domain.aggregate.Region;

import java.util.List;
import java.util.Objects;

public final class Query {

    private final List<Season> seasons;
    private final List<ActivityLevelType> activityLevel;
    private final List<Region> regions;

    public Query(List<Season> seasons, List<ActivityLevelType> activityLevelTypes, List<Region> regions) {
        this.seasons = seasons;
        this.activityLevel = activityLevelTypes;
        this.regions = regions;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public List<ActivityLevelType> getActivityLevel() {
        return activityLevel;
    }

    public List<Region> getRegions() {
        return regions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Query query = (Query) o;
        return Objects.equals(seasons, query.seasons) && Objects.equals(activityLevel, query.activityLevel) && Objects.equals(regions, query.regions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seasons, activityLevel, regions);
    }
}
