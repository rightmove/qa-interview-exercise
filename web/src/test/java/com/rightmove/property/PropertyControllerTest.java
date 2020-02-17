package com.rightmove.property;

import com.rightmove.property.data.PropertyEntity;
import com.rightmove.property.data.PropertyType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.web.client.HttpClientErrorException;
import org.junit.runner.RunWith;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.junit.Assert.*;

public class PropertyControllerTest {

    private MockMvc mockMvc;
    private PropertyController propertyController;
    private SearchPropertyService searchPropertyService;
    private PropertyResult propertyResult;

    @Before
    public void setUp() {
        searchPropertyService = mock(SearchPropertyService.class);
        propertyController = new PropertyController(searchPropertyService);
        mockMvc = standaloneSetup(propertyController).build();
    }

    @Test
    public void test1(){
        String postcode = "W1D 3QU";
        List<PropertyEntity> properties = new ArrayList<>();
        properties.add(new PropertyEntity(1L,1000000L,7,2,"12","Richard Lane","London","W1F 3ER", PropertyType.DETACHED));
        PropertyResult propertyResult = new PropertyResult(properties);
        when(searchPropertyService.retrievePropertiesByPostcode(any())).thenReturn(propertyResult);
        given().
                mockMvc(mockMvc)
                .param("postcode", postcode)
                .when()
                .get("/property")
                .then()
                .statusCode(HttpServletResponse.SC_OK);

    }

    @Test
    public void test2(){
        String postcode = "W1D 3QU";
        propertyController.getPropertiesByPostcode(postcode);
        verify(searchPropertyService, times(1)).retrievePropertiesByPostcode(postcode);
    }

    @Test
    public void test3(){
        String postcode = "";
        propertyController.getPropertiesByPostcode(postcode);
        verify(searchPropertyService, times(0)).retrievePropertiesByPostcode(postcode);
    }

}
