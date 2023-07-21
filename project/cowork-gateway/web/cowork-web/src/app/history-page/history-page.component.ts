import {Component, OnInit} from '@angular/core';
import {AdvertisingDto, AdvertisingService} from "../generated";
import {Router} from "@angular/router";

@Component({
  selector: 'app-history-page',
  templateUrl: './history-page.component.html',
  styleUrls: ['./history-page.component.scss']
})
export class HistoryPageComponent implements OnInit {
  advertisingList: AdvertisingDto[] = [];
  constructor(private advertisingService: AdvertisingService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.advertisingService.getHistory().subscribe((getAds: AdvertisingDto[]) => {
      this.advertisingList = getAds.reverse();
    });
  }

  navigate(adId: number): void {
    this.router.navigate(['advertising', adId]);
  }

}
