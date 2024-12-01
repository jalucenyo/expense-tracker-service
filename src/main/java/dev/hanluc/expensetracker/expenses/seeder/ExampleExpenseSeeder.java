package dev.hanluc.expensetracker.expenses.seeder;

import dev.hanluc.expensetracker.common.domain.vo.Money;
import dev.hanluc.expensetracker.expenses.domain.ExpenseBuilder;
import dev.hanluc.expensetracker.expenses.domain.repository.ExpenseRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Configuration
public class ExampleExpenseSeeder implements ApplicationRunner {

  private final ExpenseRepository expenseRepository;

  public ExampleExpenseSeeder(ExpenseRepository expenseRepository) {
    this.expenseRepository = expenseRepository;
  }

  @Override
  public void run(ApplicationArguments args) {

    final var exampleExpenses = List.of(
      new ExpenseBuilder().setId(UUID.randomUUID()).setAmount(new Money(1000L, 2)).setDescription("Office Supplies").setTransactionDate(OffsetDateTime.now().minusDays(5)).setPaymentMethod("CREDIT_CARD").setVendor("Office Depot").setRecurrence("WEEKLY").setNotes("Purchased pens and notebooks").createExpense(),
      new ExpenseBuilder().setId(UUID.randomUUID()).setAmount(new Money(4999L, 2)).setDescription("Software Subscription").setTransactionDate(OffsetDateTime.now().minusMonths(1)).setPaymentMethod("CASH").setVendor("Adobe").setRecurrence("MONTHLY").setNotes("Monthly subscription for Photoshop").createExpense(),
      new ExpenseBuilder().setId(UUID.randomUUID()).setAmount(new Money(120000L, 2)).setDescription("Conference Registration").setTransactionDate(OffsetDateTime.now().minusWeeks(2)).setPaymentMethod("TRANSFER").setVendor("TechCon 2024").setRecurrence("WEEKLY").setNotes("Early bird registration for the tech conference").createExpense(),
      new ExpenseBuilder().setId(UUID.randomUUID()).setAmount(new Money(8500L, 2)).setDescription("Team Lunch").setTransactionDate(OffsetDateTime.now().minusDays(10)).setPaymentMethod("CASH").setVendor("The Italian Place").setRecurrence("YEARLY").setNotes("Lunch with team to celebrate project completion").createExpense(),
      new ExpenseBuilder().setId(UUID.randomUUID()).setAmount(new Money(150000L, 2)).setDescription("Hardware Purchase").setTransactionDate(OffsetDateTime.now().minusDays(3)).setPaymentMethod("CREDIT_CARD").setVendor("Best Buy").setRecurrence("YEARLY").setNotes("Purchased a new laptop for development").createExpense()
    );

    expenseRepository.saveAll(exampleExpenses);
  }

}
