package section06.starter.jsonapp.messaging.producer;

import section06.starter.jsonapp.model.Employee;
import lombok.NoArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class EmployeeProducer extends RouteBuilder {
  @Override
  public void configure() {
    from("{{app.timer-endpoint}}")
            .transform(method(Employee.class))
            .marshal().json(JsonLibrary.Jackson)
            //.log("${body}")
            //.setHeader(KafkaConstants.KEY, constant("my-key"))
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
}
