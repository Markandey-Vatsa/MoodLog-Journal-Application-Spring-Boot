package net.personalProject.journalApp.apiResponse;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class QuoteResponse {
        private String quote;
        private String author;
        private String category;
}
