package com.rightmove.property;

import java.util.List;

public interface SearchPropertyService {
    public List<DisplayProperty> retrievePropertiesByPostcode(String postcode);
}
