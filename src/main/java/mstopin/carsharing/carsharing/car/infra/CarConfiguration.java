package mstopin.carsharing.carsharing.car.infra;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class CarConfiguration {
  @Bean
  CarDatabaseRepository carDatabaseRepository(CarEntityRepository carJpaRepository) {
    return new CarDatabaseRepository(carJpaRepository);
  }

  @Bean
  CommandLineRunner seedCars(CarEntityRepository carEntityRepository) {
    return args -> {
      carEntityRepository.save(new CarEntity(UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"), CarStatus.AVAILABLE, 1));
    };
  }
}
