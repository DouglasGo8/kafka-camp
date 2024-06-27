package section08.retrying.consumer.mediation.consumer;

import lombok.NoArgsConstructor;
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
      // - Retry after 10 seconds
      // - Retry 4 times
    //onException();
  }
}
