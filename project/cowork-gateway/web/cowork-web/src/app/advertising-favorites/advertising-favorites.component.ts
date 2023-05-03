import {Component, OnInit} from '@angular/core';
import {AdvertisingDto, AdvertisingService} from "../generated";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-advertising-favourites',
  templateUrl: './advertising-favorites.component.html',
  styleUrls: ['./advertising-favorites.component.scss']
})
export class AdvertisingFavoritesComponent implements OnInit {
  constructor(private advertisingService: AdvertisingService,
              private route: ActivatedRoute,
              private router: Router,) {
  }

  displayedColumns: string[] = ['id', 'text', 'email', 'detail', 'activation' ,'delete', 'favorite'];
  advertisings: AdvertisingDto[]= [];

  getAds(): void {
    this.advertisingService.getFavoriteAds().subscribe(ad => this.advertisings = ad);
  }

  detail(id: string): void {
    this.router.navigate(['advertising',id]);
  }

  delete(id: number): void {
    this.advertisingService.deleteAdvertising(id).subscribe(
      (data: AdvertisingDto) => {
        console.log(data);
      }
    );
  }

  setStatus(id: number): void {
    this.advertisingService.setAdStatus(id).subscribe(
      (data: any) => {
        console.log(data);
      }
    );
  }

  ngOnInit(): void {
    this.getAds();
  }

  setFavoriteStatus(id: number): void {
    this.advertisingService.setAdFavoriteStatus(id).subscribe(
      (data: any) => {
        console.log(data);
      }
    );
  }
}
