package org.example.demobackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.demobackend.common.StatusEnum;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductOfferingCreateReq implements Serializable {
    private String name;
    private Long price;
    private String color;
}
