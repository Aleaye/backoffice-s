package pe.edu.cibertec.L3_GutierrezCayo.L3_Gutierrez_Alex.response;

import pe.edu.cibertec.L3_GutierrezCayo.L3_Gutierrez_Alex.dto.CarDetailDto;

public record FindCarByIdResponse(String code,
                                  String error,
                                  CarDetailDto car) {
}
