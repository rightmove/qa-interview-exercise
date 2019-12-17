package com.rightmove.property.data;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class PropertyDaoImplTest {

	private PropertyDaoImpl underTest;

	@Before
	public void setup() {
	    underTest = new PropertyDaoImpl();
	}

	@After
	public void tearDown() throws Exception {
	    underTest.clear();
	}

	@Test
	public void shouldSaveProperty() throws Exception {
		Property property = new Property(1L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);
		underTest.save(property);

		Optional<Property> one = underTest.getOne(1L);

		assertThat(one).isPresent();
		assertThat(one.get().getId()).isEqualTo(1L);
	}

	@Test
	public void shouldReturnOptionalEmptyWhenPropertyNotFound() throws Exception {
		Optional<Property> one = underTest.getOne(1L);

		assertThat(one).isNotPresent();
	}

	@Test
	public void shouldReturnDifferentList() throws Exception {
		Property property = new Property(1L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);
		underTest.save(property);

		Set<Property> propertyEntityList = underTest.getAll();

		propertyEntityList.add(new Property(2L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT));

		Set<Property> result = underTest.getAll();

		assertThat(result).hasSize(1);
	}

	@Test
	public void shouldUpdateProperty() throws Exception {
		Property property = new Property(1L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);

		underTest.save(property);

		Property propertyUpdate = new Property(1L, 200_000L, 3, 1, "34", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);

		underTest.save(propertyUpdate);

		Optional<Property> result = underTest.getOne(1L);

		assertThat(underTest.getAll()).hasSize(1);
		assertThat(result.get().getPrice()).isEqualTo(200_000L);
		assertThat(result.get().getId()).isEqualTo(1L);
	}

	@Test
	public void shouldDeleteAllProperties() throws Exception {
		Property property = new Property(1L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);

		underTest.save(property);

		underTest.clear();

		Set<Property> propertyEntities = underTest.getAll();

		assertThat(propertyEntities).isEmpty();
	}

	@Test
	public void shouldGetAll() throws Exception {
		Property property = new Property(1L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);
		Property property2 = new Property(2L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);

		underTest.save(property);
		underTest.save(property2);

		Set<Property> propertyEntities = underTest.getAll();

		assertThat(propertyEntities).hasSize(2);
	}

	@Test
	public void shouldReturnEmptyListWhenNoProperties() throws Exception {
		assertThat(underTest.getAll()).isEmpty();
	}
}