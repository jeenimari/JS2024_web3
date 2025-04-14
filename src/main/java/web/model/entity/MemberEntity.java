package web.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "member")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mmo;
    private String meamil;
    private String mpwd;
    private String mname;

}
