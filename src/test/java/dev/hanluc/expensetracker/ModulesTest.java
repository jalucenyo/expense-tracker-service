package dev.hanluc.expensetracker;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

class ModulesTest {

  static ApplicationModules modules = ApplicationModules.of(Application.class);

  @Test
  void verify_modules_structure() {
    modules.verify();
  }

  @Test
  void create_modules_documentation() {
    new Documenter(modules).writeDocumentation();
  }

}
