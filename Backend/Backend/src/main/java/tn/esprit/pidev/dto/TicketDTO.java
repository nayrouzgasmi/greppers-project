package tn.esprit.pidev.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
@Getter @Setter
@NoArgsConstructor
public class TicketDTO {
    Date dateDebut;
    Date dateFin;
}
