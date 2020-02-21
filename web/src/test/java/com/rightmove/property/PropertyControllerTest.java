package com.rightmove.property;

import com.rightmove.property.data.PropertyType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

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
        List<DisplayProperty> properties = new ArrayList<>();
        properties.add(new DisplayProperty.Builder().id(1).displayAddress("1 Dragon Street " + postcode).priceIndicator(PriceIndicator.HIGH).propertyType(PropertyType.FLAT).build());
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
