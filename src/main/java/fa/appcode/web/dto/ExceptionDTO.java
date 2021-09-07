package fa.appcode.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDTO {
    private Date timestamp;
    private int status;
    private String message;
    private List<ObjectError> errors;
    private String path;


    public void setErrors(List<ObjectError> allErrors) {
        this.errors = allErrors;
    }
}
