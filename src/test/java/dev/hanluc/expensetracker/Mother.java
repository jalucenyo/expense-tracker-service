package dev.hanluc.expensetracker;

import static org.instancio.Select.all;
import static org.instancio.Select.field;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import org.instancio.Instancio;
import org.instancio.Model;
import org.instancio.Select;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

public class Mother<T> {

  private final Supplier<Model<T>> randomBase;

  public Mother(Supplier<Model<T>> randomGenerator) {
    this.randomBase = randomGenerator;
  }

  public Page<T> emptyPage() {
    return new PageImpl<>(List.of());
  }

  public Page<T> insidePage(int size) {
    return new PageImpl<>(Instancio.ofList(randomBase.get()).size(size).create());
  }

  public List<T> insideList(int size) {
    return Instancio.ofList(randomBase.get()).size(size).create();
  }


  public T withNullField(String... fields) {
    final var fieldSelectors = Arrays.stream(fields).map(Select::field).toArray(org.instancio.Selector[]::new);
    return Instancio.of(randomBase.get())
        .set(all(fieldSelectors), null)
        .create();
  }

  public T withEmptyField(String... fields) {
    final var fieldSelectors = Arrays.stream(fields).map(Select::field).toArray(org.instancio.Selector[]::new);
    return Instancio.of(randomBase.get())
        .set(all(fieldSelectors), "")
        .create();
  }

  public T withFieldValue(String field, Object value) {
    return Instancio.of(randomBase.get())
        .set(field(field), value)
        .create();
  }

  public T create() {
    return Instancio.of(randomBase.get()).create();
  }

}
