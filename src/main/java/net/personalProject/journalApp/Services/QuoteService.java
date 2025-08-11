package net.personalProject.journalApp.Services;


import net.personalProject.journalApp.Cache.AppCache;
import net.personalProject.journalApp.Repository.ConfigAppCacheRepository;
import net.personalProject.journalApp.apiResponse.QuoteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuoteService {

    @Autowired
    AppCache APP_CACHE;


    @Autowired
    RestTemplate restTemplate;



    public QuoteResponse quoteOfTheMoment() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", APP_CACHE.appCache.get("quote_service_key"));

        HttpEntity<String> entity = new HttpEntity<>(headers);


        ResponseEntity<QuoteResponse[]> response = restTemplate.exchange(
                APP_CACHE.appCache.get("quote_service_api"),
                HttpMethod.GET,
                entity,
                QuoteResponse[].class
        );

        return response.getBody()[0];
    }

}
