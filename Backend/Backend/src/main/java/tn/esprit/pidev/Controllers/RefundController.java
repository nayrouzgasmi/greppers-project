package tn.esprit.pidev.Controllers;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.Entities.Refund;
import tn.esprit.pidev.Services.IRefundService;

import java.util.List;

@Controller
@RestController
@AllArgsConstructor
@RequestMapping("/ticket")

public class RefundController {
    IRefundService refundService;

    @GetMapping("/retrieve-all-refunds")
    public List<Refund> getTicket() {
        List<Refund> listRefunds = refundService.retrieveAllRefund();
        return listRefunds;
    }

    @GetMapping("/retrieve-Refund/{id}")
    public Refund retrieveRefund(@PathVariable("id") Integer id) {
        return refundService.retrieveRefund(id);
    }

    @PostMapping("/add-Refund")
    public Refund addTicket(@RequestBody Refund r ) {
        Refund rf = refundService.addRefund(r);
        return rf;
    }
    @DeleteMapping("/remove-Refund/{id}")
    public void removeRefund(@PathVariable("id") Integer id) {
        refundService.deleteRefund(id);
    }

    @PutMapping("/update-Refund")
    public Refund updateRefund(@RequestBody Refund r) {
        Refund tf= refundService.updateRefund(r);
        return tf;
    }
}
