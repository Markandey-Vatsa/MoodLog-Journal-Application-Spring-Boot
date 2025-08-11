package net.personalProject.journalApp.Services;


import net.personalProject.journalApp.Cache.AppCache;
import net.personalProject.journalApp.apiResponse.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Autowired
    AppCache APP_CACHE;

    @Autowired
    RestTemplate restTemplate;
    public WeatherResponse getWeather(String city){

       String url = APP_CACHE.appCache.get("weather_api").replace("<city>",city).replace("<api_key>",APP_CACHE.appCache.get("weather_api_key"));
       ResponseEntity<WeatherResponse> response = restTemplate.exchange(url, HttpMethod.GET, null, WeatherResponse.class);
       WeatherResponse body = response.getBody();
       return body;
    }
}
