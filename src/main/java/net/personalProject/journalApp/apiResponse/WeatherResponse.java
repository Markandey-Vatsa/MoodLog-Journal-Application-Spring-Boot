package net.personalProject.journalApp.apiResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


@Getter
@Setter
public class WeatherResponse {
    private Current current;



    @Getter
    @Setter
    public class Current {

        @JsonProperty("observation_time")
        private String observationTime;
        private int temperature;

        @JsonProperty("weather_descriptions")
        private ArrayList<String> weatherDescriptions;

        @JsonProperty("feelslike")
        private int feelsLike;

    }

}
