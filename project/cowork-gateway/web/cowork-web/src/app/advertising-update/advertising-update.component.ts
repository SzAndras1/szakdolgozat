import {Component, Input, OnInit} from '@angular/core';
import {Location} from "@angular/common";
import {Router} from "@angular/router";
import {AdvertisingDto, AdvertisingService} from "../generated";

@Component({
  selector: 'app-advertising-update',
  templateUrl: './advertising-update.component.html',
  styleUrls: ['./advertising-update.component.scss']
})
export class AdvertisingUpdateComponent implements OnInit{
  //@Input() ad?: AdvertisingDto
  advertisingDto: AdvertisingDto

  constructor(private router: Router,
              private advertisingService: AdvertisingService,
              private location: Location) {
  }
  public updateAd() {
    console.log(this.advertisingDto);
    this.advertisingService.updateAdvertising(this.advertisingDto)
      .subscribe((data: any) => {
        console.log(data);
      });
    this.router.navigate(['advertising']);
  }

  goBack(): void {
    this.location.back();
  }
  ngOnInit() {
    this.advertisingDto = {address: "", email: "", priority: 0, text: "", userId: 0}
  }
}
