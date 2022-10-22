package enset.ma.apmicroservices.web;


import enset.ma.apmicroservices.dto.CustomerRequestDTO;
import enset.ma.apmicroservices.dto.CustomerRespanseDTO;
import enset.ma.apmicroservices.services.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api")
public class CustomerRestAPI {
    private CustomerService customerService;


    public CustomerRestAPI(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping(path = "/customers")
    public List<CustomerRespanseDTO> allCustomers() {
        return customerService.lisCostumer();
    }
    @PostMapping(path = "/customers")
    public CustomerRespanseDTO save(@RequestBody CustomerRequestDTO customerRequestDTO){
        customerRequestDTO.setId(UUID.randomUUID().toString());
        return customerService.save(customerRequestDTO);
    }
    @GetMapping(path = "/customers/{id}")
    public CustomerRespanseDTO getCustomer(@PathVariable String id) {
        return customerService.GetCustomer(id);
    }




}
