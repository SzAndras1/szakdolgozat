import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvertisingDetailsComponent } from './advertising-details.component';

describe('AdvertisingDetailsComponent', () => {
  let component: AdvertisingDetailsComponent;
  let fixture: ComponentFixture<AdvertisingDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdvertisingDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdvertisingDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
