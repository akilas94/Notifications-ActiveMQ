package com.singlife.email.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

import java.io.Serializable;

public class SingLifeNamingStrategy extends SpringPhysicalNamingStrategy implements Serializable {

  private static final long serialVersionUID = 1L;

  @Override
  public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
    return new Identifier(name.getText(), name.isQuoted());
  }

  @Override
  public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
    return new Identifier(name.getText(), name.isQuoted());
  }

}
