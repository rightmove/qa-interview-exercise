package com.rightmove.property;

import com.rightmove.property.data.PropertyDao;
import com.rightmove.property.data.PropertyEntity;
import com.rightmove.property.data.PropertyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SearchPropertyServiceImpl implements SearchPropertyService {

    private PropertyDao propertyDao;

    @Autowired
    public SearchPropertyServiceImpl(PropertyDao propertyDao){
        this.propertyDao = propertyDao;
    }

    @Override
    public PropertyResult retrievePropertiesByPostcode(String postcode) {
        List<PropertyEntity> properties = propertyDao.getAll().stream()
                .filter(propertyEntity -> postcode.equals(propertyEntity.getPostcode())).collect(Collectors.toList());
        PropertyResult propertyResult = new PropertyResult(properties);
        return propertyResult;
    }

}
