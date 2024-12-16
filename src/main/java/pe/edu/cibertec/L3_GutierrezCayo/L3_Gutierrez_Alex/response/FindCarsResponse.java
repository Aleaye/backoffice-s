package pe.edu.cibertec.L3_GutierrezCayo.L3_Gutierrez_Alex.response;

import pe.edu.cibertec.L3_GutierrezCayo.L3_Gutierrez_Alex.dto.CarDto;

public record FindCarsResponse(String code,
                               String error,
                               Iterable<CarDto> cars) {
}
