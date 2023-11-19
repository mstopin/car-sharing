package mstopin.carsharing.carsharing.renter.domain.events;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
public class CarRentedEvent implements RenterEvent {
  @NonNull UUID renterId;
  @NonNull UUID carId;
}
