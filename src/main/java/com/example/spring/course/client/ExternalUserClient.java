package com.example.spring.course.client;

import com.example.spring.course.exceptions.AppException;
import com.example.spring.course.model.ExternalUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Component
@RequiredArgsConstructor
public class ExternalUserClient {

    private final RestClient restClient;

    public ExternalUserResponse findUser(Long id){
        try {
            return restClient.get()
                    .uri("/users/{id}", id)
                    .retrieve()
                    .body(ExternalUserResponse.class);
        } catch(HttpClientErrorException.NotFound notFound) {
            throw new AppException("Utente esterno " + id + " non trovato ", HttpStatus.NOT_FOUND);
        } catch(RestClientException restClientException) {
            throw new AppException("Errore nella chiamata al servizio esterno", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
