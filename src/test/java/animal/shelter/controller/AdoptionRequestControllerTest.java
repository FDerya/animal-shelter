package animal.shelter.controller;

import animal.shelter.model.AdoptionRequest;
import animal.shelter.service.AdoptionRequestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@WebMvcTest(AdoptionRequestController.class)
public class AdoptionRequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdoptionRequestService adoptionRequestService;

    @Autowired
    private ObjectMapper objectMapper;

    private AdoptionRequest nonExistingRequest;
    private AdoptionRequest existingRequest;

    @BeforeEach
    public void init() {
        nonExistingRequest = new AdoptionRequest(1, null, null, new Date(), "Available");
        existingRequest = new AdoptionRequest(2, null, null, new Date(), "Available");
    }


    @Test
    void getAdoptionByIdExisting() {
        Optional<AdoptionRequest> adoptionRequest = Optional.of(existingRequest);
        Mockito.when(adoptionRequestService.findAdoptionById(3)).thenReturn(adoptionRequest);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/adoption_request/3")
                .param("idAdoption", String.valueOf(3));
        try {
            mockMvc.perform(request)
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.idAdoption").value(3))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Available"));
        } catch (Exception exception) {
            assert (false);
        }
    }


    @Test
    public void getAdoptionByIdNonExisting() throws Exception {
        Mockito.when(adoptionRequestService.findAdoptionById(anyInt())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/adoption_request/3"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void getAllNoActivitiesInDatabase() throws Exception {
        List<AdoptionRequest> requestList = new ArrayList<>();
        Mockito.when(adoptionRequestService.findAllAdoption()).thenReturn(requestList);

        mockMvc.perform(MockMvcRequestBuilders.get("/adoption_request"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)))
                .andDo(print());
    }

    @Test
    public void getAll() throws Exception {
        List<AdoptionRequest> requestList = new ArrayList<>();
        requestList.add(nonExistingRequest);
        requestList.add(existingRequest);
        Mockito.when(adoptionRequestService.findAllAdoption()).thenReturn(requestList);

        mockMvc.perform(MockMvcRequestBuilders.get("/adoption_request"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].status").value("Available"))
                .andExpect(jsonPath("$[1].status").value("Available"))
                .andDo(print());
    }

    @Test
    public void deleteAdoption() throws Exception {
        Mockito.when(adoptionRequestService.findAdoptionById(anyInt())).thenReturn(Optional.of(existingRequest));

        mockMvc.perform(MockMvcRequestBuilders.delete("/adoption_request/delete/1"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void deleteAdoptionNonExisting() throws Exception {
        Mockito.when(adoptionRequestService.findAdoptionById(anyInt())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.delete("/adoption_request/delete/3"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
}
