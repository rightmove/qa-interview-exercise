package com.rightmove;

import com.rightmove.datasetup.PropertyEntityBuilder;
import com.rightmove.property.data.PropertyDao;
import com.rightmove.property.data.PropertyEntity;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.junit.After;
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

	@After
	public void tearDown() {
		propertyDao.clear();
	}


}
