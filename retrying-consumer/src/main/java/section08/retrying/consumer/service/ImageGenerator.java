package section08.retrying.consumer.service;

import lombok.NoArgsConstructor;
import org.apache.camel.Handler;
import org.springframework.stereotype.Service;
import section08.retrying.consumer.model.Image;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@NoArgsConstructor
public class ImageGenerator {

  //static final AtomicInteger counter = new AtomicInteger();

  @Handler
  public List<Image> produceImage() {

    final int origin = 100, bound = 10_000;
    final var type = new String[]{"jpg", "svg", "png", "gif", "bmp", "tiff"};
    //var images = new Image[6];
    //
    //for (int rng = 0; rng < 6; )
    //images[rng++] = new Image(ThreadLocalRandom.current().nextLong(origin, bound),
    //      "thumb-image-0" + rng, type[ThreadLocalRandom.current().nextInt(0, type.length)]);
    //
    return IntStream.rangeClosed(0, 6)
            // 0..6 ------------------------------------------------------------------------------------------
            .mapToObj(rng -> new Image(ThreadLocalRandom.current().nextLong(origin, bound),
                    "thumb-image-0" + rng,
                    type[ThreadLocalRandom.current().nextInt(0, type.length)]))
            // boxed ------------------------------------------------------------------------------------------
            .collect(Collectors.toList());

  }
}
