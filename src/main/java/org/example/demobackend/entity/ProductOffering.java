package org.example.demobackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.demobackend.common.StatusEnum;

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
    @GeneratedValue(                                strategy =  GenerationType.IDENTITY)
    private Long id; // nên để kiểu wrapper

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Long price;

    @Column(name = "color")
    private String color;

    @Column(name = "status") // không cần khai báo column cũng đc
    //@Enumerated(EnumType.STRING) -> nó sẽ lưu xuống DB là ACTIVE hoặc INACTIVE
    //@Enumerated(EnumType.ORDINAL) -> nó sẽ lưu xuống DB là 0 hoặc 1
    // lưu theo kiểu chuỗi thì đổi kiểu dữ liệu về String và ở db đổi status sang vacher
    @Enumerated(EnumType.ORDINAL)
    private StatusEnum statusEnum;
}
