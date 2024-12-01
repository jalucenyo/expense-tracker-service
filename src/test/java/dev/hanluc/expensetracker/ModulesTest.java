package dev.hanluc.expensetracker;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ModulesTest {

  @Container
  @ServiceConnection
  static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>(
    DockerImageName.parse("postgres:latest"));

  @Test
  void contextLoads() {
    var modules = ApplicationModules.of(Application.class);

    for (var m: modules){
      System.out.println("module: " + m.getName() + " - " + m.getBasePackage());
    }

    modules.verify();

    new Documenter(modules)
      .writeIndividualModulesAsPlantUml()
      .writeModulesAsPlantUml();

  }

}
