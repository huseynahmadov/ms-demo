package kubernetes.mapper;

import kubernetes.dto.StudentResponse;
import kubernetes.dto.request.CreateStudentRequest;
import kubernetes.dto.request.UpdateStudentRequest;
import kubernetes.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    StudentResponse mapStudentToStudentResponse(Student student);

    Student mapStudentRequestToStudent(CreateStudentRequest request);

    Student mapUpdateRequestToEntity(@MappingTarget Student student, UpdateStudentRequest request);

}
