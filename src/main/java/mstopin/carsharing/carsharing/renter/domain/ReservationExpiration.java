package mstopin.carsharing.carsharing.renter.domain;

import lombok.Value;

import java.time.Instant;

@Value
public class ReservationExpiration {
  Instant expiresAt;

  public boolean isExpired() {
    return expiresAt.isBefore(Instant.now());
  }
}
