import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvertisingUserAdsComponent } from './advertising-user-ads.component';

describe('AdvertisingUserAdsComponent', () => {
  let component: AdvertisingUserAdsComponent;
  let fixture: ComponentFixture<AdvertisingUserAdsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdvertisingUserAdsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdvertisingUserAdsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
