package tn.esprit.pidev.Controllers;



import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.Entities.ReponseTicket;
import tn.esprit.pidev.Entities.Ticket;
import tn.esprit.pidev.Entities.TicketStatus;
import tn.esprit.pidev.Services.IReponseTicketService;
import tn.esprit.pidev.Services.ITicketService;
import tn.esprit.pidev.dto.Statistique;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/reponseTicket")
@CrossOrigin(origins = "http://localhost:4200")

public class ReponseTicketController {
    IReponseTicketService rtservice;
    ITicketService ticketService;

    @GetMapping("/retrieve-all-ReponseTickets")
    public List<ReponseTicket> getTicket() {
        List<ReponseTicket> listReponseTickets = rtservice.retrieveAllReponseTicket();

        return listReponseTickets;
    }


    @RequestMapping("/retrieve-ReponseTicket/{id}")
    public ReponseTicket retrieveReponseTicket(@PathVariable("id") Integer id) {
        return rtservice.retrieveReponseTicket(id);
    }

    @PostMapping("/add-ReponseTicket/{idTicket}")
    public ReponseTicket addTicket(@RequestBody ReponseTicket r ,@PathVariable Integer idTicket) {
        Ticket ticket=ticketService.retrieveTicket(idTicket);
        ticket.setStatus(TicketStatus.ACCEPTED);
        ticketService.updateTicket(ticket);
        ReponseTicket rt = rtservice.addReponseTicket(r,idTicket);
        return rt;
    }
    @DeleteMapping("/remove-ReponseTicket/{id}")
    public void removeReponseTicket(@PathVariable("id") Integer id) {
        rtservice.deleteReponseTicket(id);


    }

    @PutMapping("/update-reponse")
    public ReponseTicket updateReponseTicket(@RequestBody ReponseTicket r) {
        ReponseTicket rt= rtservice.updateReponseTicket(r);
        return rt;
    }

    @PutMapping ( "/update-reponse/{id}")
    public ReponseTicket updateTicket(@PathVariable Integer id,@RequestBody ReponseTicket t) {

        ReponseTicket tt= rtservice.retrieveReponseTicket(id);


        tt.setDescription(t.getDescription());
       tt.setSujet(t.getSujet());
       tt.setDateCreation(t.getDateCreation());
        rtservice.updateReponseTicket(tt);
        return tt;
    }


    @GetMapping("/getAllReponseNonTraiter")
    public List<ReponseTicket> getAllReponseNonTraiter(){
       return rtservice.getTicketNonTraiter();
    }


    @PutMapping("/editReponse/{idReponse}/{description}")
    public ReponseTicket editReponseTicket(@PathVariable Integer idReponse,@PathVariable String description)
    {
        return rtservice.editReponseTicket(idReponse,description);
    }

    @GetMapping("/stat")
    public Statistique statistic() {
        return rtservice.statistic();
    }
    }
