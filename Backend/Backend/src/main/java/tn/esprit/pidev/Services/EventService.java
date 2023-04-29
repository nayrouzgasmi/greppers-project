package tn.esprit.pidev.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pidev.Entities.Event;
import tn.esprit.pidev.Entities.User;
import tn.esprit.pidev.Repositories.EventRepository;
import tn.esprit.pidev.Repositories.UserRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    public Event ajouterEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getEvents()
    {
        return eventRepository.findAll();
    }

    public boolean supprimerEvent(Event event)
    {
        if(eventRepository.findById((int)event.getID()).isPresent())
        {
            eventRepository.delete(event);
            return true;
        }else
        {
            return false;
        }

    }

    public Event modifierEvent(Event event)
    {
        if(eventRepository.findById((int)event.getID()).isPresent())
        {
            return eventRepository.save(event);
        }else
        {
            return ajouterEvent(event);
        }
    }

    public Optional<Event> searchEvent(int eventId)
    {
        return Optional.ofNullable(eventRepository.findById(eventId).get());
    }

    public boolean participateEvent(int eventId,long user_id)
    {
        Optional<Event> e = eventRepository.findById(eventId);
        if(e.isPresent())
        {
            if(e.get().getNbPlaces()<e.get().getUsers().size())
            {
                Optional<User> u = userRepository.findById(user_id);
                if(u.isPresent())
                {
                    e.get().getUsers().add(u.get());
                    eventRepository.save(e.get());
                    userRepository.save(u.get());
                    return true;

                }else
                {
                    return false;
                }
            }else {
                return false;
            }

        }else
        {
            return false;
        }
    }

    public boolean revokeParticipateEvent(int eventId,long user_id)
    {
        Optional<Event> e = eventRepository.findById(eventId);
        if(e.isPresent())
        {
            Optional<User> u = userRepository.findById(user_id);
            if(u.isPresent())
            {
                e.get().getUsers().remove(u.get());
                eventRepository.save(e.get());
                userRepository.save(u.get());
                return true;

            }else
            {
                return false;
            }
        }else
        {
            return false;
        }
    }

// Fonctionnalités avancées :
    public float revenueEvent(int eventId)
    {
        Optional<Event> e = eventRepository.findById(eventId);
        if(e.isPresent())
        {
            return e.get().getPrix()*e.get().getUsers().size();
        }else
        {
            return 0;
        }

    }

    public int nombreDePlacesDisponible(int event_id)
    {
        Optional<Event> e = eventRepository.findById(event_id);
        if(e.isPresent())
        {
            if(e.get().getNbPlaces()-e.get().getUsers().size()<0)
            {
                return 0;
            }else {
                return e.get().getNbPlaces()-e.get().getUsers().size();
            }
        }else
        {
            return 0;
        }

    }

    public int numberOfParticipantsEvent(int event_id)
    {
        Optional<Event> e = eventRepository.findById(event_id);
        if(e.isPresent())
        {
            return e.get().getUsers().size();
        }else
        {
            return 0;
        }
    }

    public int numberOfParticipantsEvents(int user_id)
    {
        List<Event> list = eventRepository.findAll();

        int participants = 0;

        for (Event event : list) {
            if(event.getuserid()==user_id)
            {
                participants+=event.getUsers().size();
            }

        }
        return participants;
    }

    public float revenueEvents(int user_id)
    {
        List<Event> list = eventRepository.findAll();
        float sum = 0;
        for (Event event : list) {
            if(event.getuserid()==user_id)
            {
                sum+=(event.getPrix()*event.getUsers().size());
            }
        }

        return sum;
    }


}
