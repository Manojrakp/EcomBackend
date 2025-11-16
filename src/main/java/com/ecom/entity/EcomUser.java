package com.ecom.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Entity
@Table(name = "ECOMUSERS")
public class EcomUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "USER_SEQ", allocationSize = 1)
//    @GenericGenerator(
//            name = "user_seq",
//            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
//            parameters = {
//                    @Parameter(name = "sequence_name", value = "USER_SEQ"),
//                    @Parameter(name = "increment_size", value = "1"),
//                    @Parameter(name = "optimizer", value = "none")
//            }
    private Long id;

    @Column(nullable = false, unique = true, length = 255)
    private String username;

    @Column(nullable = true, unique = true , length = 150)
    private String email;

    @Column(nullable = false, length =255 )
    private String password;

    @Column(length = 150)
    private String role;

}

