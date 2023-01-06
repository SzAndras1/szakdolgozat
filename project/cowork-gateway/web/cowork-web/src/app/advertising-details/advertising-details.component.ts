import { Component, OnInit } from '@angular/core';
import {AdvertisingPageResultDto, AdvertisingDto, AdvertisingService} from "../generated";
import {Router} from "@angular/router";

@Component({
  selector: 'app-advertising-details',
  templateUrl: './advertising-details.component.html',
  styleUrls: ['./advertising-details.component.scss']
})
export class AdvertisingDetailsComponent implements OnInit {
  edit: boolean = false;


  constructor(
    private advertisingService: AdvertisingService,
    private router: Router) { }

  public advertisingDto: AdvertisingDto;

  public createAd() {
    console.log(this.advertisingDto);
    this.advertisingService.createAd(this.advertisingDto)
    .subscribe((data: any) => {
      console.log(data);
    });
    //this.router.navigate(['advertising']);
  }

  ngOnInit() {
    this.advertisingDto = {address: "", email: "", priority: 0, text: "", userId: 0};
  }
}
