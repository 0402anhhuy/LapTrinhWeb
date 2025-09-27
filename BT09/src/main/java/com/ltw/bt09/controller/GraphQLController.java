package com.ltw.bt09.controller;

import com.ltw.bt09.entity.Category;
import com.ltw.bt09.entity.Product;
import com.ltw.bt09.entity.User;
import com.ltw.bt09.service.CategoryService;
import com.ltw.bt09.service.ProductService;
import com.ltw.bt09.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class GraphQLController {

    private final ProductService productService;
    private final UserService userService;
    private final CategoryService categoryService;

    // Product Queries
    @QueryMapping
    public List<Product> getAllProducts() {
        return productService.findAllBy();
    }

    @QueryMapping
    public List<Product> getProductsByPriceAsc() {
        return productService.findAllByOrderByPriceAsc();
    }

    @QueryMapping
    public List<Product> getProductsByCategory(@Argument Long categoryId) {
        return productService.findAllByCategories_Id(categoryId);
    }

    @QueryMapping
    public Product getProductById(@Argument Long id) {
        Product product = productService.findById(id);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }
        return product;
    }

    // User Queries
    @QueryMapping
    public List<User> getAllUsers() {
        return userService.findAllBy();
    }

    @QueryMapping
    public User getUserById(@Argument Long id) {
        User user = userService.findById(id);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return user;
    }

    // Category Queries
    @QueryMapping
    public List<Category> getAllCategories() {
        return categoryService.findAllBy();
    }

    @QueryMapping
    public Category getCategoryById(@Argument Long id) {
        Category category = categoryService.findById(id);
        if (category == null) {
            throw new RuntimeException("Category not found");
        }
        return category;
    }

    // Product Mutations
    @MutationMapping
    public Product createProduct(@Argument ProductInput input) {
        User user = userService.findById(input.userId());
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        List<Category> categories = input.categoryIds().stream()
                .map(categoryId -> {
                    Category c = categoryService.findById(categoryId);
                    if (c == null) {
                        throw new RuntimeException("Category not found: " + categoryId);
                    }
                    return c;
                })
                .collect(Collectors.toList());

        Product product = new Product();
        product.setTitle(input.title());
        product.setQuantity(input.quantity());
        product.setDescription(input.description());
        product.setPrice(input.price());
        product.setUser(user);
        product.setCategories(categories);

        productService.save(product);
        return product;
    }

    @MutationMapping
    public Product updateProduct(@Argument Long id, @Argument ProductInput input) {
        Product existingProduct = productService.findById(id);
        if (existingProduct == null) {
            throw new RuntimeException("Product not found");
        }

        User user = userService.findById(input.userId());
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        List<Category> categories = input.categoryIds().stream()
                .map(categoryId -> {
                    Category c = categoryService.findById(categoryId);
                    if (c == null) {
                        throw new RuntimeException("Category not found: " + categoryId);
                    }
                    return c;
                })
                .collect(Collectors.toList());

        existingProduct.setTitle(input.title());
        existingProduct.setQuantity(input.quantity());
        existingProduct.setDescription(input.description());
        existingProduct.setPrice(input.price());
        existingProduct.setUser(user);
        existingProduct.setCategories(categories);

        productService.save(existingProduct);
        return existingProduct;
    }

    @MutationMapping
    public Boolean deleteProduct(@Argument Long id) {
        productService.deleteById(id);
        return true;
    }

    // User Mutations
    @MutationMapping
    public User createUser(@Argument UserInput input) {
        User user = new User();
        user.setFullname(input.fullname());
        user.setEmail(input.email());
        user.setPassword(input.password());
        user.setPhone(input.phone());
        userService.save(user);
        return user;
    }

    @MutationMapping
    public User updateUser(@Argument Long id, @Argument UserInput input) {
        User existingUser = userService.findById(id);
        if (existingUser == null) {
            throw new RuntimeException("User not found");
        }

        existingUser.setFullname(input.fullname());
        existingUser.setEmail(input.email());
        existingUser.setPassword(input.password());
        existingUser.setPhone(input.phone());

        userService.save(existingUser);
        return existingUser;
    }

    @MutationMapping
    public Boolean deleteUser(@Argument Long id) {
        userService.deleteById(id);
        return true;
    }

    // Category Mutations
    @MutationMapping
    public Category createCategory(@Argument CategoryInput input) {
        Category category = new Category();
        category.setName(input.name());
        category.setImages(input.images());
        categoryService.save(category);
        return category;
    }

    @MutationMapping
    public Category updateCategory(@Argument Long id, @Argument CategoryInput input) {
        Category existingCategory = categoryService.findById(id);
        if (existingCategory == null) {
            throw new RuntimeException("Category not found");
        }

        existingCategory.setName(input.name());
        existingCategory.setImages(input.images());

        categoryService.save(existingCategory);
        return existingCategory;
    }

    @MutationMapping
    public Boolean deleteCategory(@Argument Long id) {
        categoryService.deleteById(id);
        return true;
    }

    // Input Records
    public record ProductInput(String title, Integer quantity, String description,
                               Double price, Long userId, List<Long> categoryIds) {}

    public record UserInput(String fullname, String email, String password, String phone) {}

    public record CategoryInput(String name, String images) {}
}