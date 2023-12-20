package com.fsse2309.project_backend.repository;

import com.fsse2309.project_backend.data.product.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
    boolean existsByPid(int pid);

    Optional<ProductEntity>findByPid(int pid);
}
