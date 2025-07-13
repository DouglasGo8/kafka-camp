package section08.retrying.consumer.mediation.consumer;

import lombok.NoArgsConstructor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ConsumerImage extends RouteBuilder {

  @Override
  public void configure() {

    // Consumer
    // - Simulate API call to convert image
    // - Simulate failed API call
    // - Retry after 2 seconds
    // - Retry 2 times
    //onException();

    onException(IllegalArgumentException.class)
            .maximumRedeliveries(2)
            .redeliveryDelay(2000)
            .backOffMultiplier(2)
            .retryAttemptedLogLevel(LoggingLevel.WARN)
   .end();


    from("{{app.kafka-endpoint}}").routeId("RetryingConsumerRouterId")
            .choice().when().jsonpath("$.[?(@.type == 'svg')]")
              .log(LoggingLevel.WARN, "SVG extension are not allowed ${headers[kafka.PARTITION]}-${body}")
              .throwException(new IllegalArgumentException("SVG extension are not allowed"))
            .otherwise()
              .log(LoggingLevel.INFO, "Message received from Kafka : ${body}-${threadName}")
              .log(LoggingLevel.INFO, "    on the topic ${headers[kafka.TOPIC]}")
              .log(LoggingLevel.INFO, "    on the partition ${headers[kafka.PARTITION]}")
              .log(LoggingLevel.INFO, "    with the offset ${headers[kafka.OFFSET]}")
              .log(LoggingLevel.INFO, "    with the key ${headers[kafka.KEY]}")
              //.unmarshal().json(JsonLibrary.Jackson, FoodOrder.class)
              //.log("Transformed Kafka Message in CarLocation Bean on CgCarLocationAll Received :: ${body.item}-${body.amount}")
            .end();
  }
}
