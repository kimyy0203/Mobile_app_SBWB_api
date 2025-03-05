package org.example.pj_rest_api.Jpa;

import jakarta.persistence.*;

@Entity
@Table(name = "User_data")
public class JpaUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_id", nullable = false, length = 50)
    private String userId;

    @Column(name = "User_password", nullable = false, length = 50)
    private String userPassword;

    @Column(name = "User_name", nullable = false, length = 30)
    private String userName;

    @Column(name = "User_num", nullable = false, length = 30)
    private String userNum;

    @Column(name = "User_pos", length = 50)
    private String userPos;

    public String getUserPos() {
        return userPos;
    }

    public void setUserPos(String userPos) {
        this.userPos = userPos;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
