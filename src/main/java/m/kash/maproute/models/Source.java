package m.kash.maproute.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Source {
    private Coordinates coordinates;

    public Source(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
