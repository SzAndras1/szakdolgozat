import {Component, OnInit} from '@angular/core';
import {AdvertisingDto, AdvertisingService} from "../generated";
import {Router} from "@angular/router";
import {Location} from "@angular/common";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-advertising-create.component',
  templateUrl: './advertising-create.component.html',
  styleUrls: ['./advertising-create.component.scss']
})
export class AdvertisingCreateComponent implements OnInit {
  profileForm!: FormGroup;

  constructor(
    private advertisingService: AdvertisingService,
    private router: Router,
    private location: Location,
    private fb: FormBuilder) {
  }

  public createAd(): void{
    this.advertisingService.createAd(this.profileForm.value as unknown as AdvertisingDto)
      .subscribe((data: AdvertisingDto) => {
        console.log(data);
      });
    this.router.navigate(['advertising']);
  }

  goBack(): void {
    this.location.back();
  }

  ngOnInit(): void {
    this.profileForm = this.fb.group({
      userId: ['', [Validators.required, Validators.pattern('^[0-9]*$')]],
      email: ['', [Validators.required, Validators.email]],
      text: ['', [Validators.required, Validators.maxLength(1000)]],
      address: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(100)]],
      priority: ['', [Validators.required, Validators.pattern('^[0-9]*$')]],
    });
    this.profileForm.addControl('category',this.toppingsControl);
  }

  toppingsControl = new FormControl([] as string[]);
  toppingList: string[] = ['Video editor', 'Programmer', 'Security', 'Visual designer', 'Consultant'];

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
}
