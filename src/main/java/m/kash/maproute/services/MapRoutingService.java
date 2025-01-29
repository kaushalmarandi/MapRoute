package m.kash.maproute.services;

import m.kash.maproute.dtos.RouteResponseWithDetailsDto;
import m.kash.maproute.models.Destination;
import m.kash.maproute.models.Source;

public interface MapRoutingService {
    public RouteResponseWithDetailsDto getRoute(Source source, Destination destination, String mode);
}
