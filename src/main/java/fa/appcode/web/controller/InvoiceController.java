package fa.appcode.web.controller;

import fa.appcode.web.dto.InvoiceDTO;
import fa.appcode.web.dto.MemberDTO;
import fa.appcode.web.dto.ScheduleSeatDTO;
import fa.appcode.web.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/invoice")
public class InvoiceController {
    private InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("/")
    public ResponseEntity<InvoiceDTO> insertInvoice(@Valid @RequestBody InvoiceDTO invoiceDTO) {
        return new ResponseEntity<>(invoiceService.insertInvoice(invoiceDTO), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDTO> getInvoiceById(@PathVariable("id") int id) {
        return new ResponseEntity<>(invoiceService.findById(id), HttpStatus.OK);
    }
}
