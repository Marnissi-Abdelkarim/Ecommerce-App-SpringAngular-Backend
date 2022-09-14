package ma.marnissiabdelkarim.ecommebackend.shared.dto;

import java.io.Serializable;

import lombok.Data;
@Data
public class CustomerDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5444794827762276846L;
	
	private Long id;
	private String customerId;
	private String firstname;
	private String lastname;
	private String phone;
	private String address;
	private String additionalInformation;
	private String region;
	private String town;
	private UserDto user;

}
