package com.rightmove.property;

import java.util.List;

public class PropertyResult {

	private List<PropertyEntity> properties;

	public PropertyResult(List<PropertyEntity> properties) {
		this.properties = properties;
	}

	public List<PropertyEntity> getProperties() {
		return properties;
	}
}
