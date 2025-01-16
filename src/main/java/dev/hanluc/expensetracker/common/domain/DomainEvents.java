package dev.hanluc.expensetracker.common.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class DomainEvents {

  private List<Object> events;

  public DomainEvents() {
    events = new ArrayList<>();
  }

  public void addEvent(Object event) {
    events.add(event);
  }

  public void pullEvents(Consumer<Object> eventCallback) {
    var eventsPulled = events;
    events = new ArrayList<>();
    eventsPulled.forEach(eventCallback);
  }

}
