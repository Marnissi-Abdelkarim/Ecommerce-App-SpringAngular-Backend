package ma.marnissiabdelkarim.ecommebackend.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity(name = "orders")
public class OrderEntity implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 5844716496094548915L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false,unique = true)
	private String orderId;
	
	@Column(nullable = true)
	private Date date=new Date();
	
	@Column(nullable = false)
	private double totalAmount;
	
	@Column(nullable = false)
	private boolean isPayementConfirmed=false;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private CustomerEntity customer;
	
	@OneToMany(mappedBy = "order",cascade = CascadeType.REMOVE)
	private List<OrderItemEntity> orderItems;
	

}
