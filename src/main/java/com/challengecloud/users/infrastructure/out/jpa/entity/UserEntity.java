package com.challengecloud.users.infrastructure.out.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(nullable = false)
    private Long id;

    private String name;

    private String surname;

    @Column(name = "id_number")
    private String idNumber;

    private String email;
}
