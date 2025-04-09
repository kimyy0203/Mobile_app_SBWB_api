package org.example.pj_rest_api.Jpa;

import jakarta.persistence.*;

@Entity
@Table(name = "com_pin")
public class JpaPinEntity {
    @EmbeddedId
    private JpaPinEntityId id;

    @Column(name = "comment", nullable = false, length = 100)
    private String comment;

    @Column(name = "cat", nullable = false, length = 10)
    private String cat;

    @Column(name = "ctprvnnm", nullable = false, length = 10)
    private String ctprvnnm;

    @Column(name = "signgunm", nullable = false, length = 10)
    private String signgunm;

    public String getSigngunm() {
        return signgunm;
    }

    public void setSigngunm(String signgunm) {
        this.signgunm = signgunm;
    }

    public String getCtprvnnm() {
        return ctprvnnm;
    }

    public void setCtprvnnm(String ctprvnnm) {
        this.ctprvnnm = ctprvnnm;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public JpaPinEntityId getId() {
        return id;
    }

    public void setId(JpaPinEntityId id) {
        this.id = id;
    }
}
