import { Component } from '@angular/core';
import {AdvertisingDto, AdvertisingService} from "../generated";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-advertising-favourites',
  templateUrl: './advertising-favorites.component.html',
  styleUrls: ['./advertising-favorites.component.scss']
})
export class AdvertisingFavoritesComponent {
  constructor(private advertisingService: AdvertisingService,
              private route: ActivatedRoute,
              private router: Router,) {
  }

  displayedColumns: string[] = ['id', 'text', 'email', 'detail', 'activation' ,'delete', 'favorite'];
  advertisings: Array<AdvertisingDto>;

  getAds(): void {
    const userId: number = Number(this.route.snapshot.paramMap.get('userId'));
    console.log(userId)
    this.advertisingService.getFavoriteAds().subscribe(ad => this.advertisings = ad);
  }

  detail(id: string) {
    this.router.navigate(['advertising',id]);
  }

  delete(id: number) {
    this.advertisingService.deleteAdvertising(id).subscribe(
      (data: any) => {
        console.log(data);
      }
    );
  }

  setStatus(id: number) {
    this.advertisingService.setAdStatus(id).subscribe(
      (data: any) => {
        console.log(data);
      }
    );
  }

  ngOnInit() {
    this.getAds();
  }

  setFavoriteStatus(id: number) {
    this.advertisingService.setAdFavoriteStatus(id).subscribe(
      (data: any) => {
        console.log(data);
      }
    );
  }
}
