package hibernate_task.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.engine.internal.Cascade;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;

    //profile
    @OneToOne (mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REFRESH,CascadeType.MERGE,CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private Profile profile;
    // post
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private List<Post> posts;
    // comment
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private List<Comment> comments;
}
