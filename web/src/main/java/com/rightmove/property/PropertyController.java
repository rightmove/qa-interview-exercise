package com.rightmove.property;

import com.rightmove.property.data.Property;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertyController {

	private PropertyService propertyService;

	public PropertyController(PropertyService propertyService) {
		this.propertyService = propertyService;
	}

	@GetMapping("/property")
	public PropertyResult getProperties(@RequestParam String postcode) {
		return new PropertyResult(propertyService.search(postcode, 0));
	}

	@GetMapping("/property/{id}")
	public DisplayPropertyDto getProperties(@PathVariable() long propertyId) {
		Property byId = propertyService.getById(propertyId);
		return new DisplayPropertyDto();
	}
}
