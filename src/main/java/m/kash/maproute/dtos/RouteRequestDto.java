package m.kash.maproute.dtos;

import lombok.Data;

@Data
public class RouteRequestDto {
    private String source;
    private String destination;
    private String mode;
}
