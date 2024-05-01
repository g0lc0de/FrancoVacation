package domain.entity;

import domain.entity.auxilliary.PhysicalLocation;

public class Country implements PhysicalLocation {

    private String name;
    private String tld;

    public Country(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

}
