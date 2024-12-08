package dev.hanluc.expensetracker.expenses.infrastructure.api;

import dev.hanluc.expensetracker.expenses.infrastructure.api.dto.ExpenseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PutApiController {

  public PutApiController(

  ) {

  }

  public ResponseEntity<Void> put(String expenseId, ExpenseResponse expenseDto) {
    return null;
  }

}
