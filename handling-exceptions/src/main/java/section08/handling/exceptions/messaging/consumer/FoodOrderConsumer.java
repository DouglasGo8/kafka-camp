package section08.handling.exceptions.messaging.consumer;


import lombok.NoArgsConstructor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class FoodOrderConsumer extends RouteBuilder {
  @Override
  public void configure() {

    // or Global Exception Interception
    onException(IllegalArgumentException.class)
            .log(LoggingLevel.ERROR, "Food order error, sending to Elasticsearch: ${body}-${exception.message}")
            //.to(ELK)
            .end();

    from("{{app.kafka-endpoint}}").routeId("HandlingExceptionRouter")
            .choice().when().jsonpath("$.[?(@.amount < 50)]")
              .throwException(new IllegalArgumentException("Order amount illegal quantity!!!"))
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
