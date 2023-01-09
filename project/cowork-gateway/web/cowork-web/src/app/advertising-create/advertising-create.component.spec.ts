import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvertisingCreateComponent } from './advertising-create.component';

describe('AdvertisingCreateComponentComponent', () => {
  let component: AdvertisingCreateComponent;
  let fixture: ComponentFixture<AdvertisingCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdvertisingCreateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdvertisingCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
