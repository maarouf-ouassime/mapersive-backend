package com.mapersive.mapersivebackend.services.insurance;

import com.mapersive.mapersivebackend.models.Insurance;
import com.mapersive.mapersivebackend.services.generic.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InsuranceService extends GenericService<Insurance> {
    Page<Insurance> findAllPaginated(Pageable pageable);
    Page<Insurance> findAllByOrderByDateOfPurchaseDesc(Pageable pageable);
    Page<Insurance> findAllByOrderByDateOfPurchaseAsc(Pageable pageable);
    Page<Insurance> findAllByCollisionIsTrue(Pageable pageable);
    Page<Insurance> findAllByCollisionIsFalse(Pageable pageable);
    Page<Insurance> findAllByCustomerGenderEquals(String gender ,Pageable pageable);
}
