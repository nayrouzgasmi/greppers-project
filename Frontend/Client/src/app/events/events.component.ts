import { Component, OnInit } from '@angular/core';
import { EventService } from './event.service';
import {Event} from "./event.model";
import { Router } from '@angular/router';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.sass']
})
export class EventsComponent implements OnInit {

  events: Event[] | any;

  nb:string | any;

  numParticipantsMap = new Map<number, number>();

  isUserParticipantsMap = new Map<number,boolean>();

  userid : number | any;



  constructor(private eventService: EventService,private router: Router){

  }
  ngOnInit(): void {
    this.userid = window.sessionStorage.getItem('id');

    this.getEvents();
  }


  getEvents() {
    this.eventService.getEvents().subscribe(events => {
      this.events = events

      this.events.forEach((element: Event) => {
        this.eventService.getPlacesLeft(element.id).subscribe(numParticipants => {
          // store the number of participants in the numParticipantsMap object, using the event ID as the key
          this.numParticipantsMap.set(element.id, numParticipants);
        });

        this.eventService.isUserParticipant(element.id,this.userid).subscribe((res) => {
          this.isUserParticipantsMap.set(element.id,res)
        });

      });
    } );



  }


  getNombreParticipants(id:number)
  {
    this.eventService.getParticipantsNumber(id).subscribe(res => {
      this.nb = res;
    })
  }

  checkEventType(type:string)
  {
    if(type==="En_ligne")
    {
      return true;
    }else
    {
      return false;
    }
  }


}
