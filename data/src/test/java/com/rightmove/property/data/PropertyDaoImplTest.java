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
		PropertyEntity property = new PropertyEntity(1L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);
		underTest.save(property);

		Optional<PropertyEntity> one = underTest.getOne(1L);

		assertThat(one).isPresent();
		assertThat(one.get().getId()).isEqualTo(1L);
	}

	@Test
	public void shouldReturnOptionalEmptyWhenPropertyNotFound() throws Exception {
		Optional<PropertyEntity> one = underTest.getOne(1L);

		assertThat(one).isNotPresent();
	}

	@Test
	public void shouldReturnDifferentList() throws Exception {
		PropertyEntity property = new PropertyEntity(1L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);
		underTest.save(property);

		Set<PropertyEntity> propertyEntityList = underTest.getAll();

		propertyEntityList.add(new PropertyEntity(2L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT));

		Set<PropertyEntity> result = underTest.getAll();

		assertThat(result).hasSize(1);
	}

	@Test
	public void shouldUpdateProperty() throws Exception {
		PropertyEntity property = new PropertyEntity(1L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);

		underTest.save(property);

		PropertyEntity propertyUpdate = new PropertyEntity(1L, 200_000L, 3, 1, "34", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);

		underTest.save(propertyUpdate);

		Optional<PropertyEntity> result = underTest.getOne(1L);

		assertThat(underTest.getAll()).hasSize(1);
		assertThat(result.get().getPrice()).isEqualTo(200_000L);
		assertThat(result.get().getId()).isEqualTo(1L);
	}

	@Test
	public void shouldDeleteAllProperties() throws Exception {
		PropertyEntity property = new PropertyEntity(1L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);

		underTest.save(property);

		underTest.clear();

		Set<PropertyEntity> propertyEntities = underTest.getAll();

		assertThat(propertyEntities).isEmpty();
	}

	@Test
	public void shouldGetAll() throws Exception {
		PropertyEntity property = new PropertyEntity(1L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);
		PropertyEntity property2 = new PropertyEntity(2L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);

		underTest.save(property);
		underTest.save(property2);

		Set<PropertyEntity> propertyEntities = underTest.getAll();

		assertThat(propertyEntities).hasSize(2);
	}

	@Test
	public void shouldReturnEmptyListWhenNoProperties() throws Exception {
		assertThat(underTest.getAll()).isEmpty();
	}
}