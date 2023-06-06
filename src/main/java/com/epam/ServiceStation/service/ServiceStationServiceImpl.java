package com.epam.ServiceStation.service;

/*import com.epam.ServiceStation.client.InsuranceApi;
import com.epam.ServiceStation.client.PoliceApi;*/
import com.epam.ServiceStation.client.PoliceApi;
import com.epam.ServiceStation.entity.TheftVehicleEntity;
import com.epam.ServiceStation.entity.VehicleDTO;
import com.epam.ServiceStation.exception.InsuranceIssueException;
import com.epam.ServiceStation.exception.PoliceVerificatioFailException;
import com.epam.ServiceStation.repository.ServicingRepository;
import com.epam.ServiceStation.entity.ServicingDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ServiceStationServiceImpl implements ServiceStationService {

    @Autowired
    ServicingRepository repository;

    @Autowired
    PoliceApi policeApi;
/*
    @Autowired
    InsuranceApi insuranceApi;*/

    @Override
    public ServicingDetails checkServicing(VehicleDTO vehicle) {
        if(doPoliceCheck(vehicle)){// && checkInsuranceDetails(vehicle)){

            Date deliveryDate  = serviceDateGenerator(vehicle);
            ServicingDetails service = new ServicingDetails();
            service.setService_in_date(new Date());
            service.setService_delivery_date(deliveryDate);
            service.setChassisNumber(vehicle.getChassisNumber());
            service.setVehicleNumber(vehicle.getRegistrationNumber());
            return repository.save(service);

        }else{
            throw new PoliceVerificatioFailException(vehicle.getRegistrationNumber());
        }
    }

    @Override
    public List<ServicingDetails> serviceHistory(String registrationNo) {
        return repository.findByVehicleNumber(registrationNo);
    }


    /* public boolean checkInsuranceDetails(Vehicle vehicle) {

         ResponseEntity<InsuranceDetails> response = insuranceApi.getInsureDetails(vehicle.getRegistrationNumber());
         InsuranceDetails insuranceDetails = response.getBody();
         if(new Date().before(insuranceDetails.getInsureExpireDetails())){
             return true;
         }
         return false;
     }
 */
    public boolean doPoliceCheck(VehicleDTO vehicle){
        ResponseEntity<TheftVehicleEntity> response = policeApi.verifyVehicle(vehicle.getRegistrationNumber(), vehicle.getChassisNumber());
        return "no".equals(response.getBody().getIsTheft());
    }

    /*public List<Vehicle> getVehicle(){
        return repository.findAll();
    }*/

    public Date serviceDateGenerator(VehicleDTO vehicle){
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        if("CAR".equals(vehicle.getVehicleType().name())){
            cal.add(Calendar.DATE, 2);
            return cal.getTime();
        }else if("BIKE".equals(vehicle.getVehicleType())){

            cal.add(Calendar.DATE, 1);
            return cal.getTime();
        }
        return today;
    }


}
