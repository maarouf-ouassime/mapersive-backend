package com.mapersive.mapersivebackend.services.insurance;

import com.mapersive.mapersivebackend.models.Insurance;
import com.mapersive.mapersivebackend.repositories.InsuranceRepository;
import com.mapersive.mapersivebackend.services.generic.GenericServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("insuranceService")
@Transactional
public class InsuranceServiceImpl extends GenericServiceImpl<Insurance> implements InsuranceService{

    private InsuranceRepository insuranceRepository;

    public InsuranceServiceImpl(InsuranceRepository insuranceRepository) {
        super(insuranceRepository);
        this.insuranceRepository = insuranceRepository;
    }

    @Override
    public Page<Insurance> findAllPaginated(Pageable pageable) {
        return insuranceRepository.findAllByOrderByIdDesc(pageable);
    }

    @Override
    public Page<Insurance> findAllByOrderByDateOfPurchaseDesc(Pageable pageable) {
        return insuranceRepository.findAllByOrderByDateOfPurchaseDesc(pageable);
    }

    @Override
    public Page<Insurance> findAllByOrderByDateOfPurchaseAsc(Pageable pageable) {
        return insuranceRepository.findAllByOrderByDateOfPurchaseAsc(pageable);
    }

    @Override
    public Page<Insurance> findAllByCollisionIsTrue(Pageable pageable) {
        return insuranceRepository.findAllByCollisionIsTrue(pageable);
    }

    @Override
    public Page<Insurance> findAllByCollisionIsFalse(Pageable pageable) {
        return insuranceRepository.findAllByCollisionIsFalse(pageable);
    }

    @Override
    public Page<Insurance> findAllByCustomerGenderEquals(String gender, Pageable pageable) {
        return insuranceRepository.findAllByCustomerGenderEquals(gender, pageable);
    }
}
