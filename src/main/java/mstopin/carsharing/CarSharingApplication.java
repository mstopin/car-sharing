package mstopin.carsharing;

import mstopin.carsharing.carsharing.car.domain.Car;
import mstopin.carsharing.carsharing.renter.application.FindAvailableCar;
import mstopin.carsharing.carsharing.renter.application.commands.ReserveCarCommand;
import mstopin.carsharing.carsharing.renter.application.handlers.ReserveCarCommandHandler;
import mstopin.carsharing.carsharing.renter.domain.RenterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class CarSharingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarSharingApplication.class, args);
	}

	@Bean
	CommandLineRunner execute(FindAvailableCar findAvailableCar, RenterRepository renterRepository) {
		return args -> {
			ReserveCarCommand command = new ReserveCarCommand(
				UUID.fromString("bbbbbbbb-aaaa-aaaa-aaaa-aaaaaaaaaaaa"),
				UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
			);

			ReserveCarCommandHandler handler = new ReserveCarCommandHandler(
				findAvailableCar,
				renterRepository
			);

			handler.execute(command);
		};
	}
}
