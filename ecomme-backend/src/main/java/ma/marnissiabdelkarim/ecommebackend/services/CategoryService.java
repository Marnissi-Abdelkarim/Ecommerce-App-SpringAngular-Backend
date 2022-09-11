package ma.marnissiabdelkarim.ecommebackend.services;

import java.util.List;

import ma.marnissiabdelkarim.ecommebackend.shared.dto.CategoryDto;

public interface CategoryService {
	
	CategoryDto createCategory(CategoryDto categoryDto);

	CategoryDto getCategoryByCategoryId(String categoryId);

	List<CategoryDto> getCategories(int page, int limit, String search);

	void deleteCategory(String id, Boolean only);

	CategoryDto updateCategory(String id, CategoryDto categoryDto);

	CategoryDto getCategoryByCategoryName(String categoryName);

}
