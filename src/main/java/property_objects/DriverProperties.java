package property_objects;

import enums.PropertyDriverType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DriverProperties {
    private PropertyDriverType type;
    private boolean headless;
    private boolean incognito;
    private boolean maximize;
}
