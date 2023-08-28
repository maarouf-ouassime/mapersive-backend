package com.mapersive.mapersivebackend.controllers;

import com.mapersive.mapersivebackend.models.Insurance;
import com.mapersive.mapersivebackend.services.insurance.InsuranceService;
import com.mapersive.mapersivebackend.utils.CustomErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin(origins = { "${app.api.settings.cross-origin.urls}" })
@RequestMapping("/api/v1/insurance")
@RestController
public class InsuranceController {

    @Autowired
    InsuranceService insuranceService;

    // -----Retrieve All Insurances -------------------

    @GetMapping(value = "/")
    public ResponseEntity<List<Insurance>> listAllInsurances() {

        List<Insurance> insurances = insuranceService.findAll();
        if (insurances.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Insurance>>(insurances, HttpStatus.OK);
    }

    // ------------Retrieve Single Insurance --------------------------

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getInsurance(@PathVariable("id") long id) {
        log.info("Fetching Insurance with id {}", id);
        Insurance insurance = insuranceService.findById(id);
        if (insurance == null) {
            log.error("Insurance with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Insurance with id " + id + " not found"),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Insurance>(insurance, HttpStatus.OK);
    }

    // -------------------Create Insurance ------------

    @PostMapping(value = "/")
    public ResponseEntity<?> createInsurance(@RequestBody Insurance insurance, UriComponentsBuilder ucBuilder) {
        log.info("Creating Insurance : {}", insurance);
        insuranceService.save(insurance);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/v1/insurance/{id}").buildAndExpand(insurance.getPolicyId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update Insurance ---------------------------

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateInsurance(@PathVariable("id") long id, @RequestBody Insurance insurance) {
        log.info("Updating Insurance with id {}", id);

        Insurance currentInsurance = insuranceService.findById(id);

        if (currentInsurance == null) {
            log.error("Unable to update. Insurance with id {} : not found.", id);
            return new ResponseEntity<>(
                    new CustomErrorType("Unable to update. Insurance with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        insuranceService.update(currentInsurance);
        return new ResponseEntity<Insurance>(currentInsurance, HttpStatus.OK);
    }

    // ------------------- Delete an Insurance------------------

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteInsurance(@PathVariable("id") long id) {
        log.info("Fetching & Deleting Insurance with id {}", id);

        Insurance insurance = insuranceService.findById(id);
        if (insurance == null) {
            log.error("Unable to delete. Insurance with id {} not found.", id);
            return new ResponseEntity<>(
                    new CustomErrorType("Unable to delete. Insurance with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        insuranceService.deleteById(id);
        return new ResponseEntity<Insurance>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/pagination/{filter}/{page}/{size}")
    public ResponseEntity<Map<String, Object>> getInsurancesPaginated(
            @PathVariable("filter") String filter,
            @PathVariable("page") int page,
            @PathVariable("size") int size) {
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Insurance> pageInsurances = null;
            switch (filter) {
                case "all":
                    pageInsurances = insuranceService.findAllPaginated(paging);
                    break;
                case "dateOfPurchaseDesc":
                    pageInsurances = insuranceService.findAllByOrderByDateOfPurchaseDesc(paging);
                    break;
                case "dateOfPurchaseAsc":
                    pageInsurances = insuranceService.findAllByOrderByDateOfPurchaseAsc(paging);
                    break;
                case "collisionTrue":
                    pageInsurances = insuranceService.findAllByCollisionIsTrue(paging);
                    break;
                case "collisionFalse":
                    pageInsurances = insuranceService.findAllByCollisionIsFalse(paging);
                    break;
                case "genderMale":
                    pageInsurances = insuranceService.findAllByCustomerGenderEquals("Male", paging);
                    break;
                case "genderFemale":
                    pageInsurances = insuranceService.findAllByCustomerGenderEquals("Female", paging);
                    break;
            }
            List<Insurance> insurances = pageInsurances.getContent();
            Map<String, Object> response = new HashMap<>();

            response.put("objects", insurances);
            response.put("currentPage", pageInsurances.getNumber());
            response.put("totalItems", pageInsurances.getTotalElements());
            response.put("totalPages", pageInsurances.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("error in api/insurance/pagination/{page}/{size}");
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
