package section07.consumer.groups.api;

import section07.consumer.groups.service.CommodityService;
import lombok.NoArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class CommodityApi extends RouteBuilder {

  @Override
  public void configure() {
    //
    restConfiguration()
            .component("netty-http")
            .host("localhost")
            .contextPath("/api/commodity/v1")
            .port(12080)
            .bindingMode(RestBindingMode.auto);
    //

    rest().get("/all").to("direct:getAllCommodities");


    from("direct:getAllCommodities")
            .bean(CommodityService::new);
  }
}
