package com.rightmove;


import com.rightmove.property.PropertyDao;
import com.rightmove.property.PropertyEntity;
import com.rightmove.property.PropertyType;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

	@Autowired
	private PropertyDao propertyDao;

	@LocalServerPort
	private int serverPort;

	@Before
	public void setup() throws Exception {
		RestAssured.port = serverPort;
		RestAssured.config = RestAssured.config().logConfig(LogConfig.logConfig());
	}

	@Test
	public void shouldFindPropertyByPostcode() {
		PropertyEntity property = new PropertyEntity(1L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);
		PropertyEntity property2 = new PropertyEntity(2L,1_000_000L,7,2,"12","Richard Lane", "London","W1F 3FT", PropertyType.DETACHED);
		propertyDao.save(property);
		propertyDao.save(property2);

		JsonPath jsonPath = given()
				.when()
				.accept(ContentType.JSON)
				.queryParam("postcode", "W1D 3QU")
				.get("/property")
				.then()
				.statusCode(HttpStatus.SC_OK)
				.extract()
				.jsonPath();

		assertThat(jsonPath.getList("properties")).hasSize(1);
		assertThat(jsonPath.getLong("properties[0].reference")).isEqualTo(1L);
	}

	@Test
	public void shouldNotFindPropertyWherePostcodeNotFound() throws Exception {
		PropertyEntity property = new PropertyEntity(1L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);
		PropertyEntity property2 = new PropertyEntity(2L,1_000_000L,7,2,"12","Richard Lane", "London","W1F 3FT", PropertyType.DETACHED);
		propertyDao.save(property);
		propertyDao.save(property2);

		JsonPath jsonPath = given()
				.when()
				.accept(ContentType.JSON)
				.queryParam("postcode", "WA11 9RW")
				.get("/property")
				.then()
				.statusCode(HttpStatus.SC_OK)
				.extract()
				.jsonPath();

		assertThat(jsonPath.getList("properties")).hasSize(0);
	}
}