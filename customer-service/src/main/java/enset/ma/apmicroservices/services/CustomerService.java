package enset.ma.apmicroservices.services;

import enset.ma.apmicroservices.dto.CustomerRequestDTO;
import enset.ma.apmicroservices.dto.CustomerRespanseDTO;

import java.util.List;

public interface CustomerService {
    CustomerRespanseDTO save(CustomerRequestDTO customerRequestDTO);

    CustomerRespanseDTO GetCustomer(String id);

    CustomerRespanseDTO update(CustomerRequestDTO customerRequestDTO);

    CustomerRespanseDTO delete(CustomerRequestDTO customerRequestDTO);

    List<CustomerRespanseDTO> lisCostumer();

}
