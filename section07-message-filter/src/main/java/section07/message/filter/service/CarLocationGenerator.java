package section07.message.filter.service;

import section07.message.filter.model.CarLocation;
import lombok.NoArgsConstructor;
import org.apache.camel.Handler;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class CarLocationGenerator {

  private long now = System.currentTimeMillis();

  final CarLocation[] cars = new CarLocation[]{
          new CarLocation("car-one", this.now, 0),
          new CarLocation("car-two", this.now, 110),
          new CarLocation("car-three", this.now, 95),
  };

  @Handler
  CarLocation[] producerCarLocation() {

    var carOne = cars[0];
    var carTwo = cars[1];
    var carThree = cars[2];
    //
    carOne.setDistance(carOne.getDistance() + 1);
    carTwo.setDistance(carTwo.getDistance() - 1);
    carThree.setDistance(carThree.getDistance() + 1);

    //
    return new CarLocation[]{carOne, carTwo, carThree};
  }
}
