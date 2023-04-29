import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { EventService } from '../event.service';
import { Event } from '../event.model';

@Component({
  selector: 'app-add-event',
  templateUrl: './add-event.component.html',
  styleUrls: ['./add-event.component.css'],
})
export class AddEventComponent implements OnInit {
  @ViewChild('updateForm') eventForm!: NgForm;

  event = {
    titre: '',
    description: '',
    date: '',
    UserID: 1,
    rate: 0,
    nbPlaces: 1,
    prix: 0,
    image: '',
    location: '',
    type: 'En_ligne',
  };

  constructor(private eventService: EventService) {}

  ngOnInit(): void {
    var type = document.getElementById('type') as HTMLInputElement;

    var location = document.getElementById('location') as HTMLInputElement;
    var url = document.getElementById('url') as HTMLInputElement;
    var lbl_location = document.getElementById(
      'lbl_location'
    ) as HTMLInputElement;

    var lbl_url = document.getElementById('lbl_url') as HTMLInputElement;

    location.hidden = true;
    url.hidden = false;

    lbl_location.hidden = true;
    lbl_url.hidden = false;
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

  createEvent() {
    if (this.eventForm.valid) {
      console.log(this.eventForm);
    }

    const newEvent: Event = {
      id: 0, // the id will be assigned by the backend
      titre: this.event.titre,
      description: this.event.description,
      date: new Date(Date.parse(this.event.date)),
      UserID: 1,
      rate: 2,
      nbPlaces: this.event.nbPlaces,
      prix: this.event.prix,
      image: 'test.png',
      localisation: this.event.location,
      type: this.event.type,
    };
    console.log(newEvent);
    this.eventService.createEvent(newEvent).subscribe(() => {});
  }
}
