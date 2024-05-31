package section07.consumer.groups.service;

import section07.consumer.groups.model.Commodity;
import lombok.NoArgsConstructor;
import org.apache.camel.Handler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;


@Service
@NoArgsConstructor
public class CommodityService {
  static final String GOLD = "gold";
  static final String COOPER = "cooper";
  static final Map<String, Commodity> COMMODITY_BASE = new HashMap<>();
  //
  static final double MAX_ADJUSTMENT = 1.05d;
  static final double MIN_ADJUSTMENT = 0.95d;

  //
  static {
    var timestamp = System.currentTimeMillis();
    //
    COMMODITY_BASE.put(COOPER, new Commodity(COOPER, 5_900.57d, "tonne", timestamp));
    COMMODITY_BASE.put(GOLD, new Commodity(GOLD, 1_895.29, "ounce", timestamp));
  }

  @Handler
  public List<Commodity> createDummyCommodities() {
    var result = new ArrayList<Commodity>();
    COMMODITY_BASE.keySet().forEach(c -> result.add(this.createDummyCommodity(c)));
    return result;
  }

  private Commodity createDummyCommodity(String name) {
    if (!COMMODITY_BASE.containsKey(name)) {
      throw new IllegalArgumentException("Invalid commodity: " + name);
    }
    var commodity = COMMODITY_BASE.get(name);
    var basePrice = commodity.price();
    var newPrice = basePrice * ThreadLocalRandom.current().nextDouble(MIN_ADJUSTMENT, MAX_ADJUSTMENT);
    // Immutable
    return new Commodity(commodity.name(), newPrice,
            commodity.measurement(),
            System.currentTimeMillis());
  }


}
