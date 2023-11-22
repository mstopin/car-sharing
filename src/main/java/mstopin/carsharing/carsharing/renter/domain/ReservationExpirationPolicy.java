package mstopin.carsharing.carsharing.renter.domain;

import lombok.RequiredArgsConstructor;
import mstopin.carsharing.common.domain.TimeProvider;

public interface ReservationExpirationPolicy {
  ReservationExpiration calculateFor(Renter renter);
}

@RequiredArgsConstructor
class DefaultReservationExpirationPolicy implements ReservationExpirationPolicy {
  private final TimeProvider timeProvider;
  @Override
  public ReservationExpiration calculateFor(Renter renter) {
    long DEFAULT_RESERVATION_DURATION_SECONDS = 15 * 60;

    return new ReservationExpiration(
      timeProvider.now().plusSeconds(DEFAULT_RESERVATION_DURATION_SECONDS)
    );
  }
}