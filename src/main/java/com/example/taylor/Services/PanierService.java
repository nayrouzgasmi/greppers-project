package com.example.taylor.Services;


import com.example.taylor.Repository.ClientRepository;
import com.example.taylor.Repository.CodepromoRepository;
import com.example.taylor.Repository.PanierRepository;
import com.example.taylor.Repository.ProduitRepository;
import com.example.taylor.entities.Client;
import com.example.taylor.entities.Codepromo;
import com.example.taylor.entities.Panier;
import com.example.taylor.entities.Produit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;



@Service
@Slf4j
public class PanierService implements IPanierService{
    @Autowired
    PanierRepository panierRepository;
    @Autowired
    ProduitRepository produitRepository ;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CodepromoRepository codePromoRepository;

    @Override
    public Panier addPanier(Panier p, Long idproduit , Long idclient) {
        Produit produit = produitRepository.findById(idproduit).orElse(null);
        Client client = clientRepository.findById(idclient).orElse(null);
        p.setClient(client);
        p.setProduit(produit);
        return panierRepository.save(p);
    }
    @Override
    public void deletePanier(Long idLignePanier) {
        panierRepository.deleteById(idLignePanier);
    }
    @Override
    public Panier updatepanier(Panier lp)  {
        return panierRepository.save(lp);
    }
    @Override
    public List<Panier> GetAllpanier() {
        return panierRepository.findAll();
    }

    @Override
    public Panier getOrCreatePanierForClient(HttpSession session, Client client) {
        Panier panier = (Panier) session.getAttribute("panier");
        if (panier != null) {
            return panier;
        }
        panier = panierRepository.findByClient(client);
        if (panier == null) {
            panier = new Panier();
            panier.setClient(client);
            panier = panierRepository.save(panier);
        }
        session.setAttribute("panier", panier);
        return panier;
    }
    @Override
    public List<Panier> getPanierByClientId(Long  idclient) {
        return panierRepository.findByClient_Idclient( idclient);
    }


    @Override
    public Panier applyCodePromo(Panier panier, String code)
    {
        Codepromo codePromo = codePromoRepository.findByCode(code);
        //&& isCodePromoValid(codePromo)
        if (codePromo != null  ){
            double  discountPercentage = codePromo.getDiscountPercentage();
            double discountAmount = panier.getTotalprice() *  discountPercentage / 100.0;
            panier.setTotalprice(panier.getTotalprice() - discountAmount);
            panier.setCodePromo(codePromo);
            panierRepository.save(panier);

        }
        return panier;
    }

    @Override
    public boolean isCodePromoValid(Codepromo codePromo) {
        return false;
    }


}