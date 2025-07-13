package section08.retrying.consumer.mediation.producer;

import lombok.NoArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;
import section08.retrying.consumer.service.ImageGenerator;

@Component
@NoArgsConstructor
public class ProducerImage extends RouteBuilder {
  @Override
  public void configure() {


    // Kafka KEY based on an image type

    from("{{app.timer-endpoint}}").routeId("RetryingProducerRouterId")
            .bean(ImageGenerator::new)
            .split(body()).streaming()
              .marshal().json(JsonLibrary.Jackson)
              .setHeader(KafkaConstants.KEY, jsonpath("$.type")) // partition randomization
              .to("{{app.kafka-endpoint}}")
              //.log(LoggingLevel.INFO, "${body}")
            .end();

  }
}
