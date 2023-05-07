package tn.esprit.pidev.Services;


import tn.esprit.pidev.Entities.Ticket;

import java.util.List;

public interface ITicketService {

    List<Ticket> retrieveAllTicket();
    List<Ticket> retrieveAllTicketsatus();

    Ticket addTicket(Ticket t ,Long idUser,Integer idProduct);

    Ticket updateTicket (Ticket t);

    Ticket retrieveTicket (Integer id);

    void deleteTicket ( Integer id);
}
