package section08.deadletter.topic.mediation.producer;

import lombok.NoArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;
import section08.deadletter.topic.service.InvoiceGenerator;

@Component
@NoArgsConstructor
public class ProducerInvoice extends RouteBuilder {
  @Override
  public void configure() {


    // Kafka KEY based on an image type

    from("{{app.timer-endpoint}}").routeId("DeadLetterTopicProducerRoute")
            .bean(InvoiceGenerator::new)
            .split(body()).streaming()
              .marshal().json(JsonLibrary.Jackson)
              .setHeader(KafkaConstants.KEY, jsonpath("$.amount")) // partition randomization
             .to("{{app.kafka-endpoint}}")
              //.log(LoggingLevel.INFO, "${body}")
            .end();

  }
}
