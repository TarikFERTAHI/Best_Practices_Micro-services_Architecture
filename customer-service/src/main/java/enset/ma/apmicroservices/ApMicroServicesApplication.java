package enset.ma.apmicroservices;

import enset.ma.apmicroservices.dto.CustomerRequestDTO;
import enset.ma.apmicroservices.services.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApMicroServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApMicroServicesApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerService customerService){
        return args -> {
            customerService.save(new CustomerRequestDTO("C01", "Tarik", "Tarik@Tarik.com"));
            customerService.save(new CustomerRequestDTO("C02", "OpenLab", "OpenLab@OpenLab.com"));
        };
    }




}
