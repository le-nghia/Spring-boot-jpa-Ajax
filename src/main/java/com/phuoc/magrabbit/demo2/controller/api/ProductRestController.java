package com.phuoc.magrabbit.demo2.controller.api;

import com.phuoc.magrabbit.demo2.model.Product;
import com.phuoc.magrabbit.demo2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<?> listProduct(){
        List<Product> list = productService.selectAll();
        if (!list.isEmpty()){
            return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
        }
        return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody Product product, BindingResult result){
        if (result.hasErrors()){
            List<FieldError> fieldErrors = result.getFieldErrors();
            Map<String, String> erros = new HashMap<>();
            for (FieldError fieldError : fieldErrors){
                erros.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(erros,HttpStatus.BAD_REQUEST);
        }
        productService.save(product);
        return new ResponseEntity<>(product,HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @Valid @RequestBody Product product, BindingResult result){
        if (result.hasErrors()){
            List<FieldError> fieldErrors = result.getFieldErrors();
            Map<String,String> errors= new HashMap<>();

            for (FieldError fieldError : fieldErrors){
                errors.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
        }
        Product product1 = productService.findById(id).get();
        if (product1 != null){
            productService.save(product);
            return new ResponseEntity<>(product,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id, @RequestBody Product product){
        Product product1 = productService.findById(id).get();
        if (product1 != null){
            productService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
