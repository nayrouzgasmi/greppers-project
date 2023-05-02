package com.example.taylor.Services;

import com.example.taylor.entities.Client;
import com.example.taylor.entities.Codepromo;
import com.example.taylor.entities.Panier;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface IPanierService {

    Panier addPanier(Panier p, Long idproduit, Long idclient);

    void deletePanier(Long idLignePanier);

    Panier updatepanier(Panier lp);

    List<Panier> GetAllpanier();
    public Panier getOrCreatePanierForClient(HttpSession session, Client client);
    public List<Panier> getPanierByClientId(Long  idclient);
   // public boolean isCodePromoValid(Codepromo codePromo);
    public Panier applyCodePromo(Panier panier, String code);
    public boolean isCodePromoValid(Codepromo codePromo);

}
