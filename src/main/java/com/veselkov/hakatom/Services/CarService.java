package com.veselkov.hakatom.Services;

import com.veselkov.hakatom.DTO.CarsDTO;
import com.veselkov.hakatom.Entities.Cars;
import com.veselkov.hakatom.Entities.Model;
import com.veselkov.hakatom.Entities.Person;
import com.veselkov.hakatom.Entities.Vendor;
import com.veselkov.hakatom.Repositories.CarRepository;
import com.veselkov.hakatom.Repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private PersonRepository personRepository;

    public void carSave(Long id, String model, Integer horsepower, Long ownerId) {
        String vendor = model.split("-")[0];
        String modelName = model.substring(model.indexOf('-') + 1);

        Vendor vendorEntity = new Vendor();
        Model modelEntity = new Model();
        Person person = personRepository.findById(ownerId).orElse(null);
        Cars cars = new Cars();
        cars.setPerson(person);

        vendorEntity.setVendorName(vendor);

        modelEntity.setModel(modelName);
        modelEntity.setVendors(vendorEntity);

        carRepository.save(new Cars(id, modelEntity, horsepower, ownerId, person));
    }

    public boolean validationCarId(Long id) {
        Optional<Cars> notnull = carRepository.findById(id);

        if (notnull.equals(Optional.empty())) {
            return false;
        } else {
            return true;
        }
    }

    public List carByPersonId(Long personId) {
        List<Cars> carsList = carRepository.findByOwnerId(personId);

        List<CarsDTO> carsDTOSList = new ArrayList<>();

        for (Cars cars : carsList) {

            CarsDTO carsDTO = new CarsDTO();

            carsDTO.setId(cars.getId());
            carsDTO.setHorsepower(cars.getHorsepower());
            carsDTO.setOwnerId(cars.getOwnerId());
            carsDTO.setModel(cars.getModels().getModel() + "-" + cars.getModels().getVendors().getVendorName());
            carsDTOSList.add(carsDTO);
        }
        return carsDTOSList;
    }
}
