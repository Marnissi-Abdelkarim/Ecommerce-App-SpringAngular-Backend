package ma.marnissiabdelkarim.ecommebackend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ma.marnissiabdelkarim.ecommebackend.entities.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
	
	ProductEntity findByProductId(String productId);
	
	@Query(value =
			  "SELECT * FROM products p WHERE p.name=?1", nativeQuery
			  = true)
	Page<ProductEntity> findAllProductsByCriteria(Pageable pageable, String
			  search);
	
	
	Page<ProductEntity> findByNameIgnoreCaseContaining(Pageable pageable,String name);
	
}
