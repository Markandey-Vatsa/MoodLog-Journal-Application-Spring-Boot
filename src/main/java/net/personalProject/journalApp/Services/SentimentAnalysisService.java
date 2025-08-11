//package net.personalProject.journalApp.Services;
//import net.personalProject.journalApp.apiResponse.QuoteResponse;
//import net.personalProject.journalApp.apiResponse.SentimentResponses;
//import net.personalProject.journalApp.enums.Sentiment;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.*;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Map;
//
//// WILL DO IT LATER
//
//@Service
//public class SentimentAnalysisService {
//
//    @Autowired
//    RestTemplate restTemplate;
//
//    @Value("${gemini.api.key}")
//    private String api_key;
//
//    private static final String API_URL = "https://twinword-sentiment-analysis.p.rapidapi.com/analyze/";
//
//    public Double analyzeSentiment(String text) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("X-goog-api-key", api_Key);
//
//
//
//
//        String body = "text=" + text;
//        HttpEntity<String> entity = new HttpEntity<>(body, headers);
//
//        ResponseEntity<SentimentResponses> response = restTemplate.exchange(
//                API_URL,
//                HttpMethod.POST,
//                entity,
//                SentimentResponses.class
//        );
//
//        SentimentResponses result = response.getBody();
//
//        if (result == null || result.getKeywords() == null || result.getKeywords().isEmpty()) {
//            return 0.0;
//        }
//
//        // Average the scores of the keywords
//        double total = 0.0;
//        int count = 0;
//
//        for (SentimentResponses.Keyword keyword : result.getKeywords()) {
//            total += keyword.getScore(); // assuming getter method works
//            count++;
//        }
//
//        return count > 0 ? total / count : 0.0;
//
//    }
//
//
//}
