package mstopin.carsharing.common.infra;

import mstopin.carsharing.common.domain.TimeProvider;

import java.time.Instant;

public class DefaultTimeProvider implements TimeProvider {
  @Override
  public Instant now() {
    return Instant.now();
  }
}
