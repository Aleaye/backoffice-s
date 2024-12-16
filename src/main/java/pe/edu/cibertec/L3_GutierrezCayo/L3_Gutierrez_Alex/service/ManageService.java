package pe.edu.cibertec.L3_GutierrezCayo.L3_Gutierrez_Alex.service;
import pe.edu.cibertec.L3_GutierrezCayo.L3_Gutierrez_Alex.dto.CarDto;
import pe.edu.cibertec.L3_GutierrezCayo.L3_Gutierrez_Alex.dto.CarDetailDto;
import java.util.List;
import java.util.Optional;

public interface ManageService {

    List<CarDto> getAllCars() throws Exception;
    Optional<CarDto> getAllCarsById(int id) throws Exception;
    Optional<CarDetailDto> getCarById(int id) throws Exception;
    boolean deleteCarById(int id) throws Exception;
    boolean addCar(CarDetailDto carDetailDto) throws Exception;
    boolean updateCar(CarDto carDto) throws Exception;

}
