package property_objects;

import api.objects.CreateNewProjectRequest;
import enums.ProjectAccessType;
import lombok.Data;

@Data
public class ProjectProperties implements Properties {
    private String projectName;
    private String projectCode;
    private String description;
    private ProjectAccessType projectAccessType;

    // ProjectAccessType is not set
    public void setByApiRequest(CreateNewProjectRequest request) {
        setProjectName(request.getTitle());
        setProjectCode(request.getCode());
        setDescription(request.getDescription());
    }
}
