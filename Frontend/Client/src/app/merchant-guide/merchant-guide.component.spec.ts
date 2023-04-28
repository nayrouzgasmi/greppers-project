import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MerchantGuideComponent } from './merchant-guide.component';

describe('MerchantGuideComponent', () => {
  let component: MerchantGuideComponent;
  let fixture: ComponentFixture<MerchantGuideComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MerchantGuideComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MerchantGuideComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
