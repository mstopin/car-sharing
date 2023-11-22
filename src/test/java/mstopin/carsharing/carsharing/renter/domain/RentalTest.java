package mstopin.carsharing.carsharing.renter.domain;

import mstopin.carsharing.carsharing.car.domain.AvailableCar;
import mstopin.carsharing.carsharing.car.domain.Car;
import mstopin.carsharing.carsharing.car.domain.Fuel;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RentalTest {
  @Test
  void shouldCalculateIsFor() {
    UUID carId = UUID.randomUUID();
    Car car = new AvailableCar(carId, Fuel.fromPercent(100));

    Rental rental = new Rental(UUID.randomUUID(), carId);

    assertThat(rental.isFor(car)).isEqualTo(true);
  }
}