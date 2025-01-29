package m.kash.maproute.dtos;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class RouteResponseDto {
    private String status;
    private List<Route> routes;
    private String source_from;
    private String language_code;

    @Data
    public static class Route {
        private String summary;
        private List<Leg> legs;
        private List<Integer> waypoint_order;
        private String overview_polyline;
        private String travel_advisory;
        private Map<String, Object> bounds;
        private String copyrights;
        private List<String> warnings;

        @Data
        public static class Leg {
            private List<Step> steps;
            private double distance;
            private String readable_distance;
            private int duration;
            private String readable_duration;
            private Location start_location;
            private Location end_location;
            private String start_address;
            private String end_address;

            @Data
            public static class Step {
                private String instructions;
                private double distance;
                private String readable_distance;
                private String maneuver;
                private int duration;
                private String readable_duration;
                private Location start_location;
                private Location end_location;
                private int bearing_before;
                private int bearing_after;
            }

            @Data
            public static class Location {
                private double lat;
                private double lng;
            }
        }
    }
    @Data
    public static class StepSummaryDto {
        private String instructions;
        private String readable_distance;
        private String maneuver;
        private String readable_duration;
    }
}
