package mstopin.carsharing.carsharing.renter.infra;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mstopin.carsharing.carsharing.renter.domain.Rental;
import mstopin.carsharing.carsharing.renter.domain.events.CarRentedEvent;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RentalEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private UUID renterId;
  private UUID carId;
  private Instant rentedAt;
  private Instant finishedAt;

  Rental toDomain() {
    return new Rental(id, carId);
  }

  static RentalEntity fromEvent(CarRentedEvent event) {
    return new RentalEntity(
      null,
      event.getRenterId(),
      event.getCarId(),
      Instant.now(),
      null
    );
  }
}
