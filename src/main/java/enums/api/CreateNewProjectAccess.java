package enums.api;

import com.google.gson.annotations.SerializedName;

public enum CreateNewProjectAccess {
    @SerializedName("all")
    ALL,
    @SerializedName("group")
    GROUP,
    @SerializedName("none")
    NONE
}
