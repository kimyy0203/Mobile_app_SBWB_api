package org.example.pj_rest_api.Jpa;

import jakarta.persistence.*;

@Entity
@Table(name = "com_pin")
public class JpaPinEntity {
    @EmbeddedId
    private JpaPinEntityId id;

    @Column(name = "comment", nullable = false, length = 100)
    private String comment;

    @Column(name = "loc", nullable = false, length = 10)
    private String loc;

    @Column(name = "cat", nullable = false, length = 10)
    private String cat;

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
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
