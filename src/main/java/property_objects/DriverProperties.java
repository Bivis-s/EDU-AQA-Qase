package property_objects;

import enums.PropertyDriverType;
import lombok.Data;

@Data
public class DriverProperties implements Properties {
    private PropertyDriverType type;
    private boolean headless;
    private boolean incognito;
    private boolean maximize;
}
