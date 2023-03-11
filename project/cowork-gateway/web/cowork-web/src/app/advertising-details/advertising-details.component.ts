import { Component, OnInit } from '@angular/core';
import {AdvertisingDto, AdvertisingService, RatingDto, RatingService} from "../generated";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {FormControl, Validators} from "@angular/forms";
import {ProgressSpinnerMode} from "@angular/material/progress-spinner";

@Component({
  selector: 'app-advertising-details',
  templateUrl: './advertising-details.component.html',
  styleUrls: ['./advertising-details.component.scss']
})
export class AdvertisingDetailsComponent implements OnInit {
  advertisingDto: AdvertisingDto
  ratings: RatingDto[] = [];
  overallRating: number;
  ratingForm = new FormControl('', [Validators.required, Validators.pattern('^[1-5]$')]);
  editMode: boolean = false
  adId: number = Number(this.route.snapshot.paramMap.get('id'));
  mode: ProgressSpinnerMode = "determinate";

  constructor(private route: ActivatedRoute,
              private advertisingService: AdvertisingService,
              private ratingService: RatingService,
              private location: Location,) {
  }

  getAd(): void {
    this.advertisingService.getAd(this.adId)
      .subscribe(ad => this.advertisingDto = ad);
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
    const userId: number = 1;
    this.ratingService.getAdRatings(userId).subscribe(ratings => this.ratings = ratings)
  }

  createRating(): void{
    let ratingDto: RatingDto = {userId: 1, ratingValue: 0};
    ratingDto.ratingValue = Number(this.ratingForm.value);
    this.ratingService.createRating(ratingDto)
      .subscribe((data: RatingDto) => {
        console.log(data);
        this.getRatings();
        this.getOverallRating();
      });
  }

  deleteRating(id: number): void{
    this.ratingService.deleteRating(id)
      .subscribe((data: RatingDto) => {
        console.log(data);
        this.getOverallRating();
        this.getRatings();
      });
  }

  getOverallRating(): void{
    this.ratingService.getOverallRating(1).subscribe(val => this.overallRating = val);
  }

  ngOnInit() {
    this.getAd();
    this.getOverallRating();
    this.getRatings();
  }

  getRatingErrorMessage(): string {
    if (this.ratingForm.hasError('required')) {
      return 'You must enter a value';
    }
    if (this.ratingForm.hasError('pattern')) {
      return 'Must be between 1 and 5';
    }

    return this.ratingForm.hasError('required') ? 'Not a valid value' : '';
  }
}
