package tn.esprit.pidev.Controllers;


import java.util.Optional;

import tn.esprit.pidev.Repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.esprit.pidev.Entities.Event;
import tn.esprit.pidev.Services.EventService;


@CrossOrigin("*")
@RestController
@RequestMapping("/events")
public class EventRestController {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventRepository eventRepository;

    
    @GetMapping(value = "/getEvents",produces = MediaType.APPLICATION_JSON_VALUE)
            public ResponseEntity<Object> getEvents()
    {
        return new ResponseEntity<>(eventService.getEvents(), HttpStatus.OK) ;
    }
    
    @PostMapping("/addEvent")
    public ResponseEntity<Event> ajouterEvent(@RequestBody Event event) {
        Event nouvelEvent = eventService.ajouterEvent(event);
        return ResponseEntity.ok(nouvelEvent);
    }
    
    @PutMapping("/updateEvent")
    public ResponseEntity<Event> modifierEvent(@RequestBody Event event)
    {
        Event updatedEvent = eventService.modifierEvent(event);
        return ResponseEntity.ok(updatedEvent);
    }
    
    @DeleteMapping("/deleteEvent")
    public ResponseEntity<String> supprimerEvent(@RequestBody Event event)
    {
        boolean result = eventService.supprimerEvent(event);
        if(result)
            return ResponseEntity.ok("{}");
        else
            return ResponseEntity.ok("{}");
    }
    
    @GetMapping("/searchEvent/{id}")
    public ResponseEntity<Event> searchEvent(@PathVariable int id)
    {
        Optional<Event> updatedEvent = eventService.searchEvent(id);
        if(updatedEvent.isPresent())
            return ResponseEntity.ok(updatedEvent.get());
        else
            return ResponseEntity.ok(null);
    }
    
    @GetMapping("/searchEventByName")
    public ResponseEntity<Event> searchEventByName(@RequestParam String eventName)
    {
        Optional<Event> event = eventRepository.findByTitre(eventName);
        if(event.isPresent())
            return ResponseEntity.ok(event.get());
        else
            return ResponseEntity.ok(null);
    }
    
    @PostMapping("/participateEvent/{eventId}/{user_id}")
    public ResponseEntity<String> participerEvent(@PathVariable int eventId,@PathVariable int user_id)
    {
        Optional<Event> search = eventService.searchEvent(eventId);
        if(search.isPresent())
        {
            return ResponseEntity.ok("Event Found and user participate : "+eventService.participateEvent(eventId, user_id));
        }else
        {
            return ResponseEntity.ok("Event Not Found");
        }
    }
    
    @PostMapping("/revokeParticipateEvent/{eventId}/{user_id}")
    public ResponseEntity<String> revokeParticipateEvent(@PathVariable int eventId,@PathVariable int user_id)
    {
        Optional<Event> search = eventService.searchEvent(eventId);
        if(search.isPresent())
        {
            return ResponseEntity.ok("Event Found and user participate : "+eventService.revokeParticipateEvent(eventId, user_id));
        }else
        {
            return ResponseEntity.ok("Event Not Found");
        }
    }
    
    @GetMapping("/revenueEvent/{eventId}")
    public ResponseEntity<String> revenueEvent(@PathVariable int eventId)
    {
        Optional<Event> search = eventService.searchEvent(eventId);
        if(search.isPresent())
        {
            return ResponseEntity.ok(eventService.revenueEvent(eventId)+"");
        }else
        {
            return ResponseEntity.ok("Event Not Found");
        }
    }
    
    @GetMapping("/revenueEvents/{user_id}")
    public ResponseEntity<String> revenueEvents(@PathVariable int user_id)
    {


        return ResponseEntity.ok(eventService.revenueEvents(user_id)+"");

    }
    
    @GetMapping("/numberOfParticipantsEvent/{event_id}")
    public ResponseEntity<String> numberOfParticipantsEvent(@PathVariable int event_id)
    {


        return ResponseEntity.ok(eventService.numberOfParticipantsEvent(event_id)+"");

    }
    
    @GetMapping("/numberOfParticipantsEvents/{user_id}")
    public ResponseEntity<String> numberOfParticipantsEvents(@PathVariable int user_id)
    {


        return ResponseEntity.ok(eventService.numberOfParticipantsEvents(user_id)+"");

    }
    
    @GetMapping("/nombreDePlacesDisponible/{event_id}")
    public ResponseEntity<Integer> nombreDePlacesDisponible(@PathVariable int event_id)
    {
        return ResponseEntity.ok(eventService.nombreDePlacesDisponible(event_id));
    }

}
