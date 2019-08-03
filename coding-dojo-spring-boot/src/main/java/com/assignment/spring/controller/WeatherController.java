package com.assignment.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.spring.model.WeatherEntity;
import com.assignment.spring.service.WeatherService;

@RestController
public class WeatherController {

	@Autowired
	private WeatherService weatherService;

	@RequestMapping("/weather")
	public WeatherEntity weather(HttpServletRequest request) {
		return weatherService.invokeWeatherAPI(request.getParameter("city"));
	}

}
