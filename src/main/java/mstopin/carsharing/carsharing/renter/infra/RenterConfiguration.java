package mstopin.carsharing.carsharing.renter.infra;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;
import java.util.UUID;

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

  @Bean
  CommandLineRunner seedRenters(RenterEntityRepository renterEntityRepository) {
    return args -> {
      renterEntityRepository.save(new RenterEntity(
        UUID.fromString("bbbbbbbb-aaaa-aaaa-aaaa-aaaaaaaaaaaa"),
        Set.of(),
        Set.of()
      ));
    };
  }
}

