package adapter;

import domain.aggregate.Region;
import domain.aggregate.Trip;

public class TripAdapter {

    private Trip trip;

    public TripAdapter(Trip trip) {
        this.trip = trip;
    }

    public Region getRegionDetails() {
        return this.trip.getRegion();
    }

    public Trip getTrip() {
        return this.trip;
    }



}
