import {Component, OnInit} from '@angular/core';
import {AdvertisingDto, AdvertisingService} from "../generated";
import {Router} from "@angular/router";
import {Location} from "@angular/common";

@Component({
  selector: 'app-advertising-create.component',
  templateUrl: './advertising-create.component.html',
  styleUrls: ['./advertising-create.component.scss']
})
export class AdvertisingCreateComponent implements OnInit {
  public advertisingDto: AdvertisingDto;

  constructor(
    private advertisingService: AdvertisingService,
    private router: Router,
    private location: Location) {
  }

  public createAd() {
    console.log(this.advertisingDto);
    this.advertisingService.createAd(this.advertisingDto)
      .subscribe((data: AdvertisingDto) => {
        console.log(data);
      });
    this.router.navigate(['advertising']);
  }

  goBack(): void {
    this.location.back();
  }

  ngOnInit() {
    this.advertisingDto = {address: "", email: "", priority: 0, text: "", userId: 0};
  }
}
