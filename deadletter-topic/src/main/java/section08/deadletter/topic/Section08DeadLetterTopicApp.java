package section08.deadletter.topic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Section08DeadLetterTopicApp {
  public static void main(String[] args) {
    SpringApplication.run(Section08DeadLetterTopicApp.class, args);
  }
}
