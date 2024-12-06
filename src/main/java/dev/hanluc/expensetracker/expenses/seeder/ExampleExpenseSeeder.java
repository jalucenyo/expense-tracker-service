package dev.hanluc.expensetracker.expenses.seeder;

import dev.hanluc.expensetracker.common.domain.vo.Money;
import dev.hanluc.expensetracker.expenses.domain.Expense;
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
      new Expense(UUID.randomUUID(), new Money(1000L, 2), "Office Supplies", OffsetDateTime.now().minusDays(5), "CREDIT_CARD", "Office Depot", "WEEKLY", "Purchased pens and notebooks", "Marketing"),
      new Expense(UUID.randomUUID(), new Money(5000L, 2), "Groceries", OffsetDateTime.now().minusDays(2), "DEBIT_CARD", "Whole Foods", "MONTHLY", "Weekly grocery shopping for the family", "Sales"),
      new Expense(UUID.randomUUID(), new Money(750L, 2), "Coffee", OffsetDateTime.now().minusDays(1), "CASH", "Starbucks", "DAILY", "Morning coffee with a croissant", "Sales"),
      new Expense(UUID.randomUUID(), new Money(20000L, 2), "Car Maintenance", OffsetDateTime.now().minusDays(10), "CREDIT_CARD", "AutoFix Garage", "ANNUAL", "Oil change and tire replacement", "Technology"),
      new Expense(UUID.randomUUID(), new Money(15000L, 2), "Rent", OffsetDateTime.now().minusDays(15), "BANK_TRANSFER", "Landlord", "MONTHLY", "Paid monthly apartment rent", "Events"),
      new Expense(UUID.randomUUID(), new Money(3200L, 2), "Electricity Bill", OffsetDateTime.now().minusDays(8), "BANK_TRANSFER", "City Electric", "MONTHLY", "Electricity usage for October", "Sustainability"),
      new Expense(UUID.randomUUID(), new Money(12000L, 2), "Travel", OffsetDateTime.now().minusDays(20), "CREDIT_CARD", "Airbnb", "ONE_TIME", "Booked a cabin for a weekend getaway", "Technology"),
      new Expense(UUID.randomUUID(), new Money(450L, 2), "Streaming Service", OffsetDateTime.now().minusDays(3), "CREDIT_CARD", "Netflix", "MONTHLY", "Subscription for Netflix premium plan", "Marketing"),
      new Expense(UUID.randomUUID(), new Money(8700L, 2), "Dining Out", OffsetDateTime.now().minusDays(6), "CASH", "The Fancy Steakhouse", "OCCASIONAL", "Anniversary dinner with family", "Technology"),
      new Expense(UUID.randomUUID(), new Money(2500L, 2), "Fuel", OffsetDateTime.now().minusDays(4), "DEBIT_CARD", "Shell Gas Station", "WEEKLY", "Filled up the car with gasoline", "Marketing")
    );

    expenseRepository.saveAll(exampleExpenses);
  }

}
