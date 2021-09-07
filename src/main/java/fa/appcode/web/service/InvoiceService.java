package fa.appcode.web.service;

import fa.appcode.web.dto.InvoiceDTO;
import fa.appcode.web.dto.ScheduleSeatDTO;
import fa.appcode.web.entities.Invoice;

import java.util.List;

public interface InvoiceService {
    InvoiceDTO insertInvoice(InvoiceDTO invoiceDTO);
    List<InvoiceDTO> findAll();
    InvoiceDTO findById(int id);
}
