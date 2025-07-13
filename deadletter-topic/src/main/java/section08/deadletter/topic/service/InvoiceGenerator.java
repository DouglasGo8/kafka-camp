package section08.deadletter.topic.service;

import lombok.NoArgsConstructor;
import org.apache.camel.Handler;
import org.springframework.stereotype.Service;
import section08.deadletter.topic.model.Invoice;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@NoArgsConstructor
public class InvoiceGenerator {

  @Handler
  public List<Invoice> produceInvoices() {

    final int origin = 1, bound = 1_000;
    //
    return IntStream.rangeClosed(0, 6)
            // 0..6 -----------------------------------------------------------
            .mapToObj(rng -> new Invoice("INV-" + rng,
                    ThreadLocalRandom.current().nextInt(origin, bound),
                    "USD"))
            // boxed ----------------------------------------------------------------
            .collect(Collectors.toList());

  }
}
