import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvertisingRemoveComponent } from './advertising-remove.component';

describe('AdvertisingRemoveComponent', () => {
  let component: AdvertisingRemoveComponent;
  let fixture: ComponentFixture<AdvertisingRemoveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdvertisingRemoveComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdvertisingRemoveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
