package com.udemy.trainning.kafka.camp.section07.message.filter.messaging.consumer;

import com.udemy.trainning.kafka.camp.section07.message.filter.model.CarLocation;
import lombok.NoArgsConstructor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CarLocationConsumer extends RouteBuilder {
  @Override
  public void configure() {

    from("{{app.kafka-endpoint-cg-all}}").routeId("CgCarLocationAll")
            .log("Message received from Kafka : ${body}-${threadName}")
            .log("    on the topic ${headers[kafka.TOPIC]}")
            .log("    on the partition ${headers[kafka.PARTITION]}")
            .log("    with the offset ${headers[kafka.OFFSET]}")
            .log("    with the key ${headers[kafka.KEY]}")
            .unmarshal().json(JsonLibrary.Jackson, CarLocation.class)
            .log("Transformed Kafka Message in CarLocation Bean on CgCarLocationAll Received :: ${body.carId}-${body.distance}")
            .end();

    from("{{app.kafka-endpoint-cg-far}}").routeId("CgCarLocationFar")
            .log("Message received from Kafka : ${body}-${threadName}")
            .log("    on the topic ${headers[kafka.TOPIC]}")
            .log("    on the partition ${headers[kafka.PARTITION]}")
            .log("    with the offset ${headers[kafka.OFFSET]}")
            .log("    with the key ${headers[kafka.KEY]}")
            .unmarshal().json(JsonLibrary.Jackson, CarLocation.class)
            .choice().when(simple("${body.distance} < 100"))
              .log(LoggingLevel.INFO, "Transformed Kafka Message in CarLocation Bean on CgCarLocationFar Received :: ${body.carId}-${body.distance}")
            .otherwise()
              .log(LoggingLevel.INFO, "Over 100 miles")
            .end();
  }
}
