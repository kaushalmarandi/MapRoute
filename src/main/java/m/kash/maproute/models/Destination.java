package m.kash.maproute.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Destination {
    private Coordinates coordinates;

    public Destination(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
