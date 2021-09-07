package fa.appcode.web.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "PROMOTION")
@Check(constraints = "START_TIME <= END_TIME")
@EqualsAndHashCode

public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "DETAIL")
    private String detail;

    @Column(name = "DISCOUNT_LEVEL")
    @Range(min = 0, max = 100, message = "Invalid percentage value")
    @NotNull(message = "Discount cannot be empty")
    private BigDecimal discountLevel;

    @Temporal(TemporalType.DATE)
    @Column(name = "END_TIME")
    @NotNull(message = "Date cannot be empty")
    private Date endTime;

    @Column(name = "IMAGE", columnDefinition = "NVARCHAR(MAX)")
    private String image;

    @Column(name = "START_TIME")
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Date cannot be empty")
    private Date startTime;

    @Column(name = "TITLE")
    @NotBlank(message = "Title cannot be blank")
    private String title;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "promotion")
    private List<Invoice> invoices;



    public Promotion(Integer id, String detail, @Range(min = 0, max = 100, message = "Invalid percentage value") @NotNull(message = "Discount cannot be empty") BigDecimal discountLevel, @NotNull(message = "Date cannot be empty") Date endTime, String image, @NotNull(message = "Date cannot be empty") Date startTime, @NotBlank(message = "Title cannot be blank") String title, List<Invoice> invoices) {
        this.id = id;
        this.detail = detail;
        this.discountLevel = discountLevel;
        this.endTime = endTime;
        this.image = image;
        this.startTime = startTime;
        this.title = title;
        this.invoices = invoices;
    }

    public Promotion(String title, String detail, Date startTime, Date endTime, BigDecimal discountLevel) {
        this.title = title;
        this.detail = detail;
        this.startTime = startTime;
        this.endTime = endTime;
        this.discountLevel = discountLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Promotion)) return false;
        Promotion promotion = (Promotion) o;
        return Objects.equals(getId(), promotion.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
