package io.github.josephpei.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="spittle_tbl")
public class Spittle implements Serializable {
    private static final long serialVersionUID = 1L;

    public Long getSpittleId() {
        return spittleId;
    }

    public void setSpittleId(Long spittleId) {
        this.spittleId = spittleId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="spittle_id")
    private Long spittleId;

    @Column(name="text")
    private String text;

    @Column(name="push_time")
    private Date pushTime;

    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User user;

    public Spittle() {

    }

}
