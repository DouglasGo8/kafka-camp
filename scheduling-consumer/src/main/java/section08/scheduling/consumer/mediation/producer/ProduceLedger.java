package section08.scheduling.consumer.mediation.producer;

import lombok.NoArgsConstructor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ProduceLedger extends RouteBuilder {

  @Override
  public void configure() {

    from("{{app.timer-endpoint}}").routeId("ProducerLedgerRouter")
            .transform(simple("Ledger___id:::${uuid(short)}"))
            //.split(body()).streaming(true)
            //.marshal().json(JsonLibrary.Jackson)
            //.to("{{app.kafka-endpoint}}")
            .log(LoggingLevel.INFO, "${body}")
            .end(); //.log("${body}")

    from("cron://schedulerConsumersStartGroup?schedule=*/10+*+*+?+*+*").routeId("PeriodicConsumerStartRouter")
            .log(LoggingLevel.INFO, "Kafka Topic Consumer will be started...")
            .to("controlbus:route?routeId=SchedulerConsumerTimerRouter&action=start")
            .end();

    from("cron://schedulerConsumersStopGroup?schedule=10/30+*+*+?+*+*").routeId("PeriodicConsumerStopRouter")
            .log(LoggingLevel.INFO, "Kafka Topic Consumer will be stoped...")
            .to("controlbus:route?routeId=SchedulerConsumerTimerRouter&action=stop")
            .end();

  }
}
