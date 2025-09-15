package com.app.quora.model;


import com.app.quora.enums.UserRole;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "UserDocument")
public class User {

    @Id
    private String id;

    private String bio;

    private String email;
    private String username;
    private String password; // For now null later add with Auth

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    private UserRole role; //This is just for security in future and distinguish between normal user and admin user.

    private List<String> followersIds = new ArrayList<>();
    private List<String> followingIds = new ArrayList<>();
}
