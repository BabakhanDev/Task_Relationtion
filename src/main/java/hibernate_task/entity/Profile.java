package hibernate_task.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Entity
@Table (name = "profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String full_name;
    private LocalDate date_of_birth;
    private Gender gender;
    private String bio;

    @OneToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;
}
