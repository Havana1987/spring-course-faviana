package com.example.spring.course.service.mapper;

import com.example.spring.course.model.UserRequest;
import com.example.spring.course.model.UserResponse;
import com.example.spring.course.repository.model.UserEntity;
import com.example.spring.course.service.dto.UserRequestDto;
import com.example.spring.course.service.dto.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class MapperUserDto {
    @Mapping(
            target = "indirizzo",
            expression = "java(mapIndirizzo(userRequest.getCitta(), userRequest.getNazione()))")
    public abstract UserRequestDto toRequestDto(UserRequest userRequest);

    @Mapping(source = "message", target = "messaggio")
    @Mapping(source = "id", target = "id")
    public abstract UserResponse toResponse(UserResponseDto userResponseDto);

    public String mapIndirizzo(String citta, String nazione) {
        return citta + " - " + nazione;
    }
}
