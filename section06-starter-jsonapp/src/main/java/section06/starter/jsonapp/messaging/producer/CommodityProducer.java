package section06.starter.jsonapp.messaging.producer;

import lombok.NoArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CommodityProducer extends RouteBuilder {
  @Override
  public void configure() {
    //.bean(CommodityService::new)
  }


}
