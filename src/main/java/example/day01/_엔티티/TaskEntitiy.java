package example.day01._엔티티;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name ="task1todo")
public class TaskEntitiy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //제약조건을 지원 함 auto-increment
    private int id;

    @Column(columnDefinition = "varchar(100)",nullable = false)
    private String title;
    @Column(updatable = false)
    private boolean state = false; // 초기값
    @Column(nullable = false)
    private LocalDate createdat;

    @Column(nullable = false)
    private LocalDateTime upateat;
}
