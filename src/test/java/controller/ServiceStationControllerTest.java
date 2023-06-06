package controller;

import static org.mockito.Mockito.any;

import com.epam.ServiceStation.controller.ServiceStationController;

import com.epam.ServiceStation.entity.VehicleDTO;
import com.epam.ServiceStation.service.ServiceStationService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.epam.ServiceStation.service.ServiceStationServiceImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class ServiceStationControllerTest {
    @Mock
    ServiceStationService service;

    @InjectMocks
    private ServiceStationController serviceStationController;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        this.mockMvc= MockMvcBuilders.standaloneSetup(serviceStationController).build();
    }

    @Test
    public void doServicingTest_WithSuccessResp() throws Exception {
        String vehicle= "{\"registrationNumber\": \"AP23AB2232\", \"expiredate\" : \"2023-10-31\"," +
                "\"chassisNumber\": \"E3121321\", \"vehicleType\" : \"CAR\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/vehicleService")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(vehicle))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void doServicingTest_WithBadRequest() throws Exception {
        String vehicle= "{\"expiredate\" : \"2023-10-31\"," +
                "\"chassisNumber\": \"E3121321\", \"vehicleType\" : \"CAR\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/vehicleService")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(vehicle))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void getServiceHistoryTest() throws Exception {
        String vehicle= "{\"expiredate\" : \"2023-10-31\"," +
                "\"chassisNumber\": \"E3121321\", \"vehicleType\" : \"CAR\"}";
        mockMvc.perform(MockMvcRequestBuilders.get("/serviceHistory/ap23")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


}

