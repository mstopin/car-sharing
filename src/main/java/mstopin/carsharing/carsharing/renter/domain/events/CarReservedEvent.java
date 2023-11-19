package mstopin.carsharing.carsharing.renter.domain.events;

import lombok.NonNull;
import lombok.Value;

import java.time.Instant;
import java.util.UUID;

@Value
public class CarReservedEvent implements RenterEvent {
  @NonNull UUID renterId;
  @NonNull UUID carId;
  @NonNull Instant to;
}
