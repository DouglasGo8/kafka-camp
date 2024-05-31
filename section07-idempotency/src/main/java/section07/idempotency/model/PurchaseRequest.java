package section07.idempotency.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
public class PurchaseRequest {

  private int id;
  private int amount;
  private String prNumber;
  private String currency;


}
