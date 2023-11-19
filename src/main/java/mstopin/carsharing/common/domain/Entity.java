package mstopin.carsharing.common.domain;

public interface Entity {
  default void validateBusinessRule(BusinessRule rule) throws RuntimeException {
    if (rule.isBroken()) {
      throw new BusinessRuleBrokenException(rule);
    }
  }
}
