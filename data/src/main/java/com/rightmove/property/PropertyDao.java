package com.rightmove.property;

import java.util.Optional;
import java.util.Set;

/**
 * A repository in charge of Property data.
 */
public interface PropertyDao {

	/**
	 * Gets a property by its reference Id.
	 * @param reference The reference Id of the property we wish to return.
	 * @return an {@code Optional} {@link PropertyEntity}. If no property can be found by the given reference Id will return empty.
	 */
	Optional<PropertyEntity> getOne(long reference);

	/**
	 * @return A {@code Set} of all the properties currently stored in this repository.
	 */
	Set<PropertyEntity> getAll();

	/**
	 * Clears all the properties currently stored in this repository.
	 */
	void clear();

	/**
	 * Saves the given {@link PropertyEntity} to the repository. If a property entity with the save reference Id already exists, it will be overridden.
	 * @param propertyEntity The {@code PropertyEntity} to save to the repository.
	 */
	void save(PropertyEntity propertyEntity);
}
