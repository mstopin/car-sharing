package mstopin.carsharing.carsharing.renter.infra;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import mstopin.carsharing.carsharing.renter.domain.Reservation;
import mstopin.carsharing.carsharing.renter.domain.ReservationExpiration;

import java.time.Instant;
import java.util.UUID;
@Getter
public class ReservationEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private UUID renterId;
  private UUID carId;
  private Instant createdAt;
  private Instant expiresAt;
  private Instant finalizedAt;
  private Instant cancelledAt;

  Reservation toDomain() {
    return new Reservation(carId, new ReservationExpiration(expiresAt));
  }
}
