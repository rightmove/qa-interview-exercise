package com.rightmove.property;

import com.rightmove.property.data.PropertyDao;
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
        return new PropertyResult(
                propertyDao.getAll().stream()
                .filter(propertyEntity -> postcode.equals(propertyEntity.getPostcode()))
                .map(propertyEntityToDisplayPropertyConverter::convert)
                .collect(Collectors.toList()));
    }

}
