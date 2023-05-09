import { TestBed } from '@angular/core/testing';

import { TicketdtoService } from './ticketdto.service';

describe('TicketdtoService', () => {
  let service: TicketdtoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TicketdtoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
