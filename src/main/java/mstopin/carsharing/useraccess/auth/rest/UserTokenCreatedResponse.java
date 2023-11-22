package mstopin.carsharing.useraccess.auth.rest;

import lombok.Value;

@Value(staticConstructor = "of")
public class UserTokenCreatedResponse {
  String token;
}
