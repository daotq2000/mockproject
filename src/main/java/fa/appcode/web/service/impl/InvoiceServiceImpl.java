package fa.appcode.web.service.impl;

import fa.appcode.web.commons.exception.NotFoundException;
import fa.appcode.web.commons.utility.CONSTANT;
import fa.appcode.web.converter.InvoiceConverter;
import fa.appcode.web.converter.ScheduleSeatConverter;
import fa.appcode.web.dto.InvoiceDTO;
import fa.appcode.web.dto.ScheduleSeatDTO;
import fa.appcode.web.entities.Account;
import fa.appcode.web.entities.Invoice;
import fa.appcode.web.entities.ScheduleSeat;
import fa.appcode.web.entities.Ticket;
import fa.appcode.web.repository.AccountRepository;
import fa.appcode.web.repository.InvoiceRepository;
import fa.appcode.web.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private InvoiceConverter invoiceConverter;
    @Autowired
    private ScheduleSeatConverter scheduleSeatConverter;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public InvoiceDTO insertInvoice(InvoiceDTO invoiceDTO) {
        System.out.println(invoiceDTO.getScheduleSeats());
        if (invoiceDTO.getScheduleSeats() == null) {
            throw new IllegalArgumentException("Schedule Seats cannot be null");
        }
        Account account = null;
        if (invoiceDTO.getAccount() != null) {
            account = accountRepository.getOne(invoiceDTO.getAccount().getId());
        }
        Invoice invoice = invoiceConverter.convertToEntity(invoiceDTO);
        List<Ticket> tickets = new ArrayList<>();
        if (null != invoiceDTO.getScheduleSeats()) {
            List<ScheduleSeatDTO> scheduleSeatDTOS = invoiceDTO.getScheduleSeats();
            if(null != scheduleSeatDTOS){
                List<ScheduleSeat> scheduleSeats = scheduleSeatDTOS.stream().map(scheduleSeatDTO -> {
                    return scheduleSeatConverter.convertToEntity(scheduleSeatDTO);
                }).collect(Collectors.toList());
                scheduleSeats.forEach(scheduleSeat -> {
                    scheduleSeat.setDateBooking(invoiceDTO.getScheduleShow());
                    Ticket ticket = new Ticket();
                    ticket.setInvoice(invoice);
                    ticket.setPrice(scheduleSeat.getSeatPrice());
                    ticket.setScheduleSeat(scheduleSeat);
                    tickets.add(ticket);
                    scheduleSeat.setTickets(tickets);
                });
            }

        }
        AtomicReference<Long> total = new AtomicReference<>(0L);
        tickets.forEach(ticket -> {
            total.updateAndGet(v -> v + ticket.getPrice().longValue());
        });
        invoice.setTotalMoney(BigDecimal.valueOf(total.get()));
        invoice.setBookingDate(new Date());
        invoice.setTickets(tickets);
        invoice.setStatus(CONSTANT.PAID_SUCCESS);
        if (account != null) {
            invoice.setAccount(account);
        }
        Invoice invoiceSave = invoiceRepository.save(invoice);
        return invoiceConverter.convertToDTO(invoiceSave);
    }

    @Override
    public List<InvoiceDTO> findAll() {
        List<Invoice> invoices = invoiceRepository.findAll();
        return invoices.stream().map(invoice -> invoiceConverter.convertToDTO(invoice)).collect(Collectors.toList());
    }

    @Override
    public InvoiceDTO findById(int id) {
        Optional<Invoice> invoice = invoiceRepository.findById(id);
        if (invoice.isPresent()) {
            return invoiceConverter.convertToDTO(invoice.get());
        } else {
            throw new NotFoundException("Invoice Not Found");
        }
    }
}
