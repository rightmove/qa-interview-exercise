package com.rightmove.property;

import com.rightmove.property.data.Property;
import com.rightmove.property.data.PropertyDao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PropertyService {

	private PropertyDao propertyDao;


	public PropertyService(PropertyDao propertyDao) {
		this.propertyDao = propertyDao;
	}

	public List<Property> search(String postcode,int minBedrooms) {

		List<Property> allProperties = new ArrayList(propertyDao.getAll());


		// for ( create incrementer; condition to continue; what to do at the end of each lap)

		List<Property> matches = new ArrayList<>();

		for (int i = 0; i < allProperties.size();i ++) {



			Property currentProperty = allProperties.get(i);

			String currentPropertyPostcode = currentProperty.getPostcode();

			if (currentPropertyPostcode == postcode && currentProperty.getBedrooms() >= minBedrooms) {
				matches.add(currentProperty);
			}

		}



		return matches;
	}



	public Property getById(long propertyId) {

		List<Property> allProperties = new ArrayList(propertyDao.getAll());

		for (Property currentProperty: allProperties){

			if(currentProperty.getId()== propertyId) {
				return currentProperty;
			}
		}

		return null;
	}
}
