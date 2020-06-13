package ru.job4j.forum.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "username", nullable = false)
    private User user;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role authority;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAuthority() {
        return authority.toString();
    }

    public void setAuthority(Role authority) {
        this.authority = authority;
    }

}
