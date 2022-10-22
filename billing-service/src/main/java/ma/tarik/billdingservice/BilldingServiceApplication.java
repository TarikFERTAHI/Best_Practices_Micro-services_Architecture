package ma.tarik.billdingservice;

import ma.tarik.billdingservice.dto.InvoiceRequestDTO;
import ma.tarik.billdingservice.services.InvoiceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
@EnableFeignClients
public class BilldingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BilldingServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(InvoiceService invoiceService) {
        return args -> {
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(7000), "C01"));
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(8000), "C02"));

        };

    }
}
