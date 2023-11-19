package mstopin.carsharing.common.domain;

import java.util.UUID;

public interface AggregateRoot {
  UUID getAggregateId();
}
