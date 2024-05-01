package adapter;

import domain.aggregate.Region;
import domain.entity.Entity;

import java.util.HashMap;
import java.util.Map;

public class UiRegion implements AggregateDisplayObject, UiMapper {

    private Map<RegionAttribute, Object> attributeMap = new HashMap<>();

    enum RegionAttribute {
        NAME,
        COUNTRIES,
        CITIES,
        DESCRIPTION
    }

    public UiRegion(Region region) {



    }

    @Override
    public void mapFromDomainEntityToUiResource(Entity domainEntity) {

    }

    @Override
    public String convertToStringForConsoleMessages() {
        return null;
    }

    @Override
    public String convertToStringForFileExports() {
        return null;
    }
}
