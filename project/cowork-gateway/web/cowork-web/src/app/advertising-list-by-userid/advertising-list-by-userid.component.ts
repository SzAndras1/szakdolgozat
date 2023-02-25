import { Component } from '@angular/core';
import {AdvertisingDto, AdvertisingService} from "../generated";
import {Location} from "@angular/common";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-advertising-list-by-userid',
  templateUrl: './advertising-list-by-userid.component.html',
  styleUrls: ['./advertising-list-by-userid.component.scss']
})
export class AdvertisingListByUseridComponent {
  constructor(private advertisingService: AdvertisingService,
              private location: Location,
              private router: Router,
              private route: ActivatedRoute,) {
  }

  displayedColumns: string[] = ['id', 'text', 'email', 'detail', 'activation' ,'delete'];
  advertisings: Array<AdvertisingDto>;

  getAds(): void {
    const userId: number = Number(this.route.snapshot.paramMap.get('userId'));
    console.log(userId)
    this.advertisingService.getUserAds(userId).subscribe(ad => this.advertisings = ad);
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

  goBack(): void {
    this.location.back();
  }
  ngOnInit() {
    this.getAds();
  }
}
