package mstopin.carsharing.carsharing.renter.infra;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import mstopin.carsharing.carsharing.renter.domain.Renter;
import mstopin.carsharing.carsharing.renter.domain.RenterId;

import java.util.UUID;

@Entity
public class RenterEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  Renter toDomain() {
    return new Renter(new RenterId(id), null);
  }
}
