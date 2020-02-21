package com.rightmove.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertyController {

	private final SearchPropertyService searchPropertyService;

	@Autowired
	public PropertyController(SearchPropertyService searchPropertyService) {
		this.searchPropertyService = searchPropertyService;
	}

	@GetMapping("/property")
	public @ResponseBody PropertyResult getPropertiesByPostcode(@RequestParam String postcode)  {
		return searchPropertyService.retrievePropertiesByPostcode(postcode);
	}

}