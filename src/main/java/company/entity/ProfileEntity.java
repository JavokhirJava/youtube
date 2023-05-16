package company.entity;

import company.enums.GeneralStatus;
import company.enums.ProfileRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "profile")
@Entity
public class ProfileEntity {
    // id,name,surname,email,password,photo,role,status
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    @Column(name = "attach_id")
    private String attachId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attach_id",insertable=false, updatable=false)
    private AttachEntity photo;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private GeneralStatus status;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private ProfileRole role;
    @Column(name = "visible")
    private Boolean visible = Boolean.TRUE;
    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();
    // photo_id
    @Column(name = "prt_id")
    private Integer prtId;
}
