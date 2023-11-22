package mstopin.carsharing.carsharing.car.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FuelTest {

  @Test
  void shouldThrowIfPercentIsNegative() {
    IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
      Fuel.fromPercent(-15);
    });

    assertThat(ex.getMessage()).isEqualTo("Fuel percent must be in range <0, 100>");
  }

  @Test
  void shouldThrowIfPercentIsMoreThan100() {
    IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
      Fuel.fromPercent(101);
    });

    assertThat(ex.getMessage()).isEqualTo("Fuel percent must be in range <0, 100>");
  }

  @Test
  void shouldCalculateIfIsBelow() {
    Fuel fuel = Fuel.fromPercent(50);

    assertThat(fuel.isBelow(75)).isEqualTo(true);
    assertThat(fuel.isBelow(25)).isEqualTo(false);
  }

  @Test
  void shouldCalculateIfIsAboveOrEqual() {
    Fuel fuel = Fuel.fromPercent(50);

    assertThat(fuel.isAboveOrEqual(25)).isEqualTo(true);
    assertThat(fuel.isAboveOrEqual(50)).isEqualTo(true);
    assertThat(fuel.isAboveOrEqual(75)).isEqualTo(false);
  }
}