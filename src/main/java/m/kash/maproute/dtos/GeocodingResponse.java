package m.kash.maproute.dtos;

import lombok.Data;

import java.util.List;

@Data
public class GeocodingResponse {
    private List<GeocodingResult> geocodingResults;
    private String status;

    @Data
    public static class GeocodingResult {
        private String formattedAddress;
        private List<String> types;
        private String name;
        private Geometry geometry;
        private List<AddressComponent> addressComponents;
        private String placeId;
    }

    @Data
    public static class Geometry {
        private Location location;
        private Viewport viewport;
    }

    @Data
    public static class Location {
        private double lat;
        private double lng;
    }

    @Data
    public static class Viewport {
        private Location southwest;
        private Location northeast;
    }

    @Data
    public static class AddressComponent {
        private List<String> types;
        private String shortName;
        private String longName;
    }
}
