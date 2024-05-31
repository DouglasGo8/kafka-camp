package section07.idempotency.messaging.producer;

import lombok.NoArgsConstructor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;
import section07.idempotency.service.PurchaseGenerator;

@Component
@NoArgsConstructor
public class PurchaseRequestProducer extends RouteBuilder {
  @Override
  public void configure() throws Exception {
    from("{{app.timer-endpoint}}").routeId("PurchaseRequestProducer")
            .transform(method(PurchaseGenerator.class))
            .split(body()).streaming().parallelProcessing()
              .marshal().json(JsonLibrary.Jackson)
              .log(LoggingLevel.INFO, "${body}")
              .setHeader(KafkaConstants.KEY, jsonpath("$.prNumber")) //
              .to("{{app.kafka-endpoint}}")
            .end();
  }
}
