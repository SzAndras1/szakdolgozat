import {Component, OnInit} from '@angular/core';
import {AdvertisingDto, AdvertisingService} from "../generated";

@Component({
  selector: 'app-history-page',
  templateUrl: './history-page.component.html',
  styleUrls: ['./history-page.component.scss']
})
export class HistoryPageComponent implements OnInit {
  advertisingList: AdvertisingDto[] = [];
  constructor(private advertisingService: AdvertisingService) {
  }

  ngOnInit(): void {
    this.advertisingService.getHistory().subscribe((getAds: AdvertisingDto[]) => {
      this.advertisingList = getAds.reverse();
    });
  }

}
