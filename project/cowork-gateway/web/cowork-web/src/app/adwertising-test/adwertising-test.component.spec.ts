import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdwertisingTestComponent } from './adwertising-test.component';

describe('AdwertisingTestComponent', () => {
  let component: AdwertisingTestComponent;
  let fixture: ComponentFixture<AdwertisingTestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdwertisingTestComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdwertisingTestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
