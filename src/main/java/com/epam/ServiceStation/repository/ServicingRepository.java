package com.epam.ServiceStation.repository;

import com.epam.ServiceStation.entity.ServicingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicingRepository extends JpaRepository<ServicingDetails, Integer > {

    List<ServicingDetails> findByVehicleNumber(String vehicleNumber);

}
