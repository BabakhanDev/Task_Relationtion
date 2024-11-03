package hibernate_task.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private String description;
    private LocalDate created = LocalDate.now();
    //  user
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private User user;
    // comment
    @OneToMany(mappedBy = "post", cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private List<Comment> comments;
}
