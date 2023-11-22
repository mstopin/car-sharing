package mstopin.carsharing.common.domain;

import java.time.Instant;

public interface TimeProvider {
  Instant now();
}

