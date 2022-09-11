package ma.marnissiabdelkarim.ecommebackend.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ma.marnissiabdelkarim.ecommebackend.entities.CategoryEntity;
import ma.marnissiabdelkarim.ecommebackend.entities.ProductEntity;
import ma.marnissiabdelkarim.ecommebackend.repositories.CategoryRepository;
import ma.marnissiabdelkarim.ecommebackend.repositories.ProductRepository;
import ma.marnissiabdelkarim.ecommebackend.services.CategoryService;
import ma.marnissiabdelkarim.ecommebackend.shared.Utils;
import ma.marnissiabdelkarim.ecommebackend.shared.dto.CategoryDto;
import ma.marnissiabdelkarim.ecommebackend.shared.dto.ProductDto;


@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	Utils util;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ProductRepository productRepository;
	
	
	public boolean isBoolean(String value) {
	    return value != null && Arrays.stream(new String[]{"true", "false", "1", "0"})
	            .anyMatch(b -> b.equalsIgnoreCase(value));
	}

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		CategoryEntity category = categoryRepository.findByName(categoryDto.getName());
		
		if(category!=null) {
			throw new RuntimeException("category name already exist!");
		}
		ModelMapper modelMapper = new ModelMapper();
		CategoryEntity categoryEntity=modelMapper.map(categoryDto, CategoryEntity.class);
		categoryEntity.setCategoryId(util.generateStringId(30));
		
		CategoryEntity newCategory=categoryRepository.save(categoryEntity);
		
		CategoryDto categoryDto2=modelMapper.map(newCategory,CategoryDto.class);
		return categoryDto2;
	}

	@Override
	public CategoryDto getCategoryByCategoryId(String categoryId) {
		CategoryEntity category=categoryRepository.findByCategoryId(categoryId);
		if(category==null) {
			throw new RuntimeException("Category with the specified id does not exist !");
		}
		ModelMapper modelMapper = new ModelMapper();
		CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
		
		
		
		return categoryDto;
	}
	
	@Override
	public CategoryDto getCategoryByCategoryName(String categoryName) {
		CategoryEntity category=categoryRepository.findByName(categoryName);
		if(category==null) {
			throw new RuntimeException("Category with the specified name does not exist !");
		}
		ModelMapper modelMapper = new ModelMapper();
		CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
		
		
		
		return categoryDto;
	}

	@Override
	public List<CategoryDto> getCategories(int page, int limit, String search) {
		if (page > 0)
			page -= 1;

		List<CategoryDto> categoriesDto = new ArrayList<CategoryDto>();
		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<CategoryEntity> categoryPage;

		if (search.isEmpty()) {
			categoryPage = categoryRepository.findAll(pageableRequest);
		} else {
			categoryPage = categoryRepository.findByNameIgnoreCaseContaining(pageableRequest, search);
		}

		List<CategoryEntity> categoryEntities = categoryPage.getContent();
		for (CategoryEntity categoryEntity : categoryEntities) {

			ModelMapper modelMapper = new ModelMapper();
			CategoryDto categoryDto = modelMapper.map(categoryEntity, CategoryDto.class);

			categoriesDto.add(categoryDto);
		}
		return categoriesDto;
	}

	@Override
	public void deleteCategory(String categoryId,Boolean only) {
		CategoryEntity categoryEntity = categoryRepository.findByCategoryId(categoryId);
		if(categoryEntity==null) {
			throw new RuntimeException("Category with the specified id does not exist !");
		}
		if(categoryEntity.getName().equals("Undefined")) {
			throw new RuntimeException("cannot delete undefined category");
		}

		if(only) {
			for (ProductEntity product : categoryEntity.getProducts()) {
				product.setCategory(categoryRepository.findByName("Undefined"));
				productRepository.save(product);
			}
		}
		else {
			for (ProductEntity product : categoryEntity.getProducts()) {
				
				productRepository.delete(product);
			}
		}
		categoryRepository.delete(categoryEntity);

	}

	@Override
	public CategoryDto updateCategory(String categoryId, CategoryDto categoryDto) {
		
		CategoryEntity categoryEntity = categoryRepository.findByCategoryId(categoryId);
		if(categoryEntity==null) {
			throw new RuntimeException("Category with the specified id does not exist !");
		}
		
		if(categoryEntity.getName().equals("Undefined")) {
			throw new RuntimeException("cannot update undefined category");
		}
		
		CategoryEntity categoryEntity2 = categoryRepository.findByName(categoryDto.getName());
		
		if(categoryEntity2!=null && categoryEntity2.getCategoryId().compareTo(categoryId)!=0) {
			
			throw new RuntimeException("Category with the specified name already exists !");
		}
		
		categoryEntity.setName(categoryDto.getName());
		categoryEntity.setDescription(categoryDto.getDescription());
		
		
		CategoryEntity updatedCategory = categoryRepository.save(categoryEntity);
		ModelMapper modelMapper = new ModelMapper();
		CategoryDto newCategoryDto = modelMapper.map(updatedCategory, CategoryDto.class);
		
		
		return newCategoryDto;
	}

}
