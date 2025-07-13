package section07.idempotency.messaging.consumer;

import lombok.NoArgsConstructor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class PurchaseRequestConsumer extends RouteBuilder {
  @Override
  public void configure() throws Exception {
    // In session lesson, was suggested the Caffeine Cache usage
    // For Apache Camel there as whole section detailing this internal Mechanism
    // https://camel.apache.org/components/4.4.x/kafka-component.html#_using_the_kafka_idempotent_repository
    from("{{app.kafka-endpoint}}").routeId("PurchaseRequestConsumerRouteId")
            .log(LoggingLevel.INFO, "Message received from Kafka : ${body}-${threadName}")
            .log(LoggingLevel.INFO, "    on the topic ${headers[kafka.TOPIC]}")
            .log(LoggingLevel.INFO, "    on the partition ${headers[kafka.PARTITION]}")
            .log(LoggingLevel.INFO, "    with the offset ${headers[kafka.OFFSET]}")
            .log(LoggingLevel.INFO, "    with the key ${headers[kafka.KEY]}")
            //.unmarshal().json(JsonLibrary.Jackson, Counter.class)
            //.log("Transformed Kafka Message in CarLocation Bean on CgCarLocationAll Received :: ${body.carId}-${body.distance}")
            .end();
  }
}
