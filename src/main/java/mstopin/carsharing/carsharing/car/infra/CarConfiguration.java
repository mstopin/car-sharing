package mstopin.carsharing.carsharing.car.infra;

import mstopin.carsharing.carsharing.car.application.RenterEventsHandler;
import mstopin.carsharing.carsharing.car.application.VehicleEventsHandler;
import mstopin.carsharing.carsharing.car.domain.CarRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CarConfiguration {
  @Bean
  CarDatabaseRepository carDatabaseRepository(CarEntityRepository carJpaRepository) {
    return new CarDatabaseRepository(carJpaRepository);
  }

  @Bean
  RenterEventsHandler renterEventsHandler(CarRepository carRepository) {
    return new RenterEventsHandler(carRepository);
  }

  @Bean
  VehicleEventsHandler vehicleEventsHandler(CarRepository carRepository) {
    return new VehicleEventsHandler(carRepository);
  }
}
