import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateReponseComponent } from './update-reponse.component';

describe('UpdateReponseComponent', () => {
  let component: UpdateReponseComponent;
  let fixture: ComponentFixture<UpdateReponseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateReponseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateReponseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
