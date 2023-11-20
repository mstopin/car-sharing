package mstopin.carsharing.common.application;

public interface CommandHandler<C,R> {
  R execute(C command);
}

