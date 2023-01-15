import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvertisingListAdsComponent } from './advertising-list-ads.component';

describe('AdvertisingListAdsComponent', () => {
  let component: AdvertisingListAdsComponent;
  let fixture: ComponentFixture<AdvertisingListAdsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdvertisingListAdsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdvertisingListAdsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
