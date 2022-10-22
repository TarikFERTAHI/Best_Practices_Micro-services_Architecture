package ma.tarik.billdingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.tarik.billdingservice.entities.Customer;

import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceRequestDTO {
    private BigDecimal amount;
    private String customerId;

}
