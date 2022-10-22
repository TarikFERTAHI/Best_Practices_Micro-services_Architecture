package enset.ma.apmicroservices.repositories;

import enset.ma.apmicroservices.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, String> {


}
