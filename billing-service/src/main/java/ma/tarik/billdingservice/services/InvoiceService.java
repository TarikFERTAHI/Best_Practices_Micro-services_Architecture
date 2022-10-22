package ma.tarik.billdingservice.services;

import ma.tarik.billdingservice.dto.InvoiceRequestDTO;
import ma.tarik.billdingservice.dto.InvoiceResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface InvoiceService {
    InvoiceResponseDTO save(InvoiceRequestDTO invoiceRequestDTO);

    InvoiceResponseDTO getInvoce(String invoceId);

    List<InvoiceResponseDTO> invoicesByCustomerId(String idCustomer);

    List<InvoiceResponseDTO> allInvoices();

}
