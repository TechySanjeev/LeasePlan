/**
 * 
 */
package com.assignment.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.assignment.spring.api.WeatherResponse;
import com.assignment.spring.config.AppConfig;
import com.assignment.spring.exception.ExternalServiceGatewayException;
import com.assignment.spring.exception.ExternalServiceInvocationException;
import com.assignment.spring.model.WeatherEntity;
import com.assignment.spring.repository.WeatherRepository;
import com.assignment.spring.utils.Constants;

/**
 * @author sanjeev
 *
 */

@Service
public class WeatherService {

	@Autowired
	private WeatherRepository weatherRepository;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private AppConfig config;

	/**
	 * Invoke Weather API
	 * 
	 * @param city
	 * @return
	 */
	public WeatherEntity invokeWeatherAPI(String city) {

		String url = (config.getWeather_api_url().replace("{city}", city)).replace("{appid}", config.getApp_id_key());
		try {
			ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(url, WeatherResponse.class);

			// Add exception handling to the weather response so that DB updates and
			// exceptions can be avoided
			if (response.getStatusCode() == HttpStatus.OK) {
				//return saveWeather(response.getBody());
				return new WeatherEntity();
			}

		} catch (HttpStatusCodeException httpStatusEx) {
			throw new ExternalServiceInvocationException(Constants.FORECAST_IO_SERVICE_NAME,
					httpStatusEx.getRawStatusCode());
		} catch (Exception ex) {
			// This is thrown when can't even get to API (e.g. network error)!
			throw new ExternalServiceGatewayException(Constants.FORECAST_IO_SERVICE_NAME, ex);
		}

		return null;
	}

	/**
	 * Save weather API results
	 * 
	 * @param response
	 * @return
	 */
	public WeatherEntity saveWeather(WeatherResponse response) {
		WeatherEntity entity = new WeatherEntity();
		entity.setCity(response.getName());
		entity.setCountry(response.getSys().getCountry());
		entity.setTemperature(response.getMain().getTemp());

		return weatherRepository.save(entity);
	}

}
