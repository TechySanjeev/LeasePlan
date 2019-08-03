package com.assignment.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConfigurationProperties
public class AppConfig {

	
	@Value("${api.app-id-key}")
	private String app_id_key;

	@Value("${api.weather-api-url}")
	private String weather_api_url;

	public String getApp_id_key() {
		return app_id_key;
	}

	public void setApp_id_key(String app_id_key) {
		this.app_id_key = app_id_key;
	}

	public String getWeather_api_url() {
		return weather_api_url;
	}

	public void setWeather_api_url(String weather_api_url) {
		this.weather_api_url = weather_api_url;
	}
	 


	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
