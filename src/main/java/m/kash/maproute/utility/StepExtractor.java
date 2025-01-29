package m.kash.maproute.utility;

import m.kash.maproute.dtos.RouteResponseDto;
import m.kash.maproute.dtos.RouteResponseWithDetailsDto;
import m.kash.maproute.dtos.StepSummaryDto;

import java.util.ArrayList;
import java.util.List;

public class StepExtractor {
    public static RouteResponseWithDetailsDto extractStepSummaries(RouteResponseDto routeResponseDto) {
        List<StepSummaryDto> stepSummaries = new ArrayList<>();
        double totalDistance = 0.0;
        int totalDuration = 0;

        // Loop through routes
        for (RouteResponseDto.Route route : routeResponseDto.getRoutes()) {
            // Loop through legs
            for (RouteResponseDto.Route.Leg leg : route.getLegs()) {
                // Loop through steps
                for (RouteResponseDto.Route.Leg.Step step : leg.getSteps()) {
                    // Map the relevant fields to StepSummaryDto
                    StepSummaryDto summary = new StepSummaryDto();
                    summary.setInstructions(step.getInstructions());
                    summary.setReadable_distance(step.getReadable_distance());
                    summary.setManeuver(step.getManeuver());
                    summary.setReadable_duration(step.getReadable_duration());
                    stepSummaries.add(summary);

                    // Calculate total distance and duration
                    totalDistance += step.getDistance();  // Add step distance to total
                    totalDuration += step.getDuration();  // Add step duration to total
                }
            }
        }

        // Calculate ETA from the total duration
        String eta = formatDuration(totalDuration);

        return new RouteResponseWithDetailsDto(totalDistance, eta, stepSummaries);
    }

    // Helper method to format the duration into "X hours Y minutes"
    private static String formatDuration(int durationInSeconds) {
        int hours = durationInSeconds / 3600;
        int minutes = (durationInSeconds % 3600) / 60;
        return String.format("%d hours %d minutes", hours, minutes);
    }

}
