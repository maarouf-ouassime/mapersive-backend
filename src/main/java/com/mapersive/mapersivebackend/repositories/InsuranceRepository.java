package com.mapersive.mapersivebackend.repositories;

import com.mapersive.mapersivebackend.models.Insurance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
    Page<Insurance> findAllByOrderByIdDesc(Pageable pageable);
    Page<Insurance> findAllByOrderByDateOfPurchaseDesc(Pageable pageable);
    Page<Insurance> findAllByOrderByDateOfPurchaseAsc(Pageable pageable);
    Page<Insurance> findAllByCollisionIsTrue(Pageable pageable);
    Page<Insurance> findAllByCollisionIsFalse(Pageable pageable);
    Page<Insurance> findAllByCustomerGenderEquals(String gender ,Pageable pageable);
}
