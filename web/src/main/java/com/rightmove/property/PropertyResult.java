package com.rightmove.property;

import com.rightmove.property.data.PropertyEntity;

import java.util.List;

public class PropertyResult {

	private List<DisplayProperty> properties;

	public PropertyResult(List<DisplayProperty> properties) {
		this.properties = properties;
	}

	public List<DisplayProperty> getProperties() {
		return properties;
	}
}
