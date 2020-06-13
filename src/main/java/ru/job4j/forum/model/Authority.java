package ru.job4j.forum.model;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name =  "authorities", uniqueConstraints = {@UniqueConstraint(columnNames = {"username", "authority"})})
public class Authority implements GrantedAuthority {
    private static final long serialVersionUID = 4696234336030966649L;

    public Authority() {
    }

    public Authority(User user, Role authority) {
        this.user = user;
        this.authority = authority;
    }

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "username", nullable = false)
    private User user;

    @Id
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role authority;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAuthority() {
        return authority.toString();
    }

    public Role getRole() {
        return authority;
    }

    public void setAuthority(Role authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Authority authority1 = (Authority) o;
        return Objects.equals(user, authority1.user) && authority == authority1.authority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, authority);
    }
}
