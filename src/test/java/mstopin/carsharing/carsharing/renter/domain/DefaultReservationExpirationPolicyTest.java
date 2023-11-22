package mstopin.carsharing.carsharing.renter.domain;

import mstopin.carsharing.common.domain.TimeProvider;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DefaultReservationExpirationPolicyTest {
  static class EpochTimeProvider implements TimeProvider {
    @Override
    public Instant now() {
      return Instant.EPOCH;
    }
  }

  @Test
  void shouldCalculateReservationExpiration() {
    final long EXPECTED_RESERVATION_TIME_SECONDS = 15 * 60;

    ReservationExpirationPolicy policy = new DefaultReservationExpirationPolicy(new EpochTimeProvider());
    Renter renter = RenterFactory.create(UUID.randomUUID(), null, null, RenterType.CLIENT);

    ReservationExpiration reservationExpiration = policy.calculateFor(renter);

    assertThat(reservationExpiration.getExpiresAt())
      .isEqualTo(Instant.EPOCH.plusSeconds(EXPECTED_RESERVATION_TIME_SECONDS));
  }
}