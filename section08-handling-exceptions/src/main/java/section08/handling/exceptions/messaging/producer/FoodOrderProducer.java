package section08.handling.exceptions.messaging.producer;

import lombok.NoArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;
import section08.handling.exceptions.model.FoodOrder;

@NoArgsConstructor
@Component
public class FoodOrderProducer extends RouteBuilder {
  @Override
  public void configure() {

    from("{{app.timer-endpoint}}")
            .bean(FoodOrder::new)
            .split(body()).streaming(true)
              .marshal().json(JsonLibrary.Jackson)
              .to("{{app.kafka-endpoint}}")
            .end(); //.log("${body}")

  }
}
