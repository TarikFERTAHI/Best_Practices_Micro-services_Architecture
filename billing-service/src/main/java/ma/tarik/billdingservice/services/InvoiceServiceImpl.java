package ma.tarik.billdingservice.services;

import lombok.AllArgsConstructor;
import ma.tarik.billdingservice.dto.InvoiceRequestDTO;
import ma.tarik.billdingservice.dto.InvoiceResponseDTO;
import ma.tarik.billdingservice.entities.Customer;
import ma.tarik.billdingservice.entities.Invoice;
import ma.tarik.billdingservice.exceptions.CustomerNotFoundException;
import ma.tarik.billdingservice.mappers.InvoiceMapper;
import ma.tarik.billdingservice.openfeign.CustomerRestClient;
import ma.tarik.billdingservice.repositories.InvoiceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.webjars.NotFoundException;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private InvoiceRepository invoiceRepository;

    private InvoiceMapper invoiceMapper;

    private CustomerRestClient customerRestClient;


    @Override
    public InvoiceResponseDTO save(InvoiceRequestDTO invoiceRequestDTO) {
        //verification de l'integrite referentielle  Invoice/Custome

        try {
            Customer customer = customerRestClient.getCustomer(invoiceRequestDTO.getCustomerId());
        } catch (Exception e) {

            throw new CustomerNotFoundException("customer not found");
        }


        Invoice invoice = invoiceMapper.fromInvoceRequestDTO(invoiceRequestDTO);
        invoice.setId(UUID.randomUUID().toString());
        invoice.setDate(new Date());
        Invoice saveInvoce = invoiceRepository.save(invoice);
        return invoiceMapper.fromInvoce(saveInvoce);
    }

    @Override
    public InvoiceResponseDTO getInvoce(String invoceId) {
        Invoice invoice = invoiceRepository.findById(invoceId).get();
        Customer customer = customerRestClient.getCustomer(invoice.getCustomerId());
        invoice.setCustomer(customer);
        return invoiceMapper.fromInvoce(invoice);
    }

    @Override
    public List<InvoiceResponseDTO> invoicesByCustomerId(String idCustomer) {
        List<Invoice> invoices = invoiceRepository.findByCustomerId(idCustomer);
        return invoices
                .stream()
                .map(invoice -> invoiceMapper.fromInvoce(invoice))
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceResponseDTO> allInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();

        for (Invoice invoice : invoices) {
            Customer customer = customerRestClient.getCustomer(invoice.getCustomerId());
            invoice.setCustomer(customer);
        }
        return invoices.stream().map(invoice -> invoiceMapper.fromInvoce(invoice)).collect(Collectors.toList());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String > exceptionHandler(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
