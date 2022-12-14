import { Component, OnInit } from '@angular/core';
import {AdvertisingDto, AdvertisingService} from "../generated";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";

@Component({
  selector: 'app-advertising-details',
  templateUrl: './advertising-details.component.html',
  styleUrls: ['./advertising-details.component.scss']
})
export class AdvertisingDetailsComponent implements OnInit {
  edit: boolean = false;
  //@Input() advertisingDto?: AdvertisingDto
  advertisingDto: AdvertisingDto

  constructor(private route: ActivatedRoute,
              private advertisingService: AdvertisingService,
              private location: Location) {
  }

  getAd(): void {
    const adId = Number(this.route.snapshot.paramMap.get('id'));
    this.advertisingService.getAd(adId)
      .subscribe(ad => this.advertisingDto = ad);
    console.log(adId)
    console.log(this.advertisingDto)
  }

  goBack(): void {
    this.location.back();
  }

  ngOnInit() {
    this.getAd();
  }
}
