package tn.esprit.pidev.Services;


import tn.esprit.pidev.Entities.ReponseTicket;
import tn.esprit.pidev.dto.Statistique;

import java.util.Date;
import java.util.List;

public interface IReponseTicketService {

     Statistique statistic();
        List<ReponseTicket> getTicketNonTraiter();

     List<ReponseTicket> retrieveAllReponseTicket();

    ReponseTicket addReponseTicket(ReponseTicket tr ,Integer idTicket);

    ReponseTicket updateReponseTicket (ReponseTicket tr);

    ReponseTicket retrieveReponseTicket (Integer id);

    void deleteReponseTicket ( Integer id);

    ReponseTicket editReponseTicket(Integer idReponse,String description);
    public Statistique getReclamationByDate (Date dateDebut, Date dateFin);
}
