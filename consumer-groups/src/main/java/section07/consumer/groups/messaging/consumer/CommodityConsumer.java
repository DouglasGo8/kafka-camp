package section07.consumer.groups.messaging.consumer;


import section07.consumer.groups.model.Commodity;
import lombok.NoArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;


@Component
@NoArgsConstructor
public class CommodityConsumer extends RouteBuilder {
  @Override
  public void configure() {

    from("{{app.kafka-endpoint-consumer-cgdash}}").routeId("CgDashBoardConsumerRouteId")
            .log("Message received from Kafka : ${body}-${threadName}")
            .log("    on the topic ${headers[kafka.TOPIC]}")
            .log("    on the partition ${headers[kafka.PARTITION]}")
            .log("    with the offset ${headers[kafka.OFFSET]}")
            .log("    with the key ${headers[kafka.KEY]}")
            .unmarshal().json(JsonLibrary.Jackson, Commodity.class)
            .log("Transformed Kafka Message in Commodity Bean on CgDashBoardGroup Received :: ${body.name}-${body.price}")
            .end();

    from("{{app.kafka-endpoint-consumer-cgnoti}}").routeId("CgNotificationConsumerRouteId")
            .log("Message received from Kafka : ${body}-${threadName}")
            .log("    on the topic ${headers[kafka.TOPIC]}")
            .log("    on the partition ${headers[kafka.PARTITION]}")
            .log("    with the offset ${headers[kafka.OFFSET]}")
            .log("    with the key ${headers[kafka.KEY]}")
            .unmarshal().json(JsonLibrary.Jackson, Commodity.class)
            .log("Transformed Kafka Message in Commodity Bean on CgNotificationGroup Received :: ${body.name}-${body.price}")
            .end();
  }
}
