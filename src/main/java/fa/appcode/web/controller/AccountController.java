package fa.appcode.web.controller;

import fa.appcode.web.dto.AccountDTO;
import fa.appcode.web.entities.Account;
import fa.appcode.web.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;
    @RequestMapping(value = "/register-form", method = RequestMethod.GET)
    public String showRegister(Model model) {
        model.addAttribute("accountDTO", new AccountDTO());
        return "register";
    }

    @RequestMapping(value = "/register-account", method = RequestMethod.POST)
    public String registerAccount(@ModelAttribute(value = "accountDTO") AccountDTO accountDTO) throws ParseException {
        accountService.save(accountDTO);
        return "index";
    }


    @RequestMapping(value = "/account/{id}/edit", method = RequestMethod.GET)
    public String showAccount(@PathVariable(value = "id") int id, Model model) throws ParseException {
        model.addAttribute("accountDTO", accountService.findById(id));
        return "account-detail";
    }
    @RequestMapping(value = "/update-account", method = RequestMethod.POST)
    public String updateAccount(@ModelAttribute(value = "accountDTO") AccountDTO accountDTO) throws ParseException {
        accountService.update(accountDTO);
        return "index";
    }
    @GetMapping("/account/delete")
    public String delete(@RequestParam("accountId") int theId) {
        // delete the post
        accountService.delete(theId);
        return "redirect:/account/page/1";
    }
    @GetMapping("/account/search/{pageNumber}")
    public String search(@RequestParam("key") String key, Model model, HttpServletRequest request,
                         @PathVariable int pageNumber) throws ParseException {
        if ("".equals(key)) {
            return "redirect:/account/page/1";
        }
        List<AccountDTO> list = accountService.search(key);
        if (list == null) {
            return "redirect:/account/page/1";
        }
        PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("employeeList");
        int pageSize = 3;
        pages = new PagedListHolder<>(list);
        pages.setPageSize(pageSize);
        final int goToPage = pageNumber - 1;
        if (goToPage <= pages.getPageCount() && goToPage >= 0) {
            pages.setPage(goToPage);
        }
        request.getSession().setAttribute("employeeList", pages);
        int current = pages.getPage() + 1;
        int begin = Math.max(1, current - list.size());
        int end = Math.min(begin + 5, pages.getPageCount());
        int totalPageCount = pages.getPageCount();
        String baseUrl = "/account/page/";
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("accounts", pages.getPageList());
        return "account-list";
    }

    @GetMapping("/account/page/{pageNumber}")
    public String showAccountPage(HttpServletRequest request,
                                  @PathVariable int pageNumber, Model model) throws ParseException{
        PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("accountList");
        int pageSize = 3;
        List<AccountDTO> list = accountService.findAll();
        if (pages == null) {
            pages = new PagedListHolder<>(list);
            pages.setPageSize(pageSize);
        } else {
            final int goToPage = pageNumber - 1;
            if (goToPage <= pages.getPageCount() && goToPage >= 0) {
                pages.setPage(goToPage);
            }
        }
        request.getSession().setAttribute("accountList", pages);
        int current = pages.getPage() + 1;
        int begin = Math.max(1, current - list.size());
        int end = Math.min(begin + 5, pages.getPageCount());
        int totalPageCount = pages.getPageCount();
        String baseUrl = "/account/page/";
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("accounts", pages.getPageList());
        return "account-list";
    }
}
