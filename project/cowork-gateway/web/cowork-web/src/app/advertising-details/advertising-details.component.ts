import {Component, OnInit} from '@angular/core';
import {AdvertisingDto, AdvertisingService} from "../generated";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {FormBuilder, Validators} from "@angular/forms";

@Component({
  selector: 'app-advertising-details',
  templateUrl: './advertising-details.component.html',
  styleUrls: ['./advertising-details.component.scss']
})
export class AdvertisingDetailsComponent implements OnInit {
  advertisingDto: AdvertisingDto
  editMode: boolean = false
  adId: number = Number(this.route.snapshot.paramMap.get('id'));

  profileForm = this.fb.group({
    userId: [0, [Validators.required, Validators.pattern('^[0-9]*$')]],
    email: ['', [Validators.required, Validators.email]],
    text: ['', [Validators.required, Validators.maxLength(1000)]],
    address: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(100)]],
    priority: [0, [Validators.required, Validators.pattern('^[0-9]*$')]],
    isActive: [true, []]
  })

  constructor(private route: ActivatedRoute,
              private advertisingService: AdvertisingService,
              private location: Location,
              private fb: FormBuilder) {
  }

  getAd(): void {
    this.advertisingService.getAd(this.adId)
      .subscribe(ad => {
        this.advertisingDto = ad;
        this.profileForm.patchValue({
          userId: ad.userId,
          email: ad.email,
          text: ad.text,
          address: ad.address,
          priority: ad.priority,
          isActive: ad.isActive
        });
      });
    console.log(this.advertisingDto)
  }

  goBack(): void {
    this.location.back();
  }

  enterModifyMode(): void {
    this.editMode = true
  }

  saveAdModify(): void {
    this.advertisingService.updateAdvertising(this.profileForm.value as unknown as AdvertisingDto)
      .subscribe((data: any) => {
        console.log(data);
      });
    this.editMode = false
  }

  ngOnInit(): void {
    this.getAd();
  }
}
