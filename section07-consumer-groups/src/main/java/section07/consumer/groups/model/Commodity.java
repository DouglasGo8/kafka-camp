package section07.consumer.groups.model;

public record Commodity(String name, double price, String measurement, long timestamp) {
  // Math.round(price * 100d) / 100d;
}
