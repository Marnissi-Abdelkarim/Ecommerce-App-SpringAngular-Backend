package ma.marnissiabdelkarim.ecommebackend.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @AllArgsConstructor @NoArgsConstructor
@Entity(name = "categories")
public class CategoryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false,unique = true)
	private String categoryId;
	
	@Column(nullable = false,unique = true)
	private String name;
	
	@Column(nullable = true)
	private String description;
	
	@OneToMany(mappedBy = "category")
	private List<ProductEntity> products;

}
