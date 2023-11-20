package mstopin.carsharing.carsharing.renter.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RenterConfiguration {
  @Bean
  RenterDatabaseRepository renterDatabaseRepository(RenterJpaRepository renterJpaRepository) {
    return new RenterDatabaseRepository(renterJpaRepository);
  }
}

