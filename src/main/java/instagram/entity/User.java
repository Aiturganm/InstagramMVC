package instagram.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter    @Setter
@Builder
@SequenceGenerator(name = "id_gen", sequenceName = "user_seq", allocationSize = 1)
public class User extends IdGenerator{
    @Column(name = "user_name", unique = true)
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String userName;
    private String password;
    @Column(unique = true)
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should contains character @")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private UserInfo userInfo;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Image image;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Follower follower;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "user")
    private List<Post> posts = new ArrayList<>();
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user", fetch = FetchType.EAGER)
    private List<Comment> comments = new ArrayList<>();
    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private Like like = new Like();


    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone_number='" + phoneNumber + '\'' +
                '}';
    }
}
