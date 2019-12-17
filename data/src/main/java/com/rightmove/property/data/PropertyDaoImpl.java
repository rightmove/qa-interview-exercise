package com.rightmove.property.data;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * A simple in memory implementation of the {@link PropertyDao}.
 */
@Component
public class PropertyDaoImpl implements PropertyDao {

	private static Map<Long, Property> properties = new HashMap<>();

	@Override
	public Optional<Property> getOne(long id) {
		return Optional.ofNullable(properties.get(id));
	}

	@Override
	public Set<Property> getAll() {
		return new HashSet<>(properties.values());
	}

	@Override
	public void clear() {
		properties.clear();
	}

	@Override
	public void save(Property propertyEntity) {
		properties.put(propertyEntity.getId(), propertyEntity);
	}
}
