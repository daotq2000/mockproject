package fa.appcode.web.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class PromotionDTO {
    private Integer id;
    private String detail;
    private BigDecimal discountLevel;
    private Date endTime;
    private String image;
    private Date startTime;
    private String title;
    private List<InvoiceDTO> invoices;
}
