package com.company.showtime;

import com.company.showtime.controller.ApiController;
import com.company.showtime.dao.ApiDAOImpl;
import com.company.showtime.service.ApiServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ApiControllerTests {


    private ApiController apiController;
    private ApiServiceImpl apiService;
    private ApiDAOImpl apiDAO;

    // These tests are done under the condition that the Api is connected to the dummy network

    public ApiControllerTests(){
        ApiDAOImpl apiDAO = new ApiDAOImpl();
        this.apiDAO = apiDAO;
        ApiServiceImpl apiService = new ApiServiceImpl(apiDAO);
        this.apiService = apiService;
        ApiController apiController = new ApiController(apiService);
        this.apiController = apiController;
    }

    // Test to see if API connects to HTTP client correctly
    @Test
    @DisplayName("Api No Content Connectivity Test")
    public void apiConnectionTest(){
        String method = "cinemaShowTimes";
        int id = 0123;
        String[] apiResponse = apiController.ApiCaller(method,id);
        assertEquals("204",apiResponse[1]);
    }

}
