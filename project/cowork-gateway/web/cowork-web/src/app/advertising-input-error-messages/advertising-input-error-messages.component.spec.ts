import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvertisingInputErrorMessagesComponent } from './advertising-input-error-messages.component';

describe('AdvertisingInputErrorMessagesComponent', () => {
  let component: AdvertisingInputErrorMessagesComponent;
  let fixture: ComponentFixture<AdvertisingInputErrorMessagesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdvertisingInputErrorMessagesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdvertisingInputErrorMessagesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
