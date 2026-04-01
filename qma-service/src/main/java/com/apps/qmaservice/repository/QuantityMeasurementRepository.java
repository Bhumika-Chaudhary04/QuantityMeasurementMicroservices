package com.apps.qmaservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apps.qmaservice.entity.QuantityMeasurementEntity;

@Repository
public interface QuantityMeasurementRepository extends JpaRepository<QuantityMeasurementEntity, Long> {

	List<QuantityMeasurementEntity> findByOperationIgnoreCase(String operation);
}