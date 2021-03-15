package api.objects;

import enums.api.CreateNewProjectAccess;
import lombok.Builder;

@Builder
public class CreateNewProjectRequest {
    private final String title;
    private final String code; //should be from 2 to 6 latin symbols
    private final String description;
    private final CreateNewProjectAccess access;
    private final String group;
}
