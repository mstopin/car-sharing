package mstopin.carsharing.carsharing.renter.domain;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ReservationExpirationTest {
  @Test
  void shouldBeExpiredWhenExpirationInThePast() {
    ReservationExpiration reservationExpiration = new ReservationExpiration(
      Instant.now().minusSeconds(3600)
    );

    assertThat(reservationExpiration.isExpired()).isEqualTo(true);
  }

  @Test
  void shouldNotBeExpiredWhenExpirationNotInThePast() {
    ReservationExpiration reservationExpiration = new ReservationExpiration(
      Instant.now().plusSeconds(3600)
    );

    assertThat(reservationExpiration.isExpired()).isEqualTo(false);
  }
}
