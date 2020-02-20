package com.rightmove.property.data;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class PropertyDaoImplTest {

	private PropertyDaoImpl propertyDao;

	@Before
	public void setup() {
	    propertyDao = new PropertyDaoImpl();
	}

	@After
	public void tearDown() throws Exception {
	    propertyDao.clear();
	}

	@Test
	public void shouldSaveProperty() throws Exception {
		PropertyEntity property = new PropertyEntity(1L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);
		propertyDao.save(property);

		Optional<PropertyEntity> one = propertyDao.getOne(1L);

		assertThat(one).isPresent();
		assertThat(one.get().getId()).isEqualTo(1L);
	}

	@Test
	public void shouldReturnOptionalEmptyWhenPropertyNotFound() throws Exception {
		Optional<PropertyEntity> one = propertyDao.getOne(1L);

		assertThat(one).isNotPresent();
	}

	@Test
	public void shouldReturnDifferentList() throws Exception {
		PropertyEntity property = new PropertyEntity(1L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);
		propertyDao.save(property);

		Set<PropertyEntity> propertyEntityList = propertyDao.getAll();

		propertyEntityList.add(new PropertyEntity(2L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT));

		Set<PropertyEntity> result = propertyDao.getAll();

		assertThat(result).hasSize(1);
	}

	@Test
	public void shouldUpdateProperty() throws Exception {
		PropertyEntity property = new PropertyEntity(1L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);

		propertyDao.save(property);

		PropertyEntity propertyUpdate = new PropertyEntity(1L, 200_000L, 3, 1, "34", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);

		propertyDao.save(propertyUpdate);

		Optional<PropertyEntity> result = propertyDao.getOne(1L);

		assertThat(propertyDao.getAll()).hasSize(1);
		assertThat(result.get().getPrice()).isEqualTo(200_000L);
		assertThat(result.get().getId()).isEqualTo(1L);
	}

	@Test
	public void shouldDeleteAllProperties() throws Exception {
		PropertyEntity property = new PropertyEntity(1L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);

		propertyDao.save(property);

		propertyDao.clear();

		Set<PropertyEntity> propertyEntities = propertyDao.getAll();

		assertThat(propertyEntities).isEmpty();
	}

	@Test
	public void shouldGetAll() throws Exception {
		PropertyEntity property = new PropertyEntity(1L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);
		PropertyEntity property2 = new PropertyEntity(2L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);

		propertyDao.save(property);
		propertyDao.save(property2);

		Set<PropertyEntity> propertyEntities = propertyDao.getAll();

		assertThat(propertyEntities).hasSize(2);
	}

	@Test
	public void shouldReturnEmptyListWhenNoProperties() throws Exception {
		assertThat(propertyDao.getAll()).isEmpty();
	}
}