package api.objects;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateNewSuiteRequest {
    String title;
    String description;
    String preconditions;
    @SerializedName("parent_id")
    int parentId;
}
