package com.phuoc.magrabbit.demo2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String nameProduct;

    @ManyToOne
    private ProductType productType;

    private Long price;

    @Column(name = "status")
    private Boolean status = false;

    @Transient
    private CommonsMultipartFile image;

    private String UrlImage;

    @ManyToOne
    private Table table;

    @ManyToOne
    private ImportProduct importProduct;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;

    private Boolean deleted = false;
}
