package ma.marnissiabdelkarim.ecommebackend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.marnissiabdelkarim.ecommebackend.entities.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
	
	CategoryEntity findByName(String name);
	CategoryEntity findByCategoryId(String categotyId);
	Page<CategoryEntity> findByNameIgnoreCaseContaining(Pageable pageable,String name);

}
