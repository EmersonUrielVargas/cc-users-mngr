package com.challengecloud.users.domain.usecase;

import com.challengecloud.users.domain.DataDomainFactory;
import com.challengecloud.users.domain.constants.Constants;
import com.challengecloud.users.domain.exception.InvalidFormatParamException;
import com.challengecloud.users.domain.exception.MissingParamRequiredException;
import com.challengecloud.users.domain.exception.UserAlreadyExistException;
import com.challengecloud.users.domain.exception.UserNotFoundException;
import com.challengecloud.users.domain.model.User;
import com.challengecloud.users.domain.spi.IUserPersistencePort;
import com.challengecloud.users.shared.DataConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;
    
    @InjectMocks
    private UserUseCase userUseCase;

    @Nested
    @DisplayName("create User")
    class CreateUserTests{
        @Test
        void shouldCreateUserSuccessFull(){
            User user = DataDomainFactory.createUser();
            userUseCase.createUser(user);

            verify(userPersistencePort).saveUser(user);
        }

        @Test
        void shouldCreateUserFail_ThrowsException_missingParam(){
            User user = DataDomainFactory.createUser();
            user.setEmail(null);
            assertThrows(MissingParamRequiredException.class, () -> userUseCase.createUser(user));
        }

        @Test
        void shouldCreateUserFail_ThrowsException_emailAlreadyExist(){
            User user = DataDomainFactory.createUser();
            when(userPersistencePort.getUserByEmail(DataConstants.DEFAULT_USER_EMAIL)).thenReturn(Optional.of(user));

            assertThrows(UserAlreadyExistException.class, () -> userUseCase.createUser(user));
        }
    }

    @Nested
    @DisplayName("GET byEmail /v1/users")
    class GetUserByEmailTests{

        @Test
        void shouldGetUser(){
            String email =  DataConstants.DEFAULT_USER_EMAIL;
            User user = DataDomainFactory.createUser();
            when(userPersistencePort.getUserByEmail(email)).thenReturn(Optional.of(user));

            User userFound = userUseCase.getUserByEmail(email);

            assertEquals(userFound.getEmail(), user.getEmail());
            verify(userPersistencePort).getUserByEmail(email);
        }

        @Test
        void shouldThrowsException_invalidFormat(){
            String emailInvalid = "wwww@.com";
            assertThrows(InvalidFormatParamException.class, () -> userUseCase.getUserByEmail(emailInvalid));
        }

        @Test
        void shouldThrowExceptionUserNotFound(){
            String email =  DataConstants.DEFAULT_USER_EMAIL;
            when(userPersistencePort.getUserByEmail(email)).thenReturn(Optional.empty());

            UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> userUseCase.getUserByEmail(email));

            assertEquals(Constants.USER_NO_FOUND, exception.getMessage());
            verify(userPersistencePort).getUserByEmail(email);
        }
    }

}
