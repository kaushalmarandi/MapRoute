package m.kash.maproute.services;

import m.kash.maproute.models.Coordinates;

public interface MapCoordinateService {
    public Coordinates getCoordinates(String address);
}
