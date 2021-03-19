package property_objects;

import api.objects.CreateNewSuiteRequest;
import lombok.Data;

@Data
public class SuiteProperties {
    private String name;
    private String parentSuiteName;
    private String description;
    private String precondition;

    // parentSuiteName is not set
    public void setPropertiesByApiRequest(CreateNewSuiteRequest request) {
        setName(request.getTitle());
        setDescription(request.getDescription());
        setPrecondition(request.getPreconditions());
    }
}
