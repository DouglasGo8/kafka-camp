package section07.consumer.groups.messaging.producer;

import section07.consumer.groups.service.CommodityService;
import lombok.NoArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CommodityProducer extends RouteBuilder {
  @Override
  public void configure() {
    //

    //from("direct:commodity") // Commodity Model
    from("{{app.timer-endpoint}}")
            .bean(CommodityService::new)
            .split(body())
              .marshal().json(JsonLibrary.Jackson)
              .log("${body}")
              .to("{{app.kafka-endpoint}}")
            .end();
  }
}
