package com.rightmove.property;

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
