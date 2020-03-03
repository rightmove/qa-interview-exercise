package com.rightmove.property.data;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class PropertyDaoImplTest {

	private PropertyDaoImpl propertyDao;

	@BeforeEach
	void setup() {
	    propertyDao = new PropertyDaoImpl();
	}

	@AfterEach
	void tearDown() {
	    propertyDao.clear();
	}

	@Test
	void shouldSaveProperty() {
		PropertyEntity property = new PropertyEntity(1L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);
		propertyDao.save(property);

		Optional<PropertyEntity> one = propertyDao.getOne(1L);

		assertThat(one).isPresent();
		assertThat(one.get().getId()).isEqualTo(1L);
	}

	@Test
	void shouldReturnOptionalEmptyWhenPropertyNotFound() {
		Optional<PropertyEntity> one = propertyDao.getOne(1L);

		assertThat(one).isNotPresent();
	}

	@Test
	void shouldReturnDifferentList() {
		PropertyEntity property = new PropertyEntity(1L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);
		propertyDao.save(property);

		Set<PropertyEntity> propertyEntityList = propertyDao.getAll();

		propertyEntityList.add(new PropertyEntity(2L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT));

		Set<PropertyEntity> result = propertyDao.getAll();

		assertThat(result).hasSize(1);
	}

	@Test
	void shouldUpdateProperty() {
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
	void shouldDeleteAllProperties() {
		PropertyEntity property = new PropertyEntity(1L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);

		propertyDao.save(property);

		propertyDao.clear();

		Set<PropertyEntity> propertyEntities = propertyDao.getAll();

		assertThat(propertyEntities).isEmpty();
	}

	@Test
	void shouldGetAll() {
		PropertyEntity property = new PropertyEntity(1L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);
		PropertyEntity property2 = new PropertyEntity(2L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);

		propertyDao.save(property);
		propertyDao.save(property2);

		Set<PropertyEntity> propertyEntities = propertyDao.getAll();

		assertThat(propertyEntities).hasSize(2);
	}

	@Test
	void shouldReturnEmptyListWhenNoProperties() {
		assertThat(propertyDao.getAll()).isEmpty();
	}
}