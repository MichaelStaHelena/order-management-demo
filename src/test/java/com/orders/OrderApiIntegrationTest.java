package com.orders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class OrderApiIntegrationTest {
  @Autowired MockMvc mockMvc;

  @Test
  void crudSummaryAndCsvUseTheSameOrderData() throws Exception {
    String create =
        "{\"customerName\":\"Ada\",\"productName\":\"Notebook\",\"quantity\":2,\"status\":\"PENDING\"}";
    String update =
        "{\"customerName\":\"Ada\",\"productName\":\"Notebook Pro\",\"quantity\":3,\"status\":\"PROCESSING\"}";
    String orderUrl =
        mockMvc
            .perform(post("/api/orders").contentType(MediaType.APPLICATION_JSON).content(create))
            .andExpect(status().isCreated())
            .andReturn()
            .getResponse()
            .getHeader("Location");
    mockMvc
        .perform(get(orderUrl))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.productName").value("Notebook"));
    mockMvc
        .perform(put(orderUrl).contentType(MediaType.APPLICATION_JSON).content(update))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.quantity").value(3));
    mockMvc
        .perform(get(orderUrl.replace("/api/orders", "/api/admin/orders") + "/summary"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("PROCESSING"));
    mockMvc
        .perform(get("/api/admin/orders/export"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("Notebook Pro")));
    mockMvc.perform(delete(orderUrl)).andExpect(status().isNoContent());
    mockMvc.perform(get(orderUrl)).andExpect(status().isNotFound());
  }

  @Test
  void rejectsInvalidInputWithReadableFieldErrors() throws Exception {
    mockMvc
        .perform(
            post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"customerName\":\"\",\"productName\":\"\",\"quantity\":0}"))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.fieldErrors.customerName").exists())
        .andExpect(jsonPath("$.fieldErrors.quantity").exists());
  }

  @Test
  void cancelsAnActiveOrderAndKeepsItVisibleToAdminAndExports() throws Exception {
    String pendingOrder = "{\"customerName\":\"Ada\",\"productName\":\"Notebook\",\"quantity\":2}";
    String orderUrl =
        mockMvc
            .perform(
                post("/api/orders").contentType(MediaType.APPLICATION_JSON).content(pendingOrder))
            .andExpect(status().isCreated())
            .andReturn()
            .getResponse()
            .getHeader("Location");

    mockMvc
        .perform(post(orderUrl + "/cancel"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("CANCELLED"));
    mockMvc
        .perform(post(orderUrl + "/cancel"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("CANCELLED"));
    mockMvc
        .perform(get(orderUrl.replace("/api/orders", "/api/admin/orders") + "/summary"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("CANCELLED"));
    mockMvc
        .perform(get("/api/admin/orders/export"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("CANCELLED")));
  }

  @Test
  void cannotCancelAnOrderThatHasAlreadyShipped() throws Exception {
    String shippedOrder =
        "{\"customerName\":\"Ada\",\"productName\":\"Notebook\",\"quantity\":2,\"status\":\"SHIPPED\"}";
    String orderUrl =
        mockMvc
            .perform(
                post("/api/orders").contentType(MediaType.APPLICATION_JSON).content(shippedOrder))
            .andExpect(status().isCreated())
            .andReturn()
            .getResponse()
            .getHeader("Location");

    mockMvc
        .perform(post(orderUrl + "/cancel"))
        .andExpect(status().isConflict())
        .andExpect(jsonPath("$.message").value(containsString("cannot be cancelled")));
  }
}
