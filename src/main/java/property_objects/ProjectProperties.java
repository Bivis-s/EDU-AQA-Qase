package property_objects;

import enums.ProjectAccessType;
import lombok.Data;

@Data
public class ProjectProperties implements Properties {
    private String projectName;
    private String projectCode;
    private String description;
    private ProjectAccessType projectAccessType;
}
