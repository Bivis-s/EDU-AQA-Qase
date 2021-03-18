package api.objects;

import enums.api.CreateNewProjectAccess;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateNewProjectRequest implements ApiRequest {
    String title;
    String code; //should be from 2 to 6 latin symbols
    String description;
    CreateNewProjectAccess access;
    String group;
}
