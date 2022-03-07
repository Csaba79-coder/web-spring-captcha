package csaba79coder.webspringcaptcha.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;

    @Column(unique = true, nullable = false, columnDefinition = "varchar(255)")
    private String nickName;

    @Column(unique = true, nullable = false, columnDefinition = "varchar(255)")
    private String email;

    @CreationTimestamp
    Date registrationDate;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
    // or if they have more roles, store it in a list!!!

    private boolean enable;
}
