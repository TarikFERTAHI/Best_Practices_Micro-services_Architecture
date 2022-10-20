package enset.ma.apmicroservices.services;

import enset.ma.apmicroservices.dto.CustomerRequestDTO;
import enset.ma.apmicroservices.dto.CustomerRespanseDTO;
import enset.ma.apmicroservices.entities.Customer;
import enset.ma.apmicroservices.mappers.CustomerMapper;
import enset.ma.apmicroservices.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerRespanseDTO save(CustomerRequestDTO customerRequestDTO) {
        //Customer customer = new Customer();
        //customer.setId(customerRequestDTO.getId());
        //customer.setName(customerRequestDTO.getName());
        //customer.setEmail(customerRequestDTO.getEmail());

        Customer customer = customerMapper.customerRequestDTOTocustomer(customerRequestDTO);
        //customer.setId(UUID.randomUUID().toString());
        Customer saveCustomer = customerRepository.save(customer);

       // CustomerRespanseDTO customerRespanseDTO = new CustomerRespanseDTO();
        // customerRespanseDTO.setId(saveCustomer.getId());
        // customerRespanseDTO.setName(saveCustomer.getName());
        // customerRespanseDTO.setEmail(saveCustomer.getEmail());
        CustomerRespanseDTO customerRespanseDTO = customerMapper.customerToCustomerResponseDTO(saveCustomer);
        return customerRespanseDTO;
    }

    @Override
    public CustomerRespanseDTO GetCustomer(String id) {
        Customer customer = customerRepository.findById(id).get();
        return customerMapper.customerToCustomerResponseDTO(customer);
    }


    @Override
    public CustomerRespanseDTO update(CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerMapper.customerRequestDTOTocustomer(customerRequestDTO);
        Customer updatedCustomer = customerRepository.save(customer);
        return customerMapper.customerToCustomerResponseDTO(updatedCustomer);
    }

    @Override
    public CustomerRespanseDTO delete(CustomerRequestDTO customerRequestDTO) {
        return null;
    }

    @Override
    public List<CustomerRespanseDTO> lisCostumer() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerRespanseDTO> customerRespanseDTOS =
                customers.stream()
                .map(cust -> customerMapper.customerToCustomerResponseDTO(cust))
                        .collect(Collectors.toList());
        return customerRespanseDTOS;
    }
}
