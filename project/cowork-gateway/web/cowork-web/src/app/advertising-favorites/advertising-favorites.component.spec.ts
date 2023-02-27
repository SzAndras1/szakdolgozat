import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvertisingFavoritesComponent } from './advertising-favorites.component';

describe('AdvertisingFavouritesComponent', () => {
  let component: AdvertisingFavoritesComponent;
  let fixture: ComponentFixture<AdvertisingFavoritesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdvertisingFavoritesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdvertisingFavoritesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
