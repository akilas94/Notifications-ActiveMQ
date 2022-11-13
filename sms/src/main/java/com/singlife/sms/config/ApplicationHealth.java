package com.singlife.sms.config;

import com.singlife.sms.repository.CheckDbConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ApplicationHealth implements HealthIndicator {

  private static final String DATABASE = "Database";
  private static final String SERVICE_UP = "UP";
  private static final String SERVICE_DOWN = "DOWN";

  @Autowired
  private CheckDbConnectionRepository checkDbConnectionRepository;

  @Override
  public Health health() {
    final Map<String, String> serviceMap = new HashMap<>();
    if (checkServices(serviceMap)) {
      return Health.down().withDetails(serviceMap).build();
    }
    return Health.up().withDetails(serviceMap).build();
  }

  private boolean checkServices(final Map<String, String> serviceMap) {
    serviceMap.put(
        DATABASE,
        getDbStatus()
    );

    return serviceMap.containsValue(SERVICE_DOWN);
  }

  private String getDbStatus() {
    return checkDbConnectionRepository.checkDbConnection() ? SERVICE_UP : SERVICE_DOWN;
  }

}
