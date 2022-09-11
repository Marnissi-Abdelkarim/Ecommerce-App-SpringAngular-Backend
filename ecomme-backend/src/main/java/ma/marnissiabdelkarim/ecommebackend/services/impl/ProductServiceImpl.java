package ma.marnissiabdelkarim.ecommebackend.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ma.marnissiabdelkarim.ecommebackend.entities.CategoryEntity;
import ma.marnissiabdelkarim.ecommebackend.entities.ProductEntity;
import ma.marnissiabdelkarim.ecommebackend.entities.UserEntity;
import ma.marnissiabdelkarim.ecommebackend.repositories.CategoryRepository;
import ma.marnissiabdelkarim.ecommebackend.repositories.ProductRepository;
import ma.marnissiabdelkarim.ecommebackend.services.ProductService;
import ma.marnissiabdelkarim.ecommebackend.shared.Utils;
import ma.marnissiabdelkarim.ecommebackend.shared.dto.ProductDto;
import ma.marnissiabdelkarim.ecommebackend.shared.dto.UserDto;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	Utils util;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ProductRepository productRepository;

	// create product
	@Override
	public ProductDto createProduct(ProductDto productDto) {

		CategoryEntity category = categoryRepository.findByName(productDto.getCategory().getName());

		if (category == null) {
			category = categoryRepository.save(new CategoryEntity(null, util.generateStringId(30),
					productDto.getCategory().getName(), productDto.getCategory().getDescription(), null));
		}

		ModelMapper modelMapper = new ModelMapper();

		ProductEntity productEntity = modelMapper.map(productDto, ProductEntity.class);

		productEntity.setProductId(util.generateStringId(30));
		productEntity.setCategory(category);

		ProductEntity newProduct = productRepository.save(productEntity);

		ProductDto productDto2 = modelMapper.map(newProduct, ProductDto.class);

		return productDto2;
	}

	// get product by productId
	@Override
	public ProductDto getProductByProductId(String productId) {
		ProductEntity productEntity = productRepository.findByProductId(productId);
		if (productEntity == null) {
			throw new RuntimeException("product with the specified id does not exist !");
		}
		ModelMapper modelMapper = new ModelMapper();

		ProductDto productDto = modelMapper.map(productEntity, ProductDto.class);

		return productDto;
	}

	@Override
	public List<ProductDto> getProducts(int page, int limit, String search) {
		if (page > 0)
			page -= 1;

		List<ProductDto> productsDto = new ArrayList<ProductDto>();
		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<ProductEntity> productPage;

		if (search.isEmpty()) {
			productPage = productRepository.findAll(pageableRequest);
		} else {
			//productPage = productRepository.findAllProductsByCriteria(pageableRequest, search);
			productPage = productRepository.findByNameIgnoreCaseContaining(pageableRequest, search);
		}

		List<ProductEntity> productEntities = productPage.getContent();
		for (ProductEntity productEntity : productEntities) {

			ModelMapper modelMapper = new ModelMapper();
			ProductDto productDto = modelMapper.map(productEntity, ProductDto.class);

			productsDto.add(productDto);
		}
		return productsDto;
	}

	@Override
	public void deleteProduct(String id) {
		ProductEntity productEntity = productRepository.findByProductId(id);
		if (productEntity == null) {
			throw new RuntimeException("entity with the specified id does not exist !");
		}
		productRepository.delete(productEntity);
		
	}

	@Override
	public ProductDto updateProduct(String productId, ProductDto productDto) {
		ProductEntity productEntity = productRepository.findByProductId(productId);
		if (productEntity == null) {
			throw new RuntimeException("entity with the specified id does not exist !");
		}
		
		CategoryEntity category = categoryRepository.findByName(productDto.getCategory().getName());

		if (category == null) {
			category = categoryRepository.save(new CategoryEntity(null, util.generateStringId(30),
					productDto.getCategory().getName(), productDto.getCategory().getDescription(), null));
		}
		
		productEntity.setName(productDto.getName());
		productEntity.setDescription(productDto.getDescription());
		productEntity.setCurrentPrice(productDto.getCurrentPrice());
		productEntity.setDiscountedPrice(productDto.getDiscountedPrice());
		productEntity.setPromotion(productDto.isPromotion());
		productEntity.setCategory(category);

		
		ProductEntity updatedProduct= productRepository.save(productEntity);
		ModelMapper modelMapper = new ModelMapper();
		ProductDto productDto2 =modelMapper.map(updatedProduct, ProductDto.class);
		
		return productDto2;
	}
}
