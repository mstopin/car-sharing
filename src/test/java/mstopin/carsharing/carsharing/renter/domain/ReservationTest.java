package mstopin.carsharing.carsharing.renter.domain;

import mstopin.carsharing.carsharing.car.domain.AvailableCar;
import mstopin.carsharing.carsharing.car.domain.Car;
import mstopin.carsharing.carsharing.car.domain.Fuel;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ReservationTest {
  @Test
  void shouldBeExpiredWhenReservationExpirationIsExpired() {
    Reservation reservation = new Reservation(
      UUID.randomUUID(),
      UUID.randomUUID(),
      new ReservationExpiration(Instant.now().minusSeconds(3600))
    );

    assertThat(reservation.isExpired()).isEqualTo(true);
  }

  @Test
  void shouldNotBeExpiredWhenReservationExpirationIsNotExpired() {
    Reservation reservation = new Reservation(
      UUID.randomUUID(),
      UUID.randomUUID(),
      new ReservationExpiration(Instant.now().plusSeconds(3600))
    );

    assertThat(reservation.isExpired()).isEqualTo(false);
  }

  @Test
  void shouldReturnIsFor() {
    UUID carId = UUID.randomUUID();
    Car car = new AvailableCar(carId, Fuel.fromPercent(100));

    Reservation reservation = new Reservation(
      UUID.randomUUID(),
      carId,
      new ReservationExpiration(Instant.now().plusSeconds(3600))
    );

    assertThat(reservation.isFor(car)).isEqualTo(true);
  }
}