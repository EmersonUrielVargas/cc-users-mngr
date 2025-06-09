package com.challengecloud.users.infrastructure.input.rest;

import com.challengecloud.users.application.dto.request.CreateUserRequestDto;
import com.challengecloud.users.application.dto.response.GetUserResponseDto;
import com.challengecloud.users.application.handler.IUserHandler;
import com.challengecloud.users.infrastructure.shared.SharedConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserHandler userHandler;

    @Operation(summary = SharedConstants.SUMMARY_SAVE_USER)
    @ApiResponses(value = {
            @ApiResponse(responseCode = SharedConstants.STATUS_CODE_CREATED, description = SharedConstants.RESPONSE_CREATED_SAVE_USER),
            @ApiResponse(responseCode = SharedConstants.STATUS_CODE_CONFLICT, description = SharedConstants.RESPONSE_CONFLICT_SAVE_USER, content = @Content)
    })
    @PostMapping("")
    public ResponseEntity<Void> saveUser(@Valid @RequestBody CreateUserRequestDto createUserRequestDto) {
        userHandler.createUser(createUserRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = SharedConstants.SUMMARY_GET_USER_BY_EMAIL)
    @ApiResponses(value = {
            @ApiResponse(responseCode = SharedConstants.STATUS_CODE_OK, description = SharedConstants.RESPONSE_OK_GET_USER,
                    content = @Content(mediaType = SharedConstants.MEDIA_TYPE_JSON,
                        schema = @Schema(implementation = GetUserResponseDto.class)
                    )
            ),
            @ApiResponse(responseCode = SharedConstants.STATUS_CODE_CONFLICT, description = SharedConstants.RESPONSE_CONFLICT_GET_USER, content = @Content),
            @ApiResponse(responseCode = SharedConstants.STATUS_CODE_NOT_FOUND, description = SharedConstants.RESPONSE_NOT_FOUND_GET_USER, content = @Content)
    })
    @GetMapping("")
    public ResponseEntity<GetUserResponseDto> getUserByEmail(@Valid @RequestParam(SharedConstants.PARAM_NAME_EMAIL) String email) {
        GetUserResponseDto response =  userHandler.getUserByEmail(email);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
