package mstopin.carsharing.carsharing.renter.domain;

import java.time.Instant;

public interface ReservationExpirationPolicy {
  ReservationExpiration calculateFor(Renter renter);
}

class DefaultReservationExpirationPolicy implements ReservationExpirationPolicy {
  @Override
  public ReservationExpiration calculateFor(Renter renter) {
    long DEFAULT_RESERVATION_DURATION_SECONDS = 15 * 60;

    return new ReservationExpiration(
      Instant.now().plusSeconds(DEFAULT_RESERVATION_DURATION_SECONDS)
    );
  }
}