package mstopin.carsharing.carsharing.renter.domain.events;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
public class CarReservationCanceledEvent implements RenterEvent {
    @NonNull UUID renterId;
    @NonNull UUID carId;
}
