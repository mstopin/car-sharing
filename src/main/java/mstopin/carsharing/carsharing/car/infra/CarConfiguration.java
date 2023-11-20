package mstopin.carsharing.carsharing.car.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CarConfiguration {
  @Bean
  CarDatabaseRepository carDatabaseRepository(CarJpaRepository carJpaRepository) {
    return new CarDatabaseRepository(carJpaRepository);
  }
}
