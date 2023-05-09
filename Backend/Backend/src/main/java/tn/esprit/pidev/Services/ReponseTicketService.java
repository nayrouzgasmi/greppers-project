package tn.esprit.pidev.Services;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.Entities.ReponseTicket;
import tn.esprit.pidev.Entities.Ticket;
import tn.esprit.pidev.Entities.TicketStatus;
import tn.esprit.pidev.Repositories.ReponseTicketRepository;
import tn.esprit.pidev.Repositories.TicketRepository;
import tn.esprit.pidev.dto.Statistique;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReponseTicketService implements IReponseTicketService {

    ReponseTicketRepository rtr ;
    ReponseTicketRepository reponseTicketRepository;
    TicketRepository ticketRepository;
    ITicketService ticketService;

    @Override
    public List<ReponseTicket> retrieveAllReponseTicket() {
        return rtr.findAll();

    }

    @Override
    public ReponseTicket addReponseTicket(ReponseTicket t,Integer idTicket) {
        Ticket ticket=ticketRepository.findById(idTicket).orElse(null);
        t.setTicket(ticket);
        return rtr.save(t);
    }
    @Override
    public ReponseTicket updateReponseTicket(ReponseTicket rt) {
        rtr.save(rt);
        return rt;
    }

    @Override
    public ReponseTicket retrieveReponseTicket(Integer id) {
        return rtr.findById(id).get();
    }

    @Override
    public void deleteReponseTicket(Integer id) {
        rtr.deleteById(id);
    }


    @Override
    public ReponseTicket editReponseTicket(Integer idReponse,String description){
        ReponseTicket reponseTicket = reponseTicketRepository.findById(idReponse).orElse(null);
        reponseTicket.setDescription(description);
        reponseTicket.setStatus(true);
        return reponseTicketRepository.save(reponseTicket);
    }
    @Override
    public List<ReponseTicket> getTicketNonTraiter(){
        return reponseTicketRepository.findByStatus(false);
    }

    @Override
    public Statistique statistic(){
        Integer intFalse=ticketRepository.countByStatus(TicketStatus.ACCEPTED);
        Integer intTrue=ticketRepository.countByStatus(TicketStatus.PENDING);
        Integer total=0;
        total=intFalse+intTrue;

        Integer pourcentageFalse=(intFalse*100)/total;
        Integer pourcentageTrue=100-pourcentageFalse;
        String chaine="\nTotal Reclamation :"+total+"\nReclamation traiter: "+pourcentageTrue+"%"+"\nReclamation non traiter: "+pourcentageFalse+"%";
        Statistique statistique = new Statistique();
        statistique.setTotal(total);
        statistique.setTraite(pourcentageFalse);
        statistique.setNontraite(pourcentageTrue);
        return statistique;
    }
    @Override
    public Statistique getReclamationByDate (Date dateDebut, Date dateFin){
        List<Ticket> tikets = ticketRepository.findAll();
      List<Ticket> ticketsBetwen= tikets.stream().filter(ticket -> {
            return ticket.getCreationDate().after(dateDebut)&&ticket.getCreationDate().before(dateFin);
        }).collect(Collectors.toList());
        int tiketTotal =(int)ticketsBetwen.stream().count();
        int tiketAccept =(int)ticketsBetwen.stream().filter(ticket -> {return ticket.getStatus().equals(TicketStatus.ACCEPTED);}).count();
      int tiketPending =(int)ticketsBetwen.stream().filter(ticket -> {return ticket.getStatus().equals(TicketStatus.PENDING);}).count();
        Statistique statistique = new Statistique();
        statistique.setTotal(tiketTotal);
        statistique.setTraite(tiketAccept);
        statistique.setNontraite(tiketPending);
        return statistique;
    }


}

