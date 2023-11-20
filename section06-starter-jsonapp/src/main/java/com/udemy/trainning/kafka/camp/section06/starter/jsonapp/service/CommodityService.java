package com.udemy.trainning.kafka.camp.section06.starter.jsonapp.service;

import com.udemy.trainning.kafka.camp.section06.starter.jsonapp.model.Commodity;
import lombok.NoArgsConstructor;
import org.apache.camel.Handler;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class CommodityService {

  private static final String GOLD = "gold";
  private static final String COOPER = "cooper";
  //
  private static final double MIN_ADJUSTMENT = 0.95d;
  private static final double MAX_ADJUSTMENT = 1.05d;
  //
  private static final Map<String, Commodity> COMMODITY_BASE = new HashMap<>();

  static {
    var timestamp = System.currentTimeMillis();
    //
    COMMODITY_BASE.put(GOLD, new Commodity(timestamp, 1_895.29d, "tonne", GOLD));
    COMMODITY_BASE.put(COOPER, new Commodity(timestamp, 5_900.57d, "ounce", COOPER));
  }

  private Commodity createDummyCommodity(String name) {
    if (!COMMODITY_BASE.containsKey(name)) {
      throw new IllegalArgumentException("Invalid commodity :" + name);
    }
    //
    var commodity = COMMODITY_BASE.get(name);
    var basePrice = commodity.price();
    var newPrice = basePrice * ThreadLocalRandom.current().nextDouble(MIN_ADJUSTMENT, MAX_ADJUSTMENT);
    //
    return new Commodity(0, newPrice, "", "");
  }

  @Handler
  public List<Commodity> dummiesCommodities() {
    return COMMODITY_BASE.keySet()
            .stream()
            .map(this::createDummyCommodity)
            .collect(Collectors.toList());
  }
}
