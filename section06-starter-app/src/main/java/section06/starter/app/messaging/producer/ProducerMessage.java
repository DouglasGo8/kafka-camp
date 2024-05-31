package section06.starter.app.messaging.producer;

import lombok.NoArgsConstructor;
import org.apache.camel.Handler;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;


@Component
@NoArgsConstructor
public class ProducerMessage extends RouteBuilder {
  @Override
  public void configure() {
    //
    from("{{app.timer-endpoint}}")
      //.transform(simple("My Message at ${date:now:yyyyMMdd HH:mm:ss}"))
      .transform(method(ProducerMessage.class))
      .log("${body}")
      .setHeader(KafkaConstants.KEY, simple("key-${random(1, 5)}")) // partition randomization
      .to("{{app.kafka-endpoint}}")
            /*.process(e -> {
              var recordMetadataList = (List<RecordMetadata>) e.getIn().getHeader(recordMedataHeader);
              if (null != recordMetadataList)
                recordMetadataList.forEach(c -> log.info("Receiving new metadata \n" +
                        "Topic - {}\n " +
                        "Offset: {}\n " +
                        "Timestamp: {}\n" +
                        "Partition: {}\n", c.topic(), c.offset(), c.timestamp(), c.partition()));
            });*/
    .end();
  }

  @Handler
  String quotes() {
    var quotes = new String[]{"diablo immortal", "diablo iV", "league of Legends"};
    return quotes[ThreadLocalRandom.current().nextInt(0, quotes.length)] + " at " + LocalDateTime.now();
  }
}
