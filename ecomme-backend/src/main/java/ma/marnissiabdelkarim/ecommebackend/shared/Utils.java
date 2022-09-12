package ma.marnissiabdelkarim.ecommebackend.shared;


import java.security.SecureRandom;
import java.util.Random;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Utils {
	
	private final Random RANDOM = new SecureRandom();
	private final String ALPHABET = "012356789ACDEFGHIJKLMNOPQRSTUVWXYZabcdefjhigklmnopqrstuvwxyz";
	
	public String generateStringId(int length) {
		
		StringBuilder returnValue = new StringBuilder(length);
		for(int i =0 ; i<length ; i++) {
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		return new String(returnValue);
		
	}
	
	

}
