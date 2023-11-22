package mstopin.carsharing.carsharing.renter.domain;

import mstopin.carsharing.carsharing.car.domain.*;
import mstopin.carsharing.carsharing.renter.domain.events.CarRentalFinishedEvent;
import mstopin.carsharing.carsharing.renter.domain.events.CarRentedEvent;
import mstopin.carsharing.carsharing.renter.domain.events.CarReservationCanceledEvent;
import mstopin.carsharing.carsharing.renter.domain.events.CarReservedEvent;
import mstopin.carsharing.common.domain.BusinessRuleBrokenException;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RenterTest {
  @Test
  void shouldReserveCar() {
    AvailableCar car = anAvailableCar(Fuel.fromPercent(100));
    Renter renter = aRenterWithoutReservationAndRental();

    assertThat(renter.reserve(car)).isInstanceOf(CarReservedEvent.class);
  }

  @Test
  void shouldNotReserveCarIfThereIsActiveReservation() {
    AvailableCar car = anAvailableCar(Fuel.fromPercent(100));
    Renter renter = aRenterWithActiveReservationWithoutRental(anAvailableCar(Fuel.fromPercent(100)));

    BusinessRuleBrokenException ex = assertThrows(BusinessRuleBrokenException.class, () -> {
      renter.reserve(car);
    });

    assertThat(ex.getMessage()).isEqualTo("Renter has already a valid reservation");
  }

  @Test
  void shouldNotReserveCarIfFuelIsLessThan15Percent() {
    AvailableCar car = anAvailableCar(Fuel.fromPercent(10));
    Renter renter = aRenterWithoutReservationAndRental();

    BusinessRuleBrokenException ex = assertThrows(BusinessRuleBrokenException.class, () -> {
      renter.reserve(car);
    });

    assertThat(ex.getMessage()).isEqualTo("Cannot reserve car that has below 15% of fuel");
  }

  @Test
  void shouldCancelReservation() {
    ReservedCar car = aReservedCar(Fuel.fromPercent(100));
    Renter renter = aRenterWithActiveReservationWithoutRental(car);

    assertThat(renter.cancelReservation(car)).isInstanceOf(CarReservationCanceledEvent.class);
  }

  @Test
  void shouldNotCancelReservationIfThereIsNoReservation() {
    ReservedCar car = aReservedCar(Fuel.fromPercent(100));
    Renter renter = aRenterWithoutReservationAndRental();

    BusinessRuleBrokenException ex = assertThrows(BusinessRuleBrokenException.class, () -> {
      renter.cancelReservation(car);
    });

    assertThat(ex.getMessage()).isEqualTo("Active reservation not found for given car");
  }

  @Test
  void shouldNotCancelReservationIfThereIsReservationForDifferentCar() {
    ReservedCar car = aReservedCar(Fuel.fromPercent(100));
    Renter renter = aRenterWithActiveReservationWithoutRental(aReservedCar(Fuel.fromPercent(100)));

    BusinessRuleBrokenException ex = assertThrows(BusinessRuleBrokenException.class, () -> {
      renter.cancelReservation(car);
    });

    assertThat(ex.getMessage()).isEqualTo("Active reservation not found for given car");
  }

  @Test
  void shouldRentCar() {
    ReservedCar car = aReservedCar(Fuel.fromPercent(100));
    Renter renter = aRenterWithActiveReservationWithoutRental(car);

    assertThat(renter.rent(car)).isInstanceOf(CarRentedEvent.class);
  }

  @Test
  void shouldNotRentCarIfThereIsNoReservation() {
    ReservedCar car = aReservedCar(Fuel.fromPercent(100));
    Renter renter = aRenterWithoutReservationAndRental();

    BusinessRuleBrokenException ex = assertThrows(BusinessRuleBrokenException.class, () -> {
      renter.rent(car);
    });

    assertThat(ex.getMessage()).isEqualTo("Active reservation not found for given car");
  }

  @Test
  void shouldNotRentCarIfThereIsReservationForDifferentCar() {
    ReservedCar car = aReservedCar(Fuel.fromPercent(100));
    Renter renter = aRenterWithActiveReservationWithoutRental(aReservedCar(Fuel.fromPercent(100)));

    BusinessRuleBrokenException ex = assertThrows(BusinessRuleBrokenException.class, () -> {
      renter.rent(car);
    });

    assertThat(ex.getMessage()).isEqualTo("Active reservation not found for given car");
  }

  @Test
  void shouldFinishRental() {
    RentedCar car = aRentedCar(Fuel.fromPercent(100));
    Renter renter = aRenterWithActiveReservationAndRental(car);

    assertThat(renter.finishRental(car)).isInstanceOf(CarRentalFinishedEvent.class);
  }

  @Test
  void shouldNotFinishRentalIfThereIsNoRental() {
    RentedCar car = aRentedCar(Fuel.fromPercent(100));
    Renter renter = aRenterWithActiveReservationWithoutRental(car);

    BusinessRuleBrokenException ex = assertThrows(BusinessRuleBrokenException.class, () -> {
      renter.finishRental(car);
    });

    assertThat(ex.getMessage()).isEqualTo("Active rental not found for given car");
  }

  @Test
  void shouldNotFinishRentalIfThereIsRentalForDifferentCar() {
    RentedCar car = aRentedCar(Fuel.fromPercent(100));
    Renter renter = aRenterWithActiveReservationAndRental(aRentedCar(Fuel.fromPercent(100)));

    BusinessRuleBrokenException ex = assertThrows(BusinessRuleBrokenException.class, () -> {
      renter.finishRental(car);
    });

    assertThat(ex.getMessage()).isEqualTo("Active rental not found for given car");
  }

  private Renter aRenterWithoutReservationAndRental() {
    return aRenter(null, null);
  }

  private Renter aRenterWithActiveReservationWithoutRental(Car car) {
    return aRenter(anActiveReservationFor(car), null);
  }

  private Renter aRenterWithActiveReservationAndRental(Car car) {
    return aRenter(anActiveReservationFor(car), aRentalFor(car));
  }

  private Renter aRenter(Reservation reservation, Rental rental) {
    return RenterFactory.create(UUID.randomUUID(), reservation, rental, RenterType.CLIENT);
  }

  private Reservation anActiveReservationFor(Car car) {
    return new Reservation(
      UUID.randomUUID(),
      car.getAggregateId(),
      new ReservationExpiration(Instant.now().plusSeconds(3600))
    );
  }

  private Rental aRentalFor(Car car) {
    return new Rental(
      UUID.randomUUID(),
      car.getAggregateId()
    );
  }

  private AvailableCar anAvailableCar(Fuel fuel) {
    return new AvailableCar(UUID.randomUUID(), fuel);
  }

  private ReservedCar aReservedCar(Fuel fuel) {
    return new ReservedCar(UUID.randomUUID(), fuel);
  }

  private RentedCar aRentedCar(Fuel fuel) {
    return new RentedCar(UUID.randomUUID(), fuel);
  }
}