package ma.marnissiabdelkarim.ecommebackend.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.marnissiabdelkarim.ecommebackend.entities.CustomerEntity;


@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
	CustomerEntity findByCustomerId(String customerId);
	Page<CustomerEntity> findByUserEmail(String email,Pageable pageable);

}
