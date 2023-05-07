package tn.esprit.pidev.Services;


import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.Entities.*;
import tn.esprit.pidev.Repositories.ProductRepository;
import tn.esprit.pidev.Repositories.ReponseTicketRepository;
import tn.esprit.pidev.Repositories.TicketRepository;
import tn.esprit.pidev.Repositories.UserRepository;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class TicketService implements  ITicketService {
    TicketRepository ticketRepository;
    UserRepository userRepository;
    ProductRepository productRepository;

    ReponseTicketRepository reponseTicketRepository;
    @Override
    public List<Ticket> retrieveAllTicket() {
        return ticketRepository.findAll();

    }
    @Override
    public List<Ticket> retrieveAllTicketsatus() {
        return ticketRepository.findByStatus(TicketStatus.ACCEPTED);

    }

    private List<String> fetchBadWords() {
        List<String> badWords = new ArrayList<>();
        try {
            URL url = new URL("https://docs.google.com/spreadsheets/d/1hIEi2YG3ydav1E06Bzf2mQbGZ12kh2fe4ISgLg_UBuM/export?format=csv");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length > 0) {
                    badWords.add(values[0]);
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return badWords;
    }
    public String convertEmoticonsToEmoji(String text) {
        Map<String, String> emoticonMap = new HashMap<>();
        emoticonMap.put(":)", "\uD83D\uDE42");
        emoticonMap.put(":(", "\uD83D\uDE41");
        emoticonMap.put(":D", "\uD83D\uDE00");
        emoticonMap.put(":P", "\uD83D\uDE1B");
        emoticonMap.put("<3", "\uD83D\uDC97");

        for (Map.Entry<String, String> entry : emoticonMap.entrySet()) {
            String pattern = Pattern.quote(entry.getKey()); // escape any special characters
            text = text.replaceAll(pattern, entry.getValue());
        }

        return text;
    }

    @Override
    public Ticket addTicket(Ticket t,Long idUser,Integer idProduct) {
        User user= userRepository.findById(idUser).orElse(null);
        Product product = productRepository.findById(idProduct).orElse(null);
        t.setUser(user);
        t.setProduct(product);
        quality(product);
        String descWithEmoji=convertEmoticonsToEmoji(t.getDescription());
        t.setDescription(descWithEmoji);

        List<String> badWords=fetchBadWords();
        boolean containsBadWord=false;
        for (String badWord:badWords){
            if(t.getDescription().toLowerCase().contains(badWord.toLowerCase())){
                containsBadWord=true;
                break;
            }
        }

        if (containsBadWord==true)
            return null;
        else
        return ticketRepository.save(t);
    }

    @Override
    public Ticket updateTicket(Ticket t) {
        ticketRepository.save(t);
        return t;
    }

    @Override
    public Ticket retrieveTicket(Integer id) {
        return ticketRepository.findById(id).get();
    }

    @Override
    public void deleteTicket(Integer id) {
        ticketRepository.deleteById(id);
    }


    public void quality(Product product) {
        Integer count = ticketRepository.countByProduct(product);

        if (count > 0)
        {       if (count == 1)
                product.setEtoile(4);
            else if (count == 3)
                product.setEtoile(3);
            else if (count == 5)
                product.setEtoile(2);
            else if (count >= 8)
                product.setEtoile(1);

            productRepository.save(product);
        }

    }

   @Scheduled(cron = "*/20 * * * * *")
   public void ArchiverTicketTraiter() {

        List<Ticket> Tickets = ticketRepository.findByStatus(TicketStatus.ACCEPTED);

       for(Ticket ticket : Tickets)
       {
           ticket.setArchiver(true);
           ticketRepository.save(ticket);
          System.out.println(ticket.getId());
       }
        System.out.println("Terminer");
   }
}
