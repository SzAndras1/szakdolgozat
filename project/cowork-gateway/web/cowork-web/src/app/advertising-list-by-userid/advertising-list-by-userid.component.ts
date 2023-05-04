import {Component, OnInit} from '@angular/core';
import {AdvertisingDto, AdvertisingService, RatingDto, RatingService} from "../generated";
import {Location} from "@angular/common";
import {ActivatedRoute, Router} from "@angular/router";
import {MatTableDataSource} from '@angular/material/table';
import {FormControl, Validators} from "@angular/forms";
import {ProgressSpinnerMode} from "@angular/material/progress-spinner";
import {PopUpDialogComponent} from "../pop-up-dialog/pop-up-dialog.component";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-advertising-list-by-userid',
  templateUrl: './advertising-list-by-userid.component.html',
  styleUrls: ['./advertising-list-by-userid.component.scss']
})
export class AdvertisingListByUseridComponent implements OnInit {
  constructor(private advertisingService: AdvertisingService,
              private ratingService: RatingService,
              public dialog: MatDialog,
              private location: Location,
              private router: Router,
              private route: ActivatedRoute,) {
  }

  displayedColumns: string[] = ['id', 'text', 'email', 'detail', 'activation', 'delete', 'favorite'];
  userId: number = Number(this.route.snapshot.paramMap.get('userId'));
  advertisings: AdvertisingDto[] = [];
  activeAdvertisings: MatTableDataSource<AdvertisingDto>;
  inactiveAdvertisings: MatTableDataSource<AdvertisingDto>;
  ratingForm = new FormControl(1, [Validators.required, Validators.pattern('^[1-5]$')]);
  ratings: RatingDto[] = [];
  overallRating: number;
  mode: ProgressSpinnerMode = "determinate";
  ratingModifyStatus: boolean[] = [];

  getAds(): void {
    this.advertisingService.getUserAds(this.userId).subscribe(
      (ad: AdvertisingDto[]) => {
        let actives: AdvertisingDto[] = []
        let inactives: AdvertisingDto[] = []
        this.advertisings = ad;
        this.advertisings.forEach((selectedAd: AdvertisingDto) => {
          if (selectedAd.isActive) {
            actives.push(selectedAd);
            console.log('active:', selectedAd)
          } else {
            inactives.push(selectedAd);
            console.log('inactive:', selectedAd)
          }
        });
        this.activeAdvertisings = new MatTableDataSource(actives);
        this.inactiveAdvertisings = new MatTableDataSource(inactives);
      });
  }

  ngOnInit(): void {
    this.getAds();
    this.getOverallRating();
    this.getRatings();
    for (let i = 0; i < this.ratingModifyStatus.length; i++) {
      this.ratingModifyStatus[i] = false;
    }
  }

  detail(id: string) {
    this.router.navigate(['advertising', id]);
  }

  deleteAd(id: number) {
    this.advertisingService.deleteAdvertising(id).subscribe(
      (data: AdvertisingDto) => {
        console.log(data);
        this.getAds();
      }
    );
  }

  setStatus(id: number) {
    this.advertisingService.setAdStatus(id).subscribe(
      (data: AdvertisingDto) => {
        console.log(data);
        this.getAds();
      }
    );
  }

  openDialog(id: number) {
    let dialogRef = this.dialog.open(PopUpDialogComponent, {
      data: false
    })

    dialogRef.afterClosed().subscribe(res => {
      if (res.data === true) {
        console.log(res.data)
        this.deleteAd(id);
      }
    })
  }

  goBack(): void {
    this.location.back();
  }

  setFavoriteStatus(id: number) {
    this.advertisingService.setAdFavoriteStatus(id).subscribe(
      (data: AdvertisingDto) => {
        console.log(data);
        this.getAds();
      }
    );
  }

  getRatings(): void {
    this.ratingService.getAdRatings(this.userId).subscribe(ratings => this.ratings = ratings)
  }

  createRating(): void {
    let ratingDto: RatingDto = {userId: this.userId, ratingValue: 0};
    ratingDto.ratingValue = Number(this.ratingForm.value);
    this.ratingService.createRating(ratingDto)
      .subscribe((data: RatingDto) => {
        console.log(data);
        this.getRatings();
        this.getOverallRating();
      });
    this.ratingModifyStatus.push(false);
  }

  deleteRating(id: number): void {
    this.ratingService.deleteRating(id)
      .subscribe((data: RatingDto) => {
        console.log(data);
        this.getOverallRating();
        this.getRatings();
      });
  }

  enterModifyMode(index: number): void {
    this.ratingForm.patchValue(this.ratings[index].ratingValue);
    this.ratingModifyStatus[index] = !this.ratingModifyStatus[index];
  }

  modifyRating(rating: RatingDto, index: number): void {
    rating.ratingValue = this.ratingForm.value!;
    this.ratingService.updateRating(rating)
      .subscribe(() => {
        this.getOverallRating();
        this.getRatings();
      });
    this.enterModifyMode(index);
  }

  getOverallRating(): void {
    this.ratingService.getOverallRating(this.userId).subscribe(val => this.overallRating = val);
  }
}
