package org.notify.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.notify.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class NotificationControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void sendNotificationSuccess() throws Exception {
        // Perform the request
        mockMvc.perform(MockMvcRequestBuilders.post("/sendNotification")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new NotificationRequest(Category.SPORTS, "Real Madrid won 4 - 0 "))))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Notification sent successfully"));
    }

    @Test
    public void sendNotificationEmptyMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/sendNotification")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new NotificationRequest(Category.SPORTS, ""))))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Message cannot be empty"));
    }

    @Test
    public void sendNotificationMissingCategory() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/sendNotification")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new NotificationRequest(null, "Real Madrid won 4 - 0"))))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Category cannot be missing"));
    }

    private String asJsonString(Object obj) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}
