package mstopin.carsharing.carsharing.renter.domain;

import mstopin.carsharing.common.infra.DefaultTimeProvider;

import java.util.UUID;

public class RenterFactory {
  public static Renter create(UUID renterId, Reservation reservation, Rental rental, RenterType renterType) {
    return new Renter(
      renterId,
      reservation,
      rental,
      renterType,
      new DefaultReservationExpirationPolicy(new DefaultTimeProvider())
    );
  }
}
