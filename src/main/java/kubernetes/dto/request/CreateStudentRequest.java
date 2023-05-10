package kubernetes.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentRequest {

    @NotBlank(message = "Student field be empty")
    private String name;

    @NotBlank(message = "Surname field be empty")
    private String surname;

    @NotBlank(message = "Group name field be empty")
    private String groupName;

}
