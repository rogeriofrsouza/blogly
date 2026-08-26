package com.blogly.blogly.infrastructure.persistence.user;

import com.blogly.blogly.domain.user.Role;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false, length = 150)
    private String password;

    @Column(nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role;

    @Column(name = "bio", length = 200)
    private String bio;

    @Column(name = "avatar_key", length = 100)
    private String avatarKey;

    @Column(name = "joined_at", nullable = false)
    private Instant joinedAt;

    public Long getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getName() {
        return this.name;
    }

    public Role getRole() {
        return this.role;
    }

    public String getBio() {
        return this.bio;
    }

    public String getAvatarKey() {
        return this.avatarKey;
    }

    public Instant getJoinedAt() {
        return this.joinedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setAvatarKey(String avatarUrl) {
        this.avatarKey = avatarUrl;
    }

    public void setJoinedAt(Instant joinedAt) {
        this.joinedAt = joinedAt;
    }
}
