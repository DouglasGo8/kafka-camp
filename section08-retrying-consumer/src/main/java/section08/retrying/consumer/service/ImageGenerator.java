package section08.retrying.consumer.service;

import lombok.NoArgsConstructor;
import org.apache.camel.Handler;
import org.springframework.stereotype.Service;
import section08.retrying.consumer.model.Image;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;


@Service
@NoArgsConstructor
public class ImageGenerator {

  static final AtomicInteger counter = new AtomicInteger();

  @Handler
  public Image[] produceImage() {

    final var type = new String[]{"jpg", "svg", "png"};

    //
    return new Image[]{
            new Image(ThreadLocalRandom.current().nextLong(10, 10_000),
                    "thumb-image-" + counter.incrementAndGet(),
                    type[ThreadLocalRandom.current().nextInt(0, type.length)]),
            //
            new Image(ThreadLocalRandom.current().nextLong(10, 10_000),
                    "thumb-image-" + counter.incrementAndGet(),
                    type[ThreadLocalRandom.current().nextInt(0, type.length)]),
            //
            new Image(ThreadLocalRandom.current().nextLong(10, 10_000),
                    "thumb-image-" + counter.incrementAndGet(),
                    type[ThreadLocalRandom.current().nextInt(0, type.length)]),
            //
            new Image(ThreadLocalRandom.current().nextLong(10, 10_000),
                    "thumb-image-" + counter.incrementAndGet(),
                    type[ThreadLocalRandom.current().nextInt(0, type.length)]),
            //
            new Image(ThreadLocalRandom.current().nextLong(10, 10_000),
                    "thumb-image-" + counter.incrementAndGet(),
                    type[ThreadLocalRandom.current().nextInt(0, type.length)]),
    };
  }
}
