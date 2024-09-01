package org.cuatrovientos.jdbc.web.controller;

import java.util.List;

import org.cuatrovientos.jdbc.persistence.model.Category;
import org.cuatrovientos.jdbc.persistence.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {
	
	@Autowired
	private CategoryRepository repo;	
	
	
	@PostMapping
	public int addCategory(@ModelAttribute("Category") Category category) {
		return repo.save(category);		
	}
	
	
    @GetMapping
    public List<Category> getCategories() {
    	List<Category> list = repo.findAll();    	
    	return list;
    }
    
    @GetMapping("{id}")
    public List<Category> getCategoryById(@PathVariable Long id ) {
    	List<Category> list = repo.findById(id);    	
    	return list;
    }
    
	@PostMapping("/edit/{id}")
	public int updateCategory(@ModelAttribute("Category") Category category) {
		return repo.update(category);		
	}
    
    @GetMapping("/delete/{id}")
    public int deleteCategory(@PathVariable Long id ) {
    	return repo.delete(id);    	
    }
    
    
    

}
