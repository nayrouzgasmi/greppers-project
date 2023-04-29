import { Component, OnInit, ViewChild } from '@angular/core';
import { EventService } from '../event.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Event } from '../event.model';

@Component({
  selector: 'app-edit-event',
  templateUrl: './edit-event.component.html',
  styleUrls: ['./edit-event.component.css'],
})
export class EditEventComponent implements OnInit {
  @ViewChild('updateForm') eventForm!: NgForm;

  eventData: any;
  eventId: any;

  constructor(
    private eventService: EventService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.eventId = this.route.snapshot.paramMap.get('id');
    var type = document.getElementById('type') as HTMLInputElement;

    var location = document.getElementById('location') as HTMLInputElement;
    var url = document.getElementById('url') as HTMLInputElement;
    var lbl_location = document.getElementById(
      'lbl_location'
    ) as HTMLInputElement;

    var lbl_url = document.getElementById('lbl_url') as HTMLInputElement;

    this.eventService.getEvent(this.eventId).subscribe((event) => {
      this.eventData = event;
      if (event.type == 'En_ligne') {
        type.value = 'En_ligne';
        this.eventData.location = event.localisation;
        location.hidden = true;
        lbl_location.hidden = true;
        url.hidden = false;
        lbl_url.hidden = false;
      } else {
        type.value = 'Presentiel';
        this.eventData.location = event.localisation;
        lbl_location.hidden = false;
        location.hidden = false;
        url.hidden = true;
        lbl_url.hidden = true;
      }
    });
  }

  editEvent() {
    const updatedEvent: Event = {
      id: this.eventData.id,
      titre: this.eventData.titre,
      description: this.eventData.description,
      date: new Date(Date.parse(this.eventData.date)),
      UserID: 1,
      rate: 2,
      nbPlaces: this.eventData.nbPlaces,
      prix: this.eventData.prix,
      image: 'test.png',
      localisation: this.eventData.location,
      type: this.eventData.type,
    };
    this.eventService.updateEvent(updatedEvent).subscribe(() => {
      this.router.navigate(['/events']);
    });
  }

  handleSelectChange() {
    var type = document.getElementById('type') as HTMLInputElement;

    var location = document.getElementById('location') as HTMLInputElement;
    var url = document.getElementById('url') as HTMLInputElement;

    var lbl_location = document.getElementById(
      'lbl_location'
    ) as HTMLInputElement;

    var lbl_url = document.getElementById('lbl_url') as HTMLInputElement;

    if (type != null && location != null && url != null) {
      if (type.value === 'En_ligne') {
        location.hidden = true;
        lbl_location.hidden = true;
        url.hidden = false;
        lbl_url.hidden = false;
      } else {
        lbl_location.hidden = false;
        location.hidden = false;
        url.hidden = true;
        lbl_url.hidden = true;
      }
    }
  }
}
