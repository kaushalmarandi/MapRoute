package m.kash.maproute.controllers;

import m.kash.maproute.dtos.*;
import m.kash.maproute.models.Destination;
import m.kash.maproute.models.Source;
import m.kash.maproute.services.MapCoordinateService;
import m.kash.maproute.services.MapRoutingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/maps")
public class MapController {
    private MapCoordinateService mapCoordinateService;
    private MapRoutingService mapRoutingService;
    public MapController(MapCoordinateService mapCoordinateService,
                         MapRoutingService mapRoutingService) {
        this.mapCoordinateService = mapCoordinateService;
        this.mapRoutingService = mapRoutingService;
    }

    @GetMapping("/coordinates")
    @CrossOrigin(origins = "*")
    public CoordinateResponseDto getCoordinates(@RequestBody CoordinateRequestDto coordinatesRequestDto){
        String sourceAddress = coordinatesRequestDto.getSource();
        Source responseSource = new Source(mapCoordinateService.getCoordinates(sourceAddress)) ;
        String destinationAddress = coordinatesRequestDto.getDestination();
        Destination responseDestination = new Destination(mapCoordinateService.getCoordinates(destinationAddress));
        return new CoordinateResponseDto(responseSource, responseDestination);
//        Coordinates coordinates = new Coordinates();
//        coordinates.setLatitude(37.7749);
//        coordinates.setLongitude(122.4194);
//        return null;
    }

    @PostMapping("/route")
    @CrossOrigin(origins = "*")
    public RouteResponseWithDetailsDto getRouteSteps(@RequestBody RouteRequestDto routeRequestDto){
        String source = routeRequestDto.getSource();
        Source sourceCoordinates = new Source(mapCoordinateService.getCoordinates(source));
        String destination = routeRequestDto.getDestination();
        Destination destinationCoordinates = new Destination(mapCoordinateService.getCoordinates(destination));
        return mapRoutingService.getRoute(sourceCoordinates, destinationCoordinates, routeRequestDto.getMode());
    }
}
