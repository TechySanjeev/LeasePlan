package com.assignment.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import com.assignment.spring.api.WeatherResponse;
import com.assignment.spring.config.AppConfig;
import com.assignment.spring.model.WeatherEntity;
import com.assignment.spring.service.WeatherService;
import com.assignment.spring.utils.Constants;

import junit.framework.Assert;

//@RunWith(SpringRunner.class)
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ApplicationTests {
	
	@Autowired
    private AppConfig appConfig;

	@InjectMocks
    private WeatherService weatherService = new WeatherService();
    
	@Mock
    private RestTemplate restTemplate;
	

        
	@Test
	public void contextLoads() {
	}
	

		 
	    @SuppressWarnings({ "unchecked", "deprecation" })
		//@Test
	    public void givenMockingIsDoneByMockito_whenInvokeIsCalled_shouldReturnMockedObject() {
	    	WeatherEntity entity = new WeatherEntity();
	    	String city = "Amsterdam"; 
	    	entity.setCity(city);
			
	        Mockito
	          .when(restTemplate.getForEntity(appConfig.getWeather_api_url().replace("{city}", city).replace("{appid}", appConfig.getApp_id_key()), WeatherResponse.class))
	          .thenReturn(new ResponseEntity(entity, HttpStatus.OK));
	 
	        WeatherEntity weatherEntity = weatherService.invokeWeatherAPI(city);
	        Assert.assertEquals(entity.getCity(), weatherEntity.getCity());
	    }
	    
	    
	    @Test
	    public void givenWeatherForecastResponse_SaveResultsToDatabase() {
	    	
	    }
	    
	    @Test
	    public void throwAnExceptionWithIncorrectCityParam() {
	    	
	    }
    
	}

