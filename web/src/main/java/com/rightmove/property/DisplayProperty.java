package com.rightmove.property;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.rightmove.property.data.PropertyType;

import java.util.Objects;

public class DisplayProperty {
    private final long id;
    private final PriceIndicator priceIndicator;
    private final String displayAddress;
    private final PropertyType type;

    public DisplayProperty(Builder builder) {
        this.id = builder.id;
        this.priceIndicator = builder.priceIndicator;
        this.displayAddress = builder.displayAddress;
        this.type = builder.type;
    }

    public long getId() {
        return id;
    }

    public PriceIndicator getPriceIndicator() {
        return priceIndicator;
    }

    public String getDisplayAddress() {
        return displayAddress;
    }

    public PropertyType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DisplayProperty that = (DisplayProperty) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        private long id;
        private PriceIndicator priceIndicator;
        private String displayAddress;
        private PropertyType type;

        public Builder id(long id){
            this.id = id;
            return this;
        }

        public Builder priceIndicator(PriceIndicator priceIndicator){
            this.priceIndicator = priceIndicator;
            return this;
        }

        public Builder displayAddress(String address){
            this.displayAddress = address;
            return this;
        }

        public Builder propertyType(PropertyType type){
            this.type = type;
            return this;
        }

        public DisplayProperty build(){
            return new DisplayProperty(this);
        }

    }

}
