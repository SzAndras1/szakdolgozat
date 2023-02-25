import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvertisingListByUseridComponent } from './advertising-list-by-userid.component';

describe('AdvertisingListByUseridComponent', () => {
  let component: AdvertisingListByUseridComponent;
  let fixture: ComponentFixture<AdvertisingListByUseridComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdvertisingListByUseridComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdvertisingListByUseridComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
