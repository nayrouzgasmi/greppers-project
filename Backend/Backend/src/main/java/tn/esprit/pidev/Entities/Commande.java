package tn.esprit.pidev.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Component
public class Commande implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCommande;
    private String adresse ;
    private Date date ;
    private String region ;
    private double totalPrice;
    private String nomclient;
    private String emailclient;


    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "idclient")
    private Client client;

   // @ManyToOne(fetch = FetchType.LAZY)
   // @JoinColumn(name = "client_id", referencedColumnName = "id")
  //  private Client client;

    //@OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    //private List<Panier> paniers ;

}
