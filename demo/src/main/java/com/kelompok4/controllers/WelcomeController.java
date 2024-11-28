package com.kelompok4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kelompok4.models.ProductDto;
import com.kelompok4.models.entities.Product;
import com.kelompok4.models.repos.ProductRepo;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class WelcomeController {

    @Autowired
    private ProductRepo repo;

    @GetMapping("/")
    public String index(){
        return "index";
    }
    
    @GetMapping("/dynamic")
    public String dynamic(Model model){
        Iterable<Product> products = repo.findAll(); // Tambah Sort.by(Sort.Direction.DESC, "id" jika ingin membalik urutan produk
        model.addAttribute("products", products);
        return "dynamic";
    }
    
    @GetMapping("/dynamic/create")
    public String createPage(Model model){
        ProductDto dto = new ProductDto();
        model.addAttribute("productDto", dto);
        return "createProduct";
    }

    @PostMapping("dynamic/create")
    public String createProduct(
        @Valid @ModelAttribute ProductDto productDto,
        BindingResult result
    ){
        if (result.hasErrors()){
            return "createProduct";
        }

        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());

        repo.save(product);

        return "redirect:/dynamic";
    }

    @GetMapping("/dynamic/edit")
    public String editPage(
        Model model,
        @RequestParam int id
    ){
        try {
            Product product = repo.findById((long) id).get();
            model.addAttribute("product", product);

            ProductDto productDto = new ProductDto();
            productDto.setName(product.getName());
            productDto.setDescription(product.getDescription());
            productDto.setPrice(product.getPrice());
            
            model.addAttribute("productDto", productDto);
        }catch(Exception ex) {
            System.out.println("Exception: " +  ex.getMessage());
            return "redirect:/dynamic";
        }
        
        return "editProduct";
    }

    @PostMapping("/dynamic/edit")
    public String editProduct(
        Model model,
        @RequestParam int id,
        @Valid @ModelAttribute ProductDto productDto,
        BindingResult result) {
            try {
                Product product = repo.findById((long) id).get();   
                model.addAttribute("product", product);

                if (result.hasErrors()){
                    return "editProduct";
                }

                
                product.setName(productDto.getName());
                product.setDescription(productDto.getDescription());
                product.setPrice(productDto.getPrice());

                repo.save(product);
            } catch (Exception e) {
                System.out.println("Exception"+ e.getMessage());
            }

        return "redirect:/dynamic";
    }

    @GetMapping("dynamic/delete")
    public String deletePage(@RequestParam int id) {
        try {
            Product product = repo.findById((long) id).get();

            repo.delete(product);
            
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());}
        
        return "redirect:/dynamic";
    }
    
    
}
