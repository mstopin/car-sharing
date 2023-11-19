package mstopin.carsharing.carsharing.renter.domain;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import mstopin.carsharing.carsharing.car.domain.Car;

import java.time.Instant;
import java.util.UUID;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ReservationRental implements Rental {
  private static final long DEFAULT_DURATION_SECONDS = 15 * 60;

  private final UUID carId;
  private final Instant to;

  public static ReservationRental fromNow(Car c) {
    return new ReservationRental(
      c.getAggregateId(),
      Instant.now().plusSeconds(DEFAULT_DURATION_SECONDS)
    );
  }

  public boolean isExpired() {
    return to.isBefore(Instant.now());
  }

  public Instant getTo() {
    return to;
  }

  public ActiveRental doFinalize() {
    return new ActiveRental();
  }
}
