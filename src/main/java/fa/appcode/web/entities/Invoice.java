package fa.appcode.web.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "INVOICE")
@Getter
@Setter
@ToString
@EqualsAndHashCode

public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;
    @Column(name = "ADD_SCORE")
    private Integer addScore;
    @Column(name = "DATE_BOOKING")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bookingDate;
    @Column(name = "MOVIE_NAME")
    private String movieName;
    @Column(name = "SCHEDULE_SHOW")
    private LocalDate scheduleShow;
    @Column(name = "SCHEDULE_SHOW_TIME")
    private String scheduleShowTime;
    @Column(name = "STATUS")
    private byte status;
    @Column(name = "TOTAL_MONEY")
    private BigDecimal totalMoney;
    @Column(name = "USER_SCORE")
    private Integer userScore;
    @Column(name = "SEAT")
    private String seat;
    @Column(name = "CUSTOMER_NAME")
    private String customerName;
    @Column(name = "CUSTOMER_PHONE")
    private String customerPhone;
    @Column(name = "CUSTOMER_IDENTITY_CARD")
    private String customerIdentityCard;
    @Column(name = "MEMBER_ID")
    private String memberId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROMOTION_ID")
    private Promotion promotion;
    @OneToMany(mappedBy = "invoice",cascade = CascadeType.ALL)
    private List<Ticket> tickets;
}
