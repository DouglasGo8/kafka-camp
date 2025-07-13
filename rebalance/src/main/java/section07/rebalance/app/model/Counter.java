package section07.rebalance.app.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.camel.Handler;

import java.util.concurrent.atomic.AtomicInteger;



@Builder
@NoArgsConstructor
public class Counter {

  @Getter
  private Integer fieldCounter;

  final static AtomicInteger index = new AtomicInteger();

  public Counter(Integer counter) {
    this.fieldCounter = counter;
  }

  @Handler
  Counter counter() {
    return Counter.builder()
            .fieldCounter(index.incrementAndGet())
            .build();
  }
}
