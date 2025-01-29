package m.kash.maproute.dtos;

import lombok.Getter;
import lombok.Setter;
import m.kash.maproute.models.Destination;
import m.kash.maproute.models.Source;

@Getter
@Setter
public class CoordinateResponseDto {
    private Source source;
    private Destination destination;

    public CoordinateResponseDto(Source source, Destination destination) {
        this.source = source;
        this.destination = destination;
    }
}
