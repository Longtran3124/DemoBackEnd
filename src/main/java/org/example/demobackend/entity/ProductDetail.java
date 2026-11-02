package org.example.demobackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "product_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "weight")
    private String weight;

    @Column(name = "feature")
    private String feature;

    @Column(name = "power")
    private String power;

    @Column(name = "brand")
    private String brand;

    @Column(name = "image")
    private String image;

    @Column(name = "video")
    private String video;

    @OneToMany(mappedBy = "productDetail")
    @JsonIgnore
    private List<ProductOfferingDetail> productOfferingDetail;

////    @OneToOne(mappedBy = "productDetail")
//    @OneToMany(mappedBy = "productDetail")
//    private List<ProductOffering> productOffering;
}
