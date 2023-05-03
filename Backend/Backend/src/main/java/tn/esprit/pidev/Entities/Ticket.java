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
@Table( name = "ticket")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Ticket implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String description;

    @Column(name = "creation_date")
    private Date creationDate;

    private String priority;

    private Float satisfaction;

    @Enumerated(EnumType.STRING)
    private TicketType type;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    private boolean archiver=false;

    @JsonIgnore
    @OneToOne(mappedBy = "ticket",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    ReponseTicket reponseTicket;

    @JsonIgnore
    @ManyToOne
    User user;

    @JsonIgnore
    @ManyToOne
    Product product;

}
