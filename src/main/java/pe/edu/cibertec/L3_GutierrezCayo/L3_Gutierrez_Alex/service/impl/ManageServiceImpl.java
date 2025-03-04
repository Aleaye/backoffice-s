package pe.edu.cibertec.L3_GutierrezCayo.L3_Gutierrez_Alex.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.L3_GutierrezCayo.L3_Gutierrez_Alex.dto.CarDto;
import pe.edu.cibertec.L3_GutierrezCayo.L3_Gutierrez_Alex.dto.CarDetailDto;
import pe.edu.cibertec.L3_GutierrezCayo.L3_Gutierrez_Alex.entity.Car;
import pe.edu.cibertec.L3_GutierrezCayo.L3_Gutierrez_Alex.repository.CarRepository;
import pe.edu.cibertec.L3_GutierrezCayo.L3_Gutierrez_Alex.service.ManageService;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    CarRepository carRepository;

    @Override
    public List<CarDto> getAllCars() throws Exception {
        List<CarDto> cars = new ArrayList<>();
        Iterable<Car> iterable = carRepository.findAll();

        if (!iterable.iterator().hasNext()) {
            throw new Exception("No cars found");
        }

        iterable.forEach(car -> {
            CarDto dto = new CarDto(
                    car.getCar_id(),
                    car.getMake(),
                    car.getModel(),
                    car.getLicense_plate(),
                    car.getOwner_name(),
                    car.getPurchase_date(),
                    car.getMileage(),
                    car.getColor()
            );
            cars.add(dto);
        });

        return cars;
    }

    @Override
    public Optional<CarDto> getAllCarsById(int id) throws Exception {
        Optional<Car> optional = carRepository.findById(id);

        if (optional.isEmpty()) {
            throw new Exception("Car with ID " + id + " not found");
        }

        return optional.map(car -> new CarDto(
                car.getCar_id(),
                car.getMake(),
                car.getModel(),
                car.getLicense_plate(),
                car.getOwner_name(),
                car.getPurchase_date(),
                car.getMileage(),
                car.getColor()
        ));
    }

    @Override
    public Optional<CarDetailDto> getCarById(int id) throws Exception {
        Optional<Car> optional = carRepository.findById(id);

        if (optional.isEmpty()) {
            throw new Exception("Car with ID " + id + " not found");
        }

        return optional.map(car -> new CarDetailDto(
                car.getCar_id(),
                car.getMake(),
                car.getModel(),
                car.getYear(),
                car.getVin(),
                car.getLicense_plate(),
                car.getOwner_name(),
                car.getOwner_contact(),
                car.getPurchase_date(),
                car.getMileage(),
                car.getEngine_type(),
                car.getColor(),
                car.getInsurance_company(),
                car.getInsurance_policy_number(),
                car.getRegistration_expiration_date(),
                car.getService_due_date()
        ));
    }

    @Override
    public boolean updateCar(CarDto carUpdateDto) throws Exception {
        Optional<Car> optional = carRepository.findById(carUpdateDto.car_id());

        if (optional.isEmpty()) {
            throw new Exception("Car with ID " + carUpdateDto.car_id() + " not found for update");
        }

        optional.ifPresent(car -> {
            car.setMake(carUpdateDto.make());
            car.setModel(carUpdateDto.model());
            car.setLicense_plate(carUpdateDto.license_plate());
            car.setOwner_name(carUpdateDto.owner_name());
            car.setPurchase_date(carUpdateDto.purchase_date());
            car.setMileage(carUpdateDto.mileage());
            car.setColor(carUpdateDto.color());
            carRepository.save(car);
        });

        return true;
    }

    @Override
    public boolean deleteCarById(int id) throws Exception {
        Optional<Car> optional = carRepository.findById(id);

        if (optional.isEmpty()) {
            throw new Exception("Car with ID " + id + " not found for deletion");
        }

        optional.ifPresent(carRepository::delete);
        return true;
    }

    @Override
    public boolean addCar(CarDetailDto carDetailDto) throws Exception {
        Optional<Car> optional = carRepository.findById(carDetailDto.car_id());

        if (optional.isPresent()) {
            throw new Exception("Car with ID " + carDetailDto.car_id() + " already exists");
        }

        Car car = new Car();
        car.setMake(carDetailDto.make());
        car.setModel(carDetailDto.model());
        car.setYear(carDetailDto.year());
        car.setVin(carDetailDto.vin());
        car.setLicense_plate(carDetailDto.license_plate());
        car.setOwner_name(carDetailDto.owner_name());
        car.setOwner_contact(carDetailDto.owner_contact());
        car.setPurchase_date(carDetailDto.purchase_date());
        car.setMileage(carDetailDto.mileage());
        car.setEngine_type(carDetailDto.engine_type());
        car.setColor(carDetailDto.color());
        car.setInsurance_company(carDetailDto.insurance_company());
        car.setRegistration_expiration_date(carDetailDto.registration_expiration_date());
        car.setService_due_date(carDetailDto.service_due_date());
        carRepository.save(car);

        return true;
    }
}