package mstopin.carsharing.useraccess.user;

import org.springframework.security.core.GrantedAuthority;

public enum UserType implements GrantedAuthority {
  USER,
  STAFF,
  ADMIN;

  @Override
  public String getAuthority() {
    return this.toString();
  }
};

