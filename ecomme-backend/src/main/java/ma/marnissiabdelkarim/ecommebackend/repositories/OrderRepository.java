package ma.marnissiabdelkarim.ecommebackend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.marnissiabdelkarim.ecommebackend.entities.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
	
	OrderEntity findByOrderId(String orderId);
	Page<OrderEntity> findByCustomerUserEmail(String email,Pageable pageable);

}
