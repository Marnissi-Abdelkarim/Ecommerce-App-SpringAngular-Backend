package ma.marnissiabdelkarim.ecommebackend.services;



import java.util.List;

import ma.marnissiabdelkarim.ecommebackend.shared.dto.ProductDto;


public interface ProductService {
	
	ProductDto createProduct(ProductDto productDto);

	ProductDto getProductByProductId(String productId) throws Exception;

	List<ProductDto> getProducts(int page, int limit, String search);

	void deleteProduct(String id);

	ProductDto updateProduct(String id, ProductDto productDto);

}
