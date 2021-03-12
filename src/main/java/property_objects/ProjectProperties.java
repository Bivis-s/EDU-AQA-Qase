package property_objects;

import enums.ProjectAccessType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProjectProperties {
    private String projectName;
    private String projectCode;
    private String description;
    private ProjectAccessType projectAccessType;
}
