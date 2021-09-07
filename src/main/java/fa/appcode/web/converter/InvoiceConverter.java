package fa.appcode.web.converter;

import fa.appcode.web.dto.InvoiceDTO;
import fa.appcode.web.entities.Invoice;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InvoiceConverter {
    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private AccountConverter accountConverter;
    public InvoiceDTO convertToDTO(Invoice invoice){
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setUserScore(invoice.getUserScore());
        invoiceDTO.setStatus(invoice.getStatus());
        invoiceDTO.setAddScore(invoice.getAddScore());
        invoiceDTO.setBookingDate(invoice.getBookingDate());
        invoiceDTO.setCustomerIdentityCard(invoice.getCustomerIdentityCard());
        invoiceDTO.setCustomerName(invoice.getCustomerName());
        invoiceDTO.setCustomerPhone(invoice.getCustomerPhone());
        invoiceDTO.setScheduleShowTime(invoice.getScheduleShowTime());
        invoiceDTO.setTotalMoney(invoice.getTotalMoney());
        invoiceDTO.setSeat(invoice.getSeat());
        invoiceDTO.setMovieName(invoice.getMovieName());
        if(invoice.getAccount() != null){
            invoiceDTO.setAccount(accountConverter.convertForMemberDTO(invoice.getAccount()));
        }
        invoiceDTO.setId(invoice.getId());
        invoiceDTO.setScheduleShowTime(invoice.getScheduleShowTime());
        invoiceDTO.setScheduleShow(invoice.getScheduleShow());
        return invoiceDTO;
    }

    public Invoice convertToEntity(InvoiceDTO invoiceDTO){
        return modelMapper.map(invoiceDTO,Invoice.class);
    }

}
