package setups;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DriverProperties {
    private DriverType type;
    private boolean headless;
    private boolean incognito;
    private boolean maximize;
}
