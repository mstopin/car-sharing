package mstopin.carsharing.carsharing.renter.infra;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import mstopin.carsharing.carsharing.renter.domain.Rental;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
public class RentalEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private UUID renterId;
  private UUID carId;
  private Instant rentedAt;
  private Instant finishedAt;

  Rental toDomain() {
    return new Rental(carId);
  }
}
