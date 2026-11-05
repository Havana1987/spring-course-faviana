package com.example.spring.course;

import com.example.spring.course.model.UserResponse;
import com.example.spring.course.repository.UserRepository;
import com.example.spring.course.repository.model.UserEntity;
import com.example.spring.course.service.DemoService;
import com.example.spring.course.service.dto.UserRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserApplicationTest {

    @InjectMocks
    private DemoService demoService;

    @Mock
    private UserRepository userRepository;


    @Test
    public void calcolaTotale() {
        double prezzo = 10.0;
        int quantità = 3;
        double totale = prezzo * quantità;
        assertEquals(30.0, totale);
    }

    @Test
    public void createUserTest() {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setEmail("ste.fav@gmail.com");
        userRequestDto.setNome("Nome");
        userRequestDto.setId(1L);

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setNome("Stefano");

        when(userRepository.saveAndFlush(any(UserEntity.class))).thenReturn(userEntity);

        UserResponse response = demoService.createUser(userRequestDto);


        assertNotNull(response);
        assertEquals(1L, response.getId());

    }

    @Test
    public void createUserTestKO() {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setEmail("ste.fav@gmail.com");
        userRequestDto.setNome("Nome");
        userRequestDto.setId(1L);

        when(userRepository.saveAndFlush(any(UserEntity.class)))
                .thenThrow(new RuntimeException("Errore di persistenza DB"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            demoService.createUser(userRequestDto);
        });
        assertEquals("Errore di persistenza DB", exception.getMessage());
    }
}