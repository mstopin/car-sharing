package mstopin.carsharing.carsharing.renter.domain;

import java.time.Instant;
import java.util.UUID;

class Reservation extends AbstractRental {
  private static final long DEFAULT_RESERVATION_TIME_SECONDS = 15 * 60;
  private final Instant validTo;

  public Reservation(UUID carId, Instant validTo) {
    super(carId);
    this.validTo = validTo;
  }

  public static Reservation reserveNow(UUID carId) {
    return new Reservation(
      carId,
      Instant.now().plusSeconds(DEFAULT_RESERVATION_TIME_SECONDS)
    );
  }

  @Override
  public boolean isExpired() {
    return validTo.isBefore(Instant.now());
  }
}
