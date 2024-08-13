package com.example.blog.model.entity;

import com.example.blog.model.enums.MemberRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Getter
@Setter
@NoArgsConstructor
public class Member extends BaseEntity {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String username;
    String password;
    String intro;

    @Enumerated(value = EnumType.STRING)
    MemberRole role;

    @OneToMany(mappedBy = "member")
    List<Article> articles = new ArrayList<>();

    public Member(Long id, String username, String password, String intro) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.intro = intro;
    }

    public Member(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
