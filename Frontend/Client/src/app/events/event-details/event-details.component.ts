import { Component, OnInit } from '@angular/core';
import { EventService } from '../event.service';
import {Event} from "../event.model";
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-event-details',
  templateUrl: './event-details.component.html',
  styleUrls: ['./event-details.component.sass']
})
export class EventDetailsComponent implements OnInit{

  event : Event | any;

  userid : number | any;

  eventId: any;

  isUserParticipant: boolean | any;

  numberOfParticipants: number | any;

  isEventFull: boolean | any;

  constructor( private eventService: EventService, private route: ActivatedRoute,
    private router: Router) {

  }


  ngOnInit() {

    this.userid = window.sessionStorage.getItem('id');
    this.eventId = this.route.snapshot.paramMap.get('id');

    this.eventService.getEvent(this.eventId).subscribe((event) => {
      this.event = event;
      this.eventService.getParticipantsNumber(this.event.id).subscribe((res)=> {
        this.numberOfParticipants = res
        if(event.nbPlaces-this.numberOfParticipants>0)
        {
          this.isEventFull = false;
        }else
        {
          this.isEventFull = true;
        }
      })
    });




    this.eventService.isUserParticipant(this.eventId,this.userid).subscribe((res) => {
      this.isUserParticipant = res;
    });


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


  participate()
  {
    this.eventService.participate(this.eventId,this.userid).subscribe((res) => {
      if(res)
      {
        this.router.navigate(['/events']);
      }else
      {
        alert("An error occured while participating in event, please refresh the page and try again")
      }
    });
  }

  skipEvent()
  {
    this.eventService.skipEvent(this.eventId,this.userid).subscribe((res) => {
      if(res)
      {
        this.router.navigate(['/events']);
      }else
      {
        alert("An error occured while participating in event, please refresh the page and try again")

      }
    });
  }



}
