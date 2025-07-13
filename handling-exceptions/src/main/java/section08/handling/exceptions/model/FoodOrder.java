package section08.handling.exceptions.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.camel.Handler;

import java.util.concurrent.ThreadLocalRandom;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FoodOrder {

  private int amount;
  private String item;

  @Handler
  FoodOrder[] orders() {
    return new FoodOrder[]{
            new FoodOrder(ThreadLocalRandom.current().nextInt(1, 100), "Chicken"),
            new FoodOrder(ThreadLocalRandom.current().nextInt(1, 100), "Fish"),
            new FoodOrder(ThreadLocalRandom.current().nextInt(1, 100), "Pizza"),
    };
    //
    //return foods[ThreadLocalRandom.current().nextInt(0, foods.length)];
  }
}
