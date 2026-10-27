package org.example.demobackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "product_offerings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductOffering implements Serializable {

    @Id // đánh dấu nó là khóa chính (primary key)
    @Column(name = "id") // đặt column để nó tham chiếu đến db
    private Long id; // nên để kiểu wrapper

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Long price;

    @Column(name = "color")
    private String color;
}
