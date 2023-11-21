package mstopin.carsharing.carsharing.renter.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RenterConfiguration {
  @Bean
  RenterDatabaseRepository renterDatabaseRepository(
    RenterEntityRepository renterEntityRepository,
    ReservationEntityRepository reservationEntityRepository,
    RentalEntityRepository rentalEntityRepository
  ) {
    return new RenterDatabaseRepository(
      renterEntityRepository,
      reservationEntityRepository,
      rentalEntityRepository
    );
  }
}

