package miron.app.service;

import lombok.AllArgsConstructor;
import miron.app.model.Todo;
import miron.app.dao.TodoDao;
import miron.app.view.BootgridTableRequest;
import miron.app.view.BootgridTableResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService implements ICrudService<Todo, TodoDao>, IViewService<BootgridTableRequest> {
    private TodoDao todoDao;

    @Override
    public BootgridTableResponse createBootgridTableModel(BootgridTableRequest bootgridTableRequest) {
        final BootgridTableResponse bootgridTableResponse = new BootgridTableResponse();
        final List<Todo> list = todoDao.getAllByTableRequest(bootgridTableRequest);
        bootgridTableResponse.setRows(list);
        bootgridTableResponse.setRowCount(bootgridTableRequest.getRowCount());
        bootgridTableResponse.setCurrent(bootgridTableRequest.getCurrent());
        bootgridTableResponse.setTotal(todoDao.getTotalRowCount(bootgridTableRequest));
        return bootgridTableResponse;
    }

    @Override
    public TodoDao getDao() { return todoDao; }
}
