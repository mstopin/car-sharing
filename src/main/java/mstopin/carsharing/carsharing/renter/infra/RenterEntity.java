package mstopin.carsharing.carsharing.renter.infra;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import mstopin.carsharing.carsharing.renter.domain.Rental;
import mstopin.carsharing.carsharing.renter.domain.Renter;
import mstopin.carsharing.carsharing.renter.domain.RenterFactory;
import mstopin.carsharing.carsharing.renter.domain.Reservation;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class RenterEntity {
  @Id
  private UUID id;

  @OneToMany
  private Set<ReservationEntity> reservations;

  @OneToMany
  private Set<RentalEntity> rentals;

  Renter toDomain() {
    Reservation reservation = findLastNotFinalized()
      .map(ReservationEntity::toDomain)
      .orElse(null);

    Rental rental = findLastNotFinished()
      .map(RentalEntity::toDomain)
      .orElse(null);

    return RenterFactory.create(
      id,
      reservation,
      rental
    );
  }
  private Optional<ReservationEntity> findLastNotFinalized() {
    return reservations
      .stream()
      .filter((r) -> r.getFinalizedAt() == null)
      .findAny();
  }

  private Optional<RentalEntity> findLastNotFinished() {
    return rentals
      .stream()
      .filter((r) -> r.getFinishedAt() == null)
      .findAny();
  }
}
