package com.example.spring.course.controller;

import com.tasgroup.spring_course.generated.api.ContattiApi;
import com.tasgroup.spring_course.generated.model.Contatto;
import com.tasgroup.spring_course.generated.model.CreateContattoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;

@RestController
public class ContattiController implements ContattiApi {
    @Override
    public Optional<NativeWebRequest> getRequest() {
        return ContattiApi.super.getRequest();
    }

    @Override
    public ResponseEntity<Contatto> creaContatto(CreateContattoRequest createContattoRequest) {
        return ContattiApi.super.creaContatto(createContattoRequest);
    }

    @Override
    public ResponseEntity<Contatto> trovaPerId(Long id) {
        return ContattiApi.super.trovaPerId(id);
    }

    @Override
    public ResponseEntity<List<Contatto>> trovaTutti() {
        return ContattiApi.super.trovaTutti();
    }
}
