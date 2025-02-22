package org.example.pj_rest_api.Jpa;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class JpaUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_pid", nullable = false)
    private Integer id;

    @Column(name = "user_id", nullable = false, length = 30)
    private String userId;

    @Column(name = "user_pw", nullable = false, length = 256)
    private String userPw;

    @Column(name = "user_email", nullable = false, length = 256)
    private String userEmail;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
