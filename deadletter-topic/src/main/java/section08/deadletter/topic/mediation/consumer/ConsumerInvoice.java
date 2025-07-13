package section08.deadletter.topic.mediation.consumer;

import lombok.NoArgsConstructor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ConsumerInvoice extends RouteBuilder {

  @Override
  public void configure() {

    onException(IllegalArgumentException.class)
            // --------------------------------------------------
            .asyncDelayedRedelivery()
            .maximumRedeliveries(2)
            .redeliveryDelay(2000)
            .retryAttemptedLogLevel(LoggingLevel.WARN)
            // ------------------------------------------------------------
            .setHeader(KafkaConstants.KEY, jsonpath("$.amount")) // partition randomization
            .to("{{app.kafka-endpoint-dlq}}")
   .end();


    from("{{app.kafka-endpoint}}").routeId("DeadLetterTopicConsumerRouter")
            .choice().when().jsonpath("$.[?(@.amount < 100)]")
              .log(LoggingLevel.WARN, "Invalid Amount ${headers[kafka.PARTITION]}-${body}")
              .throwException(new IllegalArgumentException("Invalid Amount"))
            .otherwise()
              .log(LoggingLevel.INFO, "Message received from Kafka : ${body}-${threadName}")
              .log(LoggingLevel.INFO, "    on the topic ${headers[kafka.TOPIC]}")
              .log(LoggingLevel.INFO, "    on the partition ${headers[kafka.PARTITION]}")
              .log(LoggingLevel.INFO, "    with the offset ${headers[kafka.OFFSET]}")
              .log(LoggingLevel.INFO, "    with the key ${headers[kafka.KEY]}")
              //.unmarshal().json(JsonLibrary.Jackson, FoodOrder.class)
              //.log("Transformed Kafka Message in CarLocation Bean on CgCarLocationAll Received :: ${body.item}-${body.amount}")
            .end();



    from("{{app.kafka-endpoint-dlq}}").routeId("DeadLetterTopicDLQConsumerRouter")
            .log(LoggingLevel.INFO, "Message received from DLQ : ${body}-${threadName}")
            .log(LoggingLevel.INFO, "    on the topic ${headers[kafka.TOPIC]}")
            .log(LoggingLevel.INFO, "    on the partition ${headers[kafka.PARTITION]}")
            .log(LoggingLevel.INFO, "    with the offset ${headers[kafka.OFFSET]}")
            .log(LoggingLevel.INFO, "    with the key ${headers[kafka.KEY]}")
            //.unmarshal().json(JsonLibrary.Jackson, FoodOrder.class)
            //.log("Transformed Kafka Message in CarLocation Bean on CgCarLocationAll Received :: ${body.item}-${body.amount}")
            .end();
  }
}
