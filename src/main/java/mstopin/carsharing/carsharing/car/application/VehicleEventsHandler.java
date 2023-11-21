package mstopin.carsharing.carsharing.car.application;

import lombok.RequiredArgsConstructor;
import mstopin.carsharing.carsharing.car.domain.AvailableCar;
import mstopin.carsharing.carsharing.car.domain.CarRepository;
import mstopin.carsharing.carsharing.car.domain.Fuel;
import mstopin.carsharing.vehicles.NewVehicleAddedEvent;
import org.springframework.context.event.EventListener;

@RequiredArgsConstructor
public class VehicleEventsHandler {
  private final CarRepository carRepository;

  @EventListener
  void handleNewVehicleAdded(NewVehicleAddedEvent event) {
    carRepository.save(new AvailableCar(
      event.getAggregateId(),
      new Fuel(1)
    ));
  }
}
