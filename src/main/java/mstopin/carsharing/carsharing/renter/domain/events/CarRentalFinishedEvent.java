package mstopin.carsharing.carsharing.renter.domain.events;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
public class CarRentalFinishedEvent implements RenterEvent {
  @NonNull UUID renterId;
  @NonNull UUID carId;
  @NonNull UUID rentalId;
}
