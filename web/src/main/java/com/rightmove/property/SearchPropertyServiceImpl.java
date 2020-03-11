package com.rightmove.property;

import com.rightmove.property.data.PropertyDao;
import com.rightmove.property.data.PropertyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class SearchPropertyServiceImpl implements SearchPropertyService {

    private final PropertyDao propertyDao;
    private final PropertyEntityToDisplayPropertyConverter propertyEntityToDisplayPropertyConverter;

    @Autowired
    public SearchPropertyServiceImpl(PropertyDao propertyDao, PropertyEntityToDisplayPropertyConverter propertyEntityToDisplayPropertyConverter){
        this.propertyDao = propertyDao;
        this.propertyEntityToDisplayPropertyConverter = propertyEntityToDisplayPropertyConverter;
    }

    @Override
    public PropertyResult retrievePropertiesByPostcode(String postcode) {
        if (isValidPostcode(postcode)){
            return new PropertyResult(
                    propertyDao.getByPostcode(postcode)
                            .stream()
                            .map(propertyEntityToDisplayPropertyConverter::convert)
                            .collect(Collectors.toList()));
        } else {
            throw new RuntimeException("not valid postcode");
        }
    }

    private boolean isValidPostcode(String postcode){
        if (postcode.length() > 100) return false;
        return true;
    }

}
