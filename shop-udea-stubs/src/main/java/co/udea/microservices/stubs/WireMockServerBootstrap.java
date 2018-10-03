package co.udea.microservices.stubs;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import org.apache.commons.io.IOUtils;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class WireMockServerBootstrap {
  public static void main(String[] args) throws IOException {
    WireMockServer server = new WireMockServer(options()
      .port(9999)
      .notifier(new ConsoleNotifier(true)));
    server.start();

    server.stubFor(get(urlEqualTo("/v1/catalog"))
      .willReturn(aResponse()
        .withHeader("Content-Type", "application/json")
        .withBody(getBody("catalog.json"))
      )
    );

    server.stubFor(get(urlEqualTo("/v1/products/SKU-12464"))
      .willReturn(aResponse()
        .withHeader("Content-Type", "application/json")
        .withBody(getBody("product-SKU-12464.json"))
      )
    );

    server.stubFor(get(urlEqualTo("/v1/products/SKU-24642"))
      .willReturn(aResponse()
        .withHeader("Content-Type", "application/json")
        .withBody(getBody("product-SKU-24642.json"))
      )
    );

    server.stubFor(get(urlEqualTo("/v1/accounts"))
      .willReturn(aResponse()
        .withHeader("Content-Type", "application/json")
        .withBody(getBody("account.json"))
      )
    );

    server.stubFor(get(urlEqualTo("/v1/accounts/12345/orders"))
      .willReturn(aResponse()
        .withHeader("Content-Type", "application/json")
        .withBody(getBody("account-orders.json"))
      )
    );

    server.stubFor(get(urlEqualTo("/v1/orders/1"))
      .willReturn(aResponse()
        .withHeader("Content-Type", "application/json")
        .withBody(getBody("orders1.json"))
      )
    );

    server.stubFor(get(urlEqualTo("/v1/cart"))
      .willReturn(aResponse()
        .withHeader("Content-Type", "application/json")
        .withBody(getBody("cart.json"))
      )
    );

    server.stubFor(post(urlEqualTo("/v1/checkout"))
      .willReturn(aResponse()
        .withHeader("Content-Type", "application/json")
        .withBody(getBody("order-result.json"))
      )
    );

    server.stubFor(post(urlEqualTo("/v1/events"))
      .willReturn(aResponse()
        .withStatus(204)
        .withHeader("Content-Type", "application/json")
        .withBody("true")
      )
    );
  }

  private static String getBody(String name) throws IOException {
    return IOUtils.toString(WireMockServerBootstrap.class.getClassLoader().getResourceAsStream(name), "UTF-8");
  }
}
