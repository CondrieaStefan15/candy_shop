package ro.deloittedigital.bootcampbackend.boundry.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class ErrorDTO {

    private int statusCode;
    private String message;

}
