import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvertisingMainComponent } from './advertising-main.component';

describe('AdwertisingTestComponent', () => {
  let component: AdvertisingMainComponent;
  let fixture: ComponentFixture<AdvertisingMainComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdvertisingMainComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdvertisingMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
