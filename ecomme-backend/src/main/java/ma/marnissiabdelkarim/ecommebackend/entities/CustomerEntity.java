package ma.marnissiabdelkarim.ecommebackend.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "customers")
public class CustomerEntity implements Serializable {

	private static final long serialVersionUID = 797136784889958264L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String customerId;
	
	@Column(nullable = false)
	private String firstname;
	
	@Column(nullable = false)
	private String lastname;
	
	@Column(nullable = false)
	private String phone;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = true)
	private String additionalInformation;
	
	@Column(nullable = false)
	private String region;
	
	@Column(nullable = false)
	private String town;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

}
