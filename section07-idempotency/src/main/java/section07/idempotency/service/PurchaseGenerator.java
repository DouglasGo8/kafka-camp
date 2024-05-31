package section07.idempotency.service;

import lombok.NoArgsConstructor;
import org.apache.camel.Handler;
import org.springframework.stereotype.Service;
import section07.idempotency.model.PurchaseRequest;

import java.util.UUID;

@Service
@NoArgsConstructor
public class PurchaseGenerator {


  @Handler
  public PurchaseRequest[] genPurchase() {

    return new PurchaseRequest[]{

            PurchaseRequest.builder()
                    .id(5551)
                    .prNumber("PR-First")
                    .amount(991)
                    .currency("USD")
                    .build(),

            PurchaseRequest.builder()
                    .id(5552)
                    .prNumber("PR-Second")
                    .amount(992)
                    .currency("USD")
                    .build(),

            PurchaseRequest.builder()
                    .id(5553)
                    .prNumber("PR-Third")
                    .amount(993)
                    .currency("USD")
                    .build(),
    };
  }

}
