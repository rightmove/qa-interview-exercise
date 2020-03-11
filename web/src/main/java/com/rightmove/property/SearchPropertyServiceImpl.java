package com.rightmove.property;

import com.rightmove.property.data.PropertyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchPropertyServiceImpl implements SearchPropertyService {

    private final PropertyDao propertyDao;
    private final PropertyEntityToDisplayPropertyConverter converter;

    @Autowired
    public SearchPropertyServiceImpl(PropertyDao propertyDao, PropertyEntityToDisplayPropertyConverter converter){
        this.propertyDao = propertyDao;
        this.converter = converter;
    }

    @Override
    public List<DisplayProperty> retrievePropertiesByPostcode(String postcode) {
        if (isValidPostcode(postcode)){
            return propertyDao.getByPostcode(postcode)
                            .stream()
                            .map(converter::convert)
                            .collect(Collectors.toList());
        } else {
            throw new RuntimeException("not valid postcode");
        }
    }

    private boolean isValidPostcode(String postcode){
        if (postcode.length() > 100) return false;
        return true;
    }

}
