import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvertisingFormComponent } from './advertising-form.component';

describe('AdvertisingCreateFormComponent', () => {
  let component: AdvertisingFormComponent;
  let fixture: ComponentFixture<AdvertisingFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdvertisingFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdvertisingFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
