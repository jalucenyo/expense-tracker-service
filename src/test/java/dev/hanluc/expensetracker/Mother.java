package dev.hanluc.expensetracker;

import static org.instancio.Select.all;
import static org.instancio.Select.field;

import java.util.Arrays;
import java.util.List;

import org.instancio.Instancio;
import org.instancio.Model;
import org.instancio.Select;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

public class Mother<T> {

  private Model<T> randomBase;

  public Mother(Model<T> randomGenerator) {
    this.randomBase = randomGenerator;
  }

  public Page<T> emptyPage() {
    return new PageImpl<>(List.of());
  }

  public Page<T> insidePage(int size) {
    return new PageImpl<>(Instancio.ofList(randomBase).size(size).create());
  }

  public List<T> insideList(int size) {
    return Instancio.ofList(randomBase).size(size).create();
  }


  public Mother<T> withNullField(String... fields) {
    final var fieldSelectors = Arrays.stream(fields).map(Select::field).toArray(org.instancio.Selector[]::new);
    randomBase = Instancio.of(randomBase)
        .set(all(fieldSelectors), null)
        .toModel();
    return this;
  }

  public Mother<T> withEmptyField(String... fields) {
    final var fieldSelectors = Arrays.stream(fields).map(Select::field).toArray(org.instancio.Selector[]::new);
    randomBase = Instancio.of(randomBase)
        .set(all(fieldSelectors), "")
        .toModel();
    return this;

  }

  public Mother<T> withFieldValue(String field, Object value) {
    randomBase = Instancio.of(randomBase)
        .set(field(field), value)
        .toModel();
    return this;
  }

  public T create() {
    return Instancio.of(randomBase).create();
  }

}
