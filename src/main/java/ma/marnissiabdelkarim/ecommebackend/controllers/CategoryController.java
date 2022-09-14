package ma.marnissiabdelkarim.ecommebackend.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ma.marnissiabdelkarim.ecommebackend.requests.CategoryRequest;

import ma.marnissiabdelkarim.ecommebackend.responses.CategoryResponseWithProducts;
import ma.marnissiabdelkarim.ecommebackend.responses.CategoryResponseWithoutProducts;

import ma.marnissiabdelkarim.ecommebackend.services.CategoryService;
import ma.marnissiabdelkarim.ecommebackend.shared.dto.CategoryDto;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	// create category
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<CategoryResponseWithoutProducts> createCategory(
			@RequestBody @Valid CategoryRequest categoryRequest) {
		ModelMapper modelMapper = new ModelMapper();
		CategoryDto categoryDto = modelMapper.map(categoryRequest, CategoryDto.class);

		CategoryDto createCategory = categoryService.createCategory(categoryDto);
		CategoryResponseWithoutProducts categoryResponse = modelMapper.map(createCategory,
				CategoryResponseWithoutProducts.class);
		return new ResponseEntity<CategoryResponseWithoutProducts>(categoryResponse, HttpStatus.CREATED);
	}

	// get category by category id
	@GetMapping(path = "/{categoryId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<CategoryResponseWithProducts> getCategoryByCategoryId(@PathVariable String categoryId) {
		CategoryDto categoryDto = categoryService.getCategoryByCategoryId(categoryId);
		ModelMapper modelMapper = new ModelMapper();
		CategoryResponseWithProducts categoryResponse = modelMapper.map(categoryDto,
				CategoryResponseWithProducts.class);
		return new ResponseEntity<CategoryResponseWithProducts>(categoryResponse, HttpStatus.OK);

	}

	// get category by category name
	@GetMapping(path = "/name/{categoryName}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<CategoryResponseWithProducts> getCategoryByCategoryName(@PathVariable String categoryName) {
		CategoryDto categoryDto = categoryService.getCategoryByCategoryName(categoryName);
		ModelMapper modelMapper = new ModelMapper();
		CategoryResponseWithProducts categoryResponse = modelMapper.map(categoryDto,
				CategoryResponseWithProducts.class);
		return new ResponseEntity<CategoryResponseWithProducts>(categoryResponse, HttpStatus.OK);

	}

	// get categories by pages
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<CategoryResponseWithProducts> getAllcategories(
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "15") int limit,
			@RequestParam(value = "search", defaultValue = "") String search, Principal principal) {

		List<CategoryResponseWithProducts> categoriesResponses = new ArrayList<>();

		List<CategoryDto> categories = categoryService.getCategories(page, limit, search);
		for (CategoryDto categoryDto : categories) {
			ModelMapper modelMapper = new ModelMapper();
			CategoryResponseWithProducts categoryResponse = modelMapper.map(categoryDto,
					CategoryResponseWithProducts.class);
			categoriesResponses.add(categoryResponse);
		}

		return categoriesResponses;
	}

	// UPDATE CATEGORY

	@PutMapping(path = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<CategoryResponseWithoutProducts> updateCategory(@PathVariable String id,
			@RequestBody @Valid CategoryRequest categoryRequest) {

		ModelMapper modelMapper = new ModelMapper();
		CategoryDto categoryDto = modelMapper.map(categoryRequest, CategoryDto.class);

		CategoryDto updatedCategory = categoryService.updateCategory(id, categoryDto);
		CategoryResponseWithoutProducts categoryResponse = modelMapper.map(updatedCategory,
				CategoryResponseWithoutProducts.class);
		return new ResponseEntity<CategoryResponseWithoutProducts>(categoryResponse, HttpStatus.ACCEPTED);
	}
	
	
	
	//DELETE CATEGORY
		@DeleteMapping(path = "/{categoryId}")
		public ResponseEntity<Object> deleteCategory(@PathVariable String categoryId,@RequestParam(value = "only",defaultValue = "false") Boolean only ) {
			categoryService.deleteCategory(categoryId,only);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

}
