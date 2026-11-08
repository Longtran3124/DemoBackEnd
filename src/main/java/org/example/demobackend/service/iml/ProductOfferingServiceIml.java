package org.example.demobackend.service.iml;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.example.demobackend.common.StatusEnum;
import org.example.demobackend.dto.request.ProductOfferingCreateReq;
import org.example.demobackend.entity.ProductOffering;
import org.example.demobackend.repository.ProductOfferingRepo;
import org.example.demobackend.service.ProductOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductOfferingServiceIml implements ProductOfferingService {

    // hàm này cho phép mình build các câu query động
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired // thay vì phải new thì dùng Autowired
    private ProductOfferingRepo productOfferingRepo;

    @Override
    public ProductOffering getById(Long id) {
        Optional<ProductOffering> product = productOfferingRepo.findById(id);
        if (product.isEmpty()) {
            throw new RuntimeException("Khong tim thay san pham");
        } else {
            return product.get();
        }
    }

    @Override
    public List<ProductOffering> getAll() {
        return productOfferingRepo.findAll();
    }

    @Override
    public List<ProductOffering> findByNameContaining(String name) {
        return productOfferingRepo.findByNameContaining(name);
    }

    @Override
    public List<ProductOffering> findByNameContainingAndPrice(String name, Long price) {
        return productOfferingRepo.findByNameContainingAndPrice(name, price);
    }

    @Override
    public ProductOffering createProductOffering(ProductOfferingCreateReq request) {
        if (request.getName() == null || request.getColor() == null || request.getPrice() == null) {
            throw new RuntimeException("Chua dien day du thong tin");
        }

        ProductOffering productOffering = new ProductOffering();
        productOffering.setName(request.getName());
        productOffering.setColor(request.getColor());
        productOffering.setPrice(request.getPrice());
        productOffering.setStatusEnum(StatusEnum.ACTIVE);

        return productOfferingRepo.save(productOffering);
    }


    @Override
    public ProductOffering updateProductOffering(Long id, ProductOffering productOffering) {
        getById(id);
        productOffering.setId(id);
        return productOfferingRepo.save(productOffering);
    }

    @Override
    public List<ProductOffering> findByBrandAndStatus(String brand, StatusEnum status) {
        return productOfferingRepo.findByBrandAndStatus(brand, status);
    }

    @Override
    @Transactional // các thao tác tác động lên dữ liệu thì nên thêm Transactional
    // Transaction khi cả 2 chạy cùng thành công hoặc cùng thất bại thì nó mới commit còn 1 trong 2 k chạy đc thì nó sẽ rollback
    public List<ProductOffering> filter(String name, Long minPrice, Long maxPrice, String color, String status) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductOffering> query = criteriaBuilder.createQuery(ProductOffering.class);

        Root<ProductOffering> root = query.from(ProductOffering.class);

        List<Predicate> predicates = new ArrayList<>();

        // dk name khác null và name khác để trống
        if (name != null && !name.isEmpty()) {
            Predicate predicate = criteriaBuilder.like(root.get("name"), "%" + name + "%");
            predicates.add(predicate);
        }

        if (minPrice != null) {
            Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
            predicates.add(predicate);
        }

        if (minPrice != null) {
            Predicate predicate = criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
            predicates.add(predicate);
        }

        if (color != null && !color.isEmpty()) {
            Predicate predicate = criteriaBuilder.like(root.get("color"), "%" + color + "%");
            predicates.add(predicate);
        }

        if (status != null && !status.isEmpty()) {
            // field trong entity tên là "statusEnum" (không phải "status" - đó là tên cột DB)
            // và kiểu dữ liệu là enum nên phải convert String -> StatusEnum
            Predicate predicate = criteriaBuilder.equal(root.get("statusEnum"), StatusEnum.valueOf(status));
            predicates.add(predicate);
        }

        // cú pháp của java biến đổi từ list sang Array
        query.where(predicates.toArray(new Predicate[0]));


        List<ProductOffering> productOfferings = entityManager.createQuery(query).getResultList();
        return productOfferings;
    }


}
