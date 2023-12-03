package com.udemy.trainning.kafka.camp.section07.message.filter.service;

import com.udemy.trainning.kafka.camp.section07.message.filter.model.CarLocation;
import lombok.NoArgsConstructor;
import org.apache.camel.Handler;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
@NoArgsConstructor
public class CarLocationGenerator {


  final CarLocation[] cars = new CarLocation[]{
          new CarLocation("car-one", System.currentTimeMillis(), 0),
          new CarLocation("car-two", System.currentTimeMillis(), 110),
          new CarLocation("car-three", System.currentTimeMillis(), 95),
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
