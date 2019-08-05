package miron.app.view;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class BootgridTableRequest {
    private Integer current;
    private Integer rowCount;
    private Map<String, String> filters = new HashMap<>();
    private Map<String, String> sorts = new HashMap<>();
}
