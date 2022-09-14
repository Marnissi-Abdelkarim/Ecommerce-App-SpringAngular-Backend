package ma.marnissiabdelkarim.ecommebackend.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "appusers")
public class UserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5309393251036110443L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; // id user dans bd

	@Column(nullable = false,unique = true)
	private String userId;

	@Column(nullable = false, length = 50,unique = true)
	private String username;

	@Column(nullable = false, length = 120, unique = true)
	private String email;


	@Column(nullable = false)
	private String encryptedPassword;

	@Column(nullable = true)
	private String emailVerificationToken;

	@Column(nullable = false)
	private Boolean emailVerificationStatus = false;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( 
        name = "users_roles", 
        joinColumns = @JoinColumn(
          name = "user_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "id")) 
    private Collection<RoleEntity> roles=new ArrayList<>();
	
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
	private List<CustomerEntity> customers;
	
//	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy ="users")
//	private Collection<RoleEntity> roles =new ArrayList<>();
//	@ManyToMany(fetch = FetchType.EAGER )
//	Collection<RoleEntity> roles =new ArrayList<>();

	

	/*
	 * @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy =
	 * "users") private Set<GroupEntity> groups = new HashSet<>();
	 */

}
