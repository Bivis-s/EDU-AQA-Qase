package property_objects;

import lombok.Data;
import lombok.NoArgsConstructor;
import setups.DriverPropertyFactory;

@Data
@NoArgsConstructor
public class DriverProperties {
    private DriverPropertyFactory.Type type;
    private boolean headless;
    private boolean incognito;
    private boolean maximize;
}
