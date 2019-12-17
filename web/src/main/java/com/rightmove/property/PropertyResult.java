package com.rightmove.property;

import com.rightmove.property.data.Property;

import java.util.List;

public class PropertyResult {

	private List<Property> properties;

	public PropertyResult(List<Property> properties) {
		this.properties = properties;
	}

	public List<Property> getProperties() {
		return properties;
	}
}
