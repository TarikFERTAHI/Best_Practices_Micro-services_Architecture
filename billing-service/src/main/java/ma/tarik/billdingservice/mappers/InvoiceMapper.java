package ma.tarik.billdingservice.mappers;


import ma.tarik.billdingservice.dto.InvoiceRequestDTO;
import ma.tarik.billdingservice.dto.InvoiceResponseDTO;
import ma.tarik.billdingservice.entities.Invoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    Invoice fromInvoceRequestDTO(InvoiceRequestDTO invoiceRequestDTO);

    InvoiceResponseDTO fromInvoce(Invoice invoice);



    
}
