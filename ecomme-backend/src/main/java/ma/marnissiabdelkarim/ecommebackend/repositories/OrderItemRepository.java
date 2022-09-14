package ma.marnissiabdelkarim.ecommebackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.marnissiabdelkarim.ecommebackend.entities.OrderItemEntity;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long>{

}
