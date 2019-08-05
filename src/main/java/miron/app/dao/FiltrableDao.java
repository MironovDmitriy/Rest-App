package miron.app.dao;

import miron.app.view.BootgridTableRequest;

import java.util.List;

public interface FiltrableDao<T> {

    List<?> getAllByTableRequest(BootgridTableRequest bootgridTableRequest);

    int getTotalRowCount(BootgridTableRequest bootgridTableRequest);
}
