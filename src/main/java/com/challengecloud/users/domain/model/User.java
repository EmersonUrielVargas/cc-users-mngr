package com.challengecloud.users.domain.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User {
    private Long id;
    private String name;
    private String lastName;
    private String idNumber;
    private String email;

}
