package mstopin.carsharing.carsharing.renter.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RenterConfiguration {
  @Bean
  RenterDatabaseRepository renterDatabaseRepository(RenterEntityRepository renterEntityRepository) {
    return new RenterDatabaseRepository(renterEntityRepository);
  }
}

