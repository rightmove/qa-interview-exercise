package com.rightmove.property;

import com.rightmove.property.data.PropertyEntity;

import java.util.Set;

public interface SearchPropertyService {
    public PropertyResult retrievePropertiesByPostcode(String postcode);
}
