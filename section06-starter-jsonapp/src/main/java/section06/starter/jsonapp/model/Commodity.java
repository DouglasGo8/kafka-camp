package section06.starter.jsonapp.model;


/**
 *
 * @param timestamp
 * @param price
 * @param name
 * @param measurement
 */
public record Commodity(long timestamp, double price, String name, String measurement) {

  public Commodity(long timestamp, double price, String name, String measurement) {
    this.price = Math.round(price * 100d) / 100d;
    this.timestamp = timestamp;
    //
    this.name = name;
    this.measurement = measurement;
  }
}
