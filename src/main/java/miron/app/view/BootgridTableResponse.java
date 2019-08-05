package miron.app.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.LinkedList;

@Getter
@Setter
@NoArgsConstructor
public class BootgridTableResponse {
    private int current;
    private int rowCount;
    private int total;
    private Collection<?> rows;

    public static BootgridTableResponse createEmptyTableResponse() {
        BootgridTableResponse empty = new BootgridTableResponse();
        empty.setCurrent(0);
        empty.setRowCount(0);
        empty.setTotal(0);
        empty.setRows(new LinkedList<>());
        return empty;
    }
}
