package mstopin.carsharing.carsharing.renter.domain;

import java.util.UUID;

public class RenterFactory {
  public static Renter create(UUID renterId, Reservation reservation, Rental rental) {
    return new Renter(
      renterId,
      reservation,
      rental,
      new DefaultReservationExpirationPolicy()
    );
  }
}
