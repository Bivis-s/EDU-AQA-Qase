package api.objects;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;

@Builder
public class CreateNewSuiteRequest {
    private final String title;
    private final String description;
    private final String preconditions;
    @SerializedName("parent_id")
    private final int parentId;
}
