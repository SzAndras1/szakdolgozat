import {Component} from '@angular/core';
import {AdvertisingDto, AdvertisingService} from "../generated";
import {ActivatedRoute, Router} from "@angular/router";
import {FormControl} from "@angular/forms";

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
  toppingsControl = new FormControl([] as string[]);
  toppingList: string[] = ['Extra cheese', 'Mushroom', 'Onion', 'Pepperoni', 'Sausage', 'Tomato'];

  onToppingRemoved(topping: string) {
    const toppings: string[] = this.toppingsControl.value as string[];
    this.removeFirst(toppings, topping);
    this.toppingsControl.setValue(toppings);
  }

  private removeFirst<T>(array: T[], toRemove: T): void {
    const index = array.indexOf(toRemove);
    if (index !== -1) {
      array.splice(index, 1);
    }
  }

  displayedColumns: string[] = ['id', 'text', 'email', 'detail', 'activation' ,'delete', 'favorite'];
  advertisings: AdvertisingDto[]= [];

  getAds(): void {
    this.advertisingService.getFavoriteAds().subscribe(ad => this.advertisings = ad);
  }

  detail(id: string) {
    this.router.navigate(['advertising',id]);
  }

  delete(id: number) {
    this.advertisingService.deleteAdvertising(id).subscribe(
      (data: AdvertisingDto) => {
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
