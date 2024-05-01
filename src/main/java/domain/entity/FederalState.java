package domain.entity;

import domain.entity.auxilliary.PhysicalLocation;

public class FederalState implements PhysicalLocation {

    private String name;
    private City[] cities;

    @Override
    public String getName() {
        return name;
    }
}
