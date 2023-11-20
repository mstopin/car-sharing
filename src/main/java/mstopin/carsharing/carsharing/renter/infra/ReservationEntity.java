package mstopin.carsharing.carsharing.renter.infra;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mstopin.carsharing.carsharing.renter.domain.Reservation;
import mstopin.carsharing.carsharing.renter.domain.ReservationExpiration;
import mstopin.carsharing.carsharing.renter.domain.events.CarReservedEvent;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
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
    return new Reservation(id, carId, new ReservationExpiration(expiresAt));
  }

  static ReservationEntity fromEvent(CarReservedEvent event) {
    return new ReservationEntity(
      null,
      event.getRenterId(),
      event.getCarId(),
      Instant.now(),
      event.getExpiresAt(),
      null,
      null
    );
  }
}
