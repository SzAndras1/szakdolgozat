import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvertisingUpdateComponent } from './advertising-update.component';

describe('AdvertisingUpdateComponent', () => {
  let component: AdvertisingUpdateComponent;
  let fixture: ComponentFixture<AdvertisingUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdvertisingUpdateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdvertisingUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
