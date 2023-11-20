package mstopin.carsharing.carsharing.renter.infra;

import lombok.RequiredArgsConstructor;
import mstopin.carsharing.carsharing.renter.domain.Renter;
import mstopin.carsharing.carsharing.renter.domain.RenterRepository;
import mstopin.carsharing.carsharing.renter.domain.events.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

interface RenterEntityRepository extends JpaRepository<RenterEntity, UUID> {}

interface ReservationEntityRepository extends JpaRepository<ReservationEntity, UUID> {
  @Modifying
  @Query("UPDATE reservation_entity r SET r.cancelledAt = NOW() WHERE r.id = :id")
  default void setCancelled(@Param(value = "id") UUID reservationId) {}
}

interface RentalEntityRepository extends JpaRepository<RentalEntity, UUID> {
  @Modifying
  @Query("UPDATE rental_entity r SET r.finished_at = NOW() WHERE r.id = :id")
  default void setFinished(@Param(value = "id") UUID rentalId) {}
}

@RequiredArgsConstructor
public class RenterDatabaseRepository implements RenterRepository {
  private final RenterEntityRepository renterEntityRepository;
  private final ReservationEntityRepository reservationEntityRepository;
  private final RentalEntityRepository rentalEntityRepository;

  @Override
  public Optional<Renter> find(UUID renterId) {
    return renterEntityRepository
      .findById(renterId)
      .map(RenterEntity::toDomain);
  }

  @Override
  public void publishDomainEvent(RenterEvent event) {
    if (event instanceof CarReservedEvent) {
      handleCarReserved((CarReservedEvent) event);
    }

    if (event instanceof CarReservationCanceledEvent) {
      handleCarReservationCancelled((CarReservationCanceledEvent) event);
    }
  }

  private void handleCarReserved(CarReservedEvent event) {
    ReservationEntity reservationEntity = ReservationEntity.fromEvent(event);
    reservationEntityRepository.save(reservationEntity);
  }

  private void handleCarReservationCancelled(CarReservationCanceledEvent event) {
    reservationEntityRepository.setCancelled(event.getReservationId());
  }

  private void handleCarRented(CarRentedEvent event) {
    RentalEntity rentalEntity = RentalEntity.fromEvent(event);
    rentalEntityRepository.save(rentalEntity);
  }

  private void handleCarRentalFinished(CarRentalFinishedEvent event) {
    rentalEntityRepository.setFinished(event.getRentalId());
  }
}
