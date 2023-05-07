package tn.esprit.pidev.Controllers;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.Entities.Ticket;
import tn.esprit.pidev.Services.ITicketService;

import java.util.List;

@Controller
@RestController
@AllArgsConstructor
@RequestMapping("/ticket")
@CrossOrigin(origins = "http://localhost:4200")

public class TicketController {
    ITicketService ticketService;

    @RequestMapping(method = RequestMethod.GET , value = "/retrieve-all-tickets")
    public List<Ticket> getTicket() {
        List<Ticket> listTickets = ticketService.retrieveAllTicket();
        return listTickets;
    }


    @RequestMapping(method = RequestMethod.GET , value = "/retrieve-all-ticketss")
    public List<Ticket> getTickets() {
        List<Ticket> listTickets = ticketService.retrieveAllTicketsatus();
        return listTickets;
    }

    @RequestMapping(method = RequestMethod.GET ,value= "/retrieve-Ticket/{id}")
    public Ticket retrieveTicket(@PathVariable("id") Integer id ) {
        return ticketService.retrieveTicket(id);
    }

    @RequestMapping(method = RequestMethod.POST , value= "/retrieve-all-tickets/{idUser}/{idProduct}")
    public Ticket addTicket(@RequestBody Ticket t ,@PathVariable  Long idUser ,@PathVariable Integer idProduct ) {

        Ticket tt = ticketService.addTicket(t,idUser,idProduct);

        return tt;
    }
    @DeleteMapping("/retrieve-all-tickets/{id}")
    public void removeTicket(@PathVariable Integer id) {
        ticketService.deleteTicket(id);
    }

    @PutMapping ( "/retrieve-all-tickets/{id}")
    public Ticket updateTicket(@PathVariable Integer id,@RequestBody Ticket t) {

        Ticket tt= ticketService.retrieveTicket(id);

        tt.setTitle(t.getTitle());
        tt.setDescription(t.getDescription());
        tt.setCreationDate(t.getCreationDate());
       tt.setSatisfaction(t.getSatisfaction());
       tt.setPriority(t.getPriority());
       tt.setType(t.getType());
       tt.setStatus(t.getStatus());
       ticketService.updateTicket(tt);
        return tt;
    }
}
