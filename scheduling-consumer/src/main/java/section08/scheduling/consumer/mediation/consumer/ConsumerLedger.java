package section08.scheduling.consumer.mediation.consumer;


import lombok.NoArgsConstructor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ConsumerLedger extends RouteBuilder {

  @Override
  public void configure() {


    from("timer://mySchedulingConsumer?fixedRate=true&period=5s").autoStartup(false).routeId("SchedulerConsumerTimerRouter")
            .transform(constant("Hi, Consuming a Kafka Topic..."))
            .log(LoggingLevel.INFO, "${body}")
            .end();
  }
}
