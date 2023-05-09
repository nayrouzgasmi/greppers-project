package tn.esprit.pidev.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "reponse_Ticket")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class ReponseTicket implements Serializable  {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;


        @Column()
        private String sujet;

        @Column(name = "Description")
        private String Description;

        @Column(name = "Status")
        private boolean status=false ;



        @Column(name = "date_creation")
        private Date dateCreation;


        @JsonIgnore
        @OneToOne(cascade = CascadeType.ALL)
        Ticket ticket;



}


