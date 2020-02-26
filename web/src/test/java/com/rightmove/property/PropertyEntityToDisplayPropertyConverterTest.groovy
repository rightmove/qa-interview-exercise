package com.rightmove.property

import spock.lang.Specification
import spock.lang.Unroll

class PropertyEntityToDisplayPropertyConverterTest extends Specification {


    @Unroll
    def "when price is #price price indicator should be #priceIndicator"(){
        def propertyEntityToDisplayPropertyConverter = new PropertyEntityToDisplayPropertyConverter();

        expect:
        propertyEntityToDisplayPropertyConverter.calculatePriceIndicator(price) == priceIndicator

        where:
        price    || priceIndicator
        1200000  || PriceIndicator.HIGH
        800000   || PriceIndicator.MEDIUM
        100000   || PriceIndicator.LOW

    }

}
