import { Component } from '@angular/core';
import {AdvertisingService} from "../generated";
import {Location} from "@angular/common";

@Component({
  selector: 'app-advertising-user-ads',
  templateUrl: './advertising-user-ads.component.html',
  styleUrls: ['./advertising-user-ads.component.scss']
})
export class AdvertisingUserAdsComponent {
  constructor(private advertisingService: AdvertisingService,
              private location: Location) {
  }

  getAds(userId: number): void {
    this.advertisingService.getUserAds(userId)
      .subscribe((data: any) => {
        console.log(data);
      });
  }

  goBack(): void {
    this.location.back();
  }
}
