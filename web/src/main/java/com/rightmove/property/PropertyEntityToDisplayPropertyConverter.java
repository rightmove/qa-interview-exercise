package com.rightmove.property;

import com.rightmove.property.data.PropertyEntity;
import org.springframework.stereotype.Component;

@Component
public class PropertyEntityToDisplayPropertyConverter {

    public PropertyEntityToDisplayPropertyConverter(){ }

    public DisplayProperty convert(PropertyEntity propertyEntity){

        return new DisplayProperty.Builder()
                .id(propertyEntity.getId())
                .priceIndicator(calculatePriceIndicator(propertyEntity.getPrice()))
                .displayAddress(createDisplayAddress(propertyEntity.getNumber(), propertyEntity.getAddress(), propertyEntity.getRegion(), propertyEntity.getPostcode()))
                .propertyType(propertyEntity.getType())
                .build();

    }

    private PriceIndicator calculatePriceIndicator(Long price){
        if (price > 1000000){
          return PriceIndicator.HIGH;
        }
        if (price < 1000000 && price > 500000){
            return PriceIndicator.MEDIUM;
        }
        else return PriceIndicator.LOW;
    }

    private String createDisplayAddress(String number, String address, String region, String postcode){
       return number + " " + address + ", " + region + ", " + postcode;
    }

}
