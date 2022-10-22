package enset.ma.apmicroservices.mappers;

import enset.ma.apmicroservices.dto.CustomerRequestDTO;
import enset.ma.apmicroservices.dto.CustomerRespanseDTO;
import enset.ma.apmicroservices.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper  {
    //@Mapping(source = "id", target = "id")
    CustomerRespanseDTO customerToCustomerResponseDTO(Customer customer);

    Customer customerRequestDTOTocustomer(CustomerRequestDTO customerRequestDTO);

}
