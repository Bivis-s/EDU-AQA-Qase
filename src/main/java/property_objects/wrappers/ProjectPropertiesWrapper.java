package property_objects.wrappers;

import lombok.Data;
import property_objects.ProjectProperties;

@Data
public class ProjectPropertiesWrapper {
    ProjectProperties projectProperties;

    public String getProjectCode() {
        return projectProperties.getProjectCode();
    }

    public String getProjectName() {
        return projectProperties.getProjectName();
    }
}
