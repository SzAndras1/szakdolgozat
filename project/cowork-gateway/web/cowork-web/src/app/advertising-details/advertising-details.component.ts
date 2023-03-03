import { Component, OnInit } from '@angular/core';
import {AdvertisingDto, AdvertisingService, RatingDto, RatingService} from "../generated";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {Observable} from "rxjs";

@Component({
  selector: 'app-advertising-details',
  templateUrl: './advertising-details.component.html',
  styleUrls: ['./advertising-details.component.scss']
})
export class AdvertisingDetailsComponent implements OnInit {
  edit: boolean = false;
  advertisingDto: AdvertisingDto
  editMode: boolean = false
  ratings: Array<RatingDto>;
  ratingDto: RatingDto
  overallRating: number;
  items = ['Item 1', 'Item 2', 'Item 3', 'Item 4', 'Item 5'];
  expandedIndex = 0;

  constructor(private route: ActivatedRoute,
              private advertisingService: AdvertisingService,
              private ratingService: RatingService,
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

  enterModifyMode(): void{
    this.editMode = true
  }

  saveAdModify(): void{
    this.advertisingService.updateAdvertising(this.advertisingDto)
      .subscribe((data: any) => {
      console.log(data);
    });
    this.editMode = false
  }

  getRatings(): void{
    const userId: number = Number(this.route.snapshot.paramMap.get('userId'));
    this.ratingService.getAdRatings(userId).subscribe(ratings => this.ratings = ratings)
  }

/*  createRating(): void{
    this.ratingService.createRating(this.ratingDto)
      .subscribe((data: any) => {
        console.log(data);
      });
  }*/

  getOverallRating(): void{
    this.ratingService.getOverallRating(1).subscribe(val => this.overallRating = val);
  }

 /* modifyRating(): void{
    this.ratingService.updateRating()
  }*/


  ngOnInit() {
    this.getAd();
    this.getOverallRating();
    this.ratingDto = {ratingValue: 0, userId: this.advertisingDto.userId}
    this.getRatings();
  }

}
