package domain.valueObject.auxilliary;

import domain.aggregate.Region;
import domain.entity.auxilliary.Builder;
import domain.valueObject.ActivityLevelType;
import domain.valueObject.Query;
import domain.valueObject.Season;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QueryBuilder implements Builder {

    private final List<Season> seasons = new ArrayList<>();
    private final List<ActivityLevelType> activityLevel = new ArrayList<>();
    private final List<Region> regions = new ArrayList<>();

    public QueryBuilder() {

    }

    public QueryBuilder buildRegion(Region r) {
        this.regions.add(r);
        return this;
    }

    public QueryBuilder buildSeason(Season s) {
        this.seasons.add(s);
        return this;
    }

    public QueryBuilder buildActivityLvl(ActivityLevelType alt) {
        this.activityLevel.add(alt);
        return this;
    }


    @Override
    public Query build() {
        return new Query(this.seasons, this.activityLevel, this.regions);
    }
}
