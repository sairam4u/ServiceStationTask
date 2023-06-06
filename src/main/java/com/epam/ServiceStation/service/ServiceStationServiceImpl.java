package com.epam.ServiceStation.service;

/*import com.epam.ServiceStation.client.InsuranceApi;
import com.epam.ServiceStation.client.PoliceApi;*/
import com.epam.ServiceStation.client.PoliceApi;
import com.epam.ServiceStation.entity.InsuranceDetails;
import com.epam.ServiceStation.entity.TheftVehicleEntity;
import com.epam.ServiceStation.entity.Vehicle;
import com.epam.ServiceStation.repository.ServicingRepository;
import com.epam.ServiceStation.entity.ServicingDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

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
    public ServicingDetails checkServicing(Vehicle vehicle) {
        if(doPoliceCheck(vehicle)){// && checkInsuranceDetails(vehicle)){

            Date deliveryDate  = serviceDateGenerator(vehicle);
            ServicingDetails service = new ServicingDetails();
            service.setService_in_date(new Date());
            service.setService_delivery_date(deliveryDate);
            service.setChassisNumber(vehicle.getChassisNumber());
            service.setVehicle_number(vehicle.getRegistrationNumber());
            return repository.save(service);

        }else{
            throw new InsuranceIssueException(vehicle.getRegistrationNumber());
        }
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
    public boolean doPoliceCheck(Vehicle vehicle){
        ResponseEntity<TheftVehicleEntity> response = policeApi.verifyVehicle(vehicle.getRegistrationNumber(), vehicle.getChassisNumber());
        return "yes".equals(response.getBody().getIsTheft());
    }

    /*public List<Vehicle> getVehicle(){
        return repository.findAll();
    }*/

    public Date serviceDateGenerator(Vehicle vehicle){
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
