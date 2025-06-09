package com.challengecloud.users.infrastructure.input.rest;

import com.challengecloud.users.application.dto.request.CreateUserRequestDto;
import com.challengecloud.users.application.dto.response.GetUserResponseDto;
import com.challengecloud.users.application.handler.IUserHandler;
import com.challengecloud.users.application.handler.impl.DataApplicationFactory;
import com.challengecloud.users.domain.constants.Constants;
import com.challengecloud.users.domain.exception.MissingParamRequiredException;
import com.challengecloud.users.domain.exception.UserAlreadyExistException;
import com.challengecloud.users.infrastructure.exceptionhandler.ControllerAdvisor;
import com.challengecloud.users.infrastructure.shared.SharedConstants;
import com.challengecloud.users.shared.DataConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserRestControllerTest {

    @InjectMocks
    private UserRestController userRestController;

    @Mock
    private IUserHandler userHandler;

    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(userRestController)
                .setControllerAdvice(new ControllerAdvisor())
                .build();
        objectMapper = new ObjectMapper();
    }
    @Nested
    @DisplayName("POST /user")
    class CreateUserTests{
        private final String pathTest = "/v1/users";
        @Test
        void createUserSuccessful() throws Exception {
            CreateUserRequestDto user = DataApplicationFactory.buildCreateUserRequestDto();
            String jsonBody = objectMapper.writeValueAsString(user);

            doNothing().when(userHandler).createUser(any(CreateUserRequestDto.class));
            MockHttpServletRequestBuilder request = post(pathTest)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonBody);
            mockMvc.perform(request)
                    .andDo(print())
                    .andExpect(status().isCreated());
        }

        @Test
        void createUserFail() throws Exception {
            CreateUserRequestDto user = DataApplicationFactory.buildCreateUserRequestDto();
            String jsonBody = objectMapper.writeValueAsString(user);

            doThrow(new UserAlreadyExistException()).when(userHandler).createUser(any(CreateUserRequestDto.class));
            MockHttpServletRequestBuilder request = post(pathTest)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonBody);
            mockMvc.perform(request)
                    .andDo(print())
                    .andExpect(status().isConflict());
        }
    }

    @Nested
    @DisplayName("GET byEmail /v1/users")
    class GetUserByEmailTests{

        private final String pathTest = "/v1/users";

        @Test
        void getUserByEmailShouldSuccessful() throws Exception {
            String emailInput = DataConstants.DEFAULT_USER_EMAIL;
            GetUserResponseDto user = DataApplicationFactory.buildGetUserResponseDto();
            String jsonResponse = objectMapper.writeValueAsString(user);

            when(userHandler.getUserByEmail(emailInput)).thenReturn(user);

            MockHttpServletRequestBuilder request = get(pathTest)
                    .param(SharedConstants.PARAM_NAME_EMAIL, emailInput)
                    .contentType(MediaType.APPLICATION_JSON);
            mockMvc.perform(request)
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().json(jsonResponse));
        }

        @Test
        void getUserByEmailShouldReturnConflict() throws Exception {
            String emailInput = DataConstants.DEFAULT_USER_EMAIL;

            when(userHandler.getUserByEmail(emailInput))
                    .thenThrow(new MissingParamRequiredException(Constants.PARAM_REQUIRED_NOT_FOUND));

            MockHttpServletRequestBuilder request = get(pathTest)
                    .param(SharedConstants.PARAM_NAME_EMAIL, emailInput)
                    .contentType(MediaType.APPLICATION_JSON);
            mockMvc.perform(request)
                    .andDo(print())
                    .andExpect(status().isConflict());
        }
    }
}
