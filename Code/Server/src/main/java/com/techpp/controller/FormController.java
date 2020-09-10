package com.techpp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techpp.modal.Teacher;
import com.techpp.service.RidesCacheManagerService;

@RestController

public class FormController {
	
	@Autowired
	RidesCacheManagerService ridesCacheManagerService;
	
	@GetMapping(value = "/showform")
	public String showForm() {
		return "sampleform";
	}
	
	@PostMapping(value = "/saveform")
	public String saveData(@ModelAttribute Teacher teacher, Model model) {
		
		teacher.setName(teacher.getName() + " Rao");
		model.addAttribute("tchr", teacher);
		
		return "successpage";
	}
	
	@GetMapping(value = "/index")
	public String intiCache() {
		
		ridesCacheManagerService.initCache();
		
		return "Cache Loaded";
	}
}
