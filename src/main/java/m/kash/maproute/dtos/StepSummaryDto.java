package m.kash.maproute.dtos;

import lombok.Data;

@Data
public class StepSummaryDto {
    private String instructions;
    private String readable_distance;
    private String maneuver;
    private String readable_duration;
}
