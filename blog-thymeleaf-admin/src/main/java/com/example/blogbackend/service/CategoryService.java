package com.example.blogbackend.service;

import com.example.blogbackend.dto.projection.CategoryPublic;
import com.example.blogbackend.dto.projection.CategoryWebPublic;
import com.example.blogbackend.entity.Blog;
import com.example.blogbackend.entity.Category;
import com.example.blogbackend.exception.BadRequestException;
import com.example.blogbackend.exception.NotFoundException;
import com.example.blogbackend.repository.BlogRepository;
import com.example.blogbackend.repository.CategoryRepository;
import com.example.blogbackend.request.UpsertCategoryRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BlogRepository blogRepository;

    //Danh sách Category phân trang (chia trang phía front end)
    public List<CategoryPublic> getCategoryList(){
        List<Category>  categoryList = categoryRepository.findAll();
        return categoryList.stream()
                .map(CategoryPublic::of)
                .toList();
    }

    private void isCategoryNameDuplicated(String name){
        if (categoryRepository.existsByName(name)) {
            throw new BadRequestException("Category name duplicated");
        }
    }
    private Category findCategoryById(Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                ()-> { throw new NotFoundException("Category id "+ id +" not found");}
        );
        return category;
    }
    //Thêm category (tên không trùng nhau)
    @Transactional
    public CategoryWebPublic addCategory(UpsertCategoryRequest request){
        //category name duplicated?
        isCategoryNameDuplicated(request.getName());

        Category newCategory = Category.builder()
                .name(request.getName())
                .build();
        categoryRepository.save(newCategory);
        return CategoryWebPublic.of(newCategory);
    }

    //Cập nhật category (tên không trùng nhau)
    @Transactional
    public CategoryWebPublic updateCategory(Integer categoryId, UpsertCategoryRequest request){
        //category id exist?
        Category category2update = findCategoryById(categoryId);

        //category name duplicated?
        isCategoryNameDuplicated(request.getName());

        //find blog list
        List<Blog> blogList = blogRepository.findByCategories_Id(categoryId);

        category2update.setName(request.getName());
        category2update.setBlogs(blogList);

        return CategoryWebPublic.of(category2update);
    }
    //Xóa category (xóa blog áp dụng category, ko xóa blog trong bảng blog)
    @Transactional
    public void deleteCategory(Integer categoryId){
        Category category2delete = findCategoryById(categoryId);
        categoryRepository.delete(category2delete);
    }
}
