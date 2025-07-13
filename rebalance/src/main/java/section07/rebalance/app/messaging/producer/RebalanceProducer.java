package section07.rebalance.app.messaging.producer;

import lombok.NoArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;
import section07.rebalance.app.model.Counter;


@Component
@NoArgsConstructor
public class RebalanceProducer extends RouteBuilder {

  @Override
  public void configure() throws Exception {

    from("{{app.timer-endpoint}}").routeId("RebalanceProducerRouteId")
            .transform(method(Counter.class))
            //.split(body()).streaming()
            .marshal().json(JsonLibrary.Jackson)
            //.log(LoggingLevel.INFO, "${body}")
            .to("{{app.kafka-endpoint}}")
            .end();
  }


}
