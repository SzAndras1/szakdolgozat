import { Component, OnInit } from '@angular/core';
import {AdvertisingPageResultDto, AdvertisingDto, AdvertisingService} from "../generated";

@Component({
  selector: 'app-advertising-details',
  templateUrl: './advertising-details.component.html',
  styleUrls: ['./advertising-details.component.scss']
})
export class AdvertisingDetailsComponent implements OnInit {
  edit: boolean = false;

  constructor(advertisingService: AdvertisingService) { }

  advertisingDto: AdvertisingDto;

  ngOnInit() {

  }
}
