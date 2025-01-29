package m.kash.maproute.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteResponseWithDetailsDto {
    private double totalDistance;
    private String eta;
    private List<StepSummaryDto> steps;
}
