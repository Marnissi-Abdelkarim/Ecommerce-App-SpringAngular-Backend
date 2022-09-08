package ma.marnissiabdelkarim.ecommebackend.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "roles")
public class RoleEntity implements Serializable {

	
	private static final long serialVersionUID = -6222890415447301192L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // id user dans bd
	
	private String roleName;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy ="roles")
	private Collection<UserEntity> users =new ArrayList<>();
	
//	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinTable(name="users_roles" , joinColumns = {@JoinColumn(name="roles_id")} , inverseJoinColumns = {@JoinColumn(name="users_id")}) 
//	private Collection<UserEntity> users =new ArrayList<>();

	

}
