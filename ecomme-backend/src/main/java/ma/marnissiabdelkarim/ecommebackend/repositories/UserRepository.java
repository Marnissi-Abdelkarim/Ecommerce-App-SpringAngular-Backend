package ma.marnissiabdelkarim.ecommebackend.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.marnissiabdelkarim.ecommebackend.entities.UserEntity;



@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
	UserEntity findByEmail(String email);

	UserEntity findByUserId(String userId);

	public Page<UserEntity> findAll(Pageable page);

	@Query(value = "select * from users u where u.username='hhh'", nativeQuery = true)
	Page<UserEntity> findAllUserByUserName(Pageable pageable);
	
	//jpql
	/*
	 * @Query(value = "select user from UserEntity user where user.firstName='hhh'")
	 * Page<UserEntity> findAllUserByFirstName2(Pageable pageable);
	 */
	
	@Query(value =
			  "SELECT * FROM users u WHERE u.username=?1", nativeQuery
			  = true) Page<UserEntity> findAllUserByCriteria(Pageable pageable, String
			  search);

	/*
	 * @Query(value =
	 * "SELECT * FROM users u WHERE u.first_name=?1 OR u.last_name=?1", nativeQuery
	 * = true) Page<UserEntity> findAllUserByCriteria(Pageable pageable, String
	 * search);
	 * 
	 * @Query(value =
	 * "SELECT * FROM users u WHERE u.first_name LIKE %:search% OR u.last_name= :search"
	 * , nativeQuery = true) Page<UserEntity> findAllUserByCriteria2(Pageable
	 * pageable,@Param("search") String search);
	 */
}
