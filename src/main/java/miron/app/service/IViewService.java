package miron.app.service;

import miron.app.view.BootgridTableRequest;
import miron.app.view.BootgridTableResponse;

import javax.transaction.Transactional;

public interface IViewService<T extends BootgridTableRequest> {

    @Transactional
    BootgridTableResponse createBootgridTableModel(T bootgridTableRequest);
}
