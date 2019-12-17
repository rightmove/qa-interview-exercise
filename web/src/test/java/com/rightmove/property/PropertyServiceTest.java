package com.rightmove.property;

import com.rightmove.property.data.DataPopulator;
import com.rightmove.property.data.PropertyDao;
import com.rightmove.property.data.PropertyDaoImpl;
import com.rightmove.property.data.Property;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class PropertyServiceTest {

	private PropertyDao propertyDao = new PropertyDaoImpl();

	private PropertyService underTest = new PropertyService(propertyDao);

	@Before
	public void setup() throws Exception {
		DataPopulator dataPopulator = new DataPopulator(propertyDao);
		dataPopulator.insertPropertyData();
	}

	@Test
	public void shouldSearch() throws Exception {

		// given
		String postcode = "MK3 6AA";
		int minBedrooms = 2;

		// when
		List<Property> propertiesInMK36AA = underTest.search(postcode, minBedrooms);

		// then
		assertThat(propertiesInMK36AA).hasSize(1);

	}


	@Test
	public void shouldGetById() throws Exception {

		// given
		long propertyId = 2;

		// when
		Property property = underTest.getById(propertyId);

		// then
		assertThat(property.getId()).isEqualTo(2);

	}
}