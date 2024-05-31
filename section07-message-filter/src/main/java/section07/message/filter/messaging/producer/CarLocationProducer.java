package section07.message.filter.messaging.producer;

import section07.message.filter.service.CarLocationGenerator;
import lombok.NoArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CarLocationProducer extends RouteBuilder {
  @Override
  public void configure() {


    from("{{app.timer-endpoint}}")
            .transform(method(CarLocationGenerator.class))
            .split(body()).streaming()
              .marshal().json(JsonLibrary.Jackson)
              //.log(LoggingLevel.INFO, "${body}")
              .to("{{app.kafka-endpoint}}")
            .end();

  }
}
