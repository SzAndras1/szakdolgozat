import {Component, OnInit} from '@angular/core';
import {AdvertisingDto, AdvertisingService} from "../generated";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {FormBuilder, FormControl, Validators} from "@angular/forms";

@Component({
  selector: 'app-advertising-details',
  templateUrl: './advertising-details.component.html',
  styleUrls: ['./advertising-details.component.scss']
})
export class AdvertisingDetailsComponent implements OnInit {
  advertisingDto: AdvertisingDto
  editMode: boolean = false
  adId: number = Number(this.route.snapshot.paramMap.get('id'));
  categoryControl = new FormControl([] as string[], Validators.required);
  categoryList: string[] = ['Video editor', 'Programmer', 'Security', 'Visual designer', 'Consultant'];

  profileForm = this.fb.group({
    userId: [0, [Validators.required, Validators.pattern('^[0-9]*$')]],
    email: ['', [Validators.required, Validators.email]],
    text: ['', [Validators.required, Validators.maxLength(1000)]],
    address: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(100)]],
    priority: [0, [Validators.required, Validators.pattern('^[0-9]*$')]],
    isActive: [true, []]
  });

  constructor(private route: ActivatedRoute,
              private advertisingService: AdvertisingService,
              private location: Location,
              private fb: FormBuilder) {
  }

  getAd(): void {
    this.advertisingService.getAd(this.adId)
      .subscribe((ad: AdvertisingDto) => {
        this.advertisingDto = ad;
        this.profileForm.patchValue({
          userId: ad.userId,
          email: ad.email,
          text: ad.text,
          address: ad.address,
          priority: ad.priority,
          isActive: ad.isActive
        });
        this.categoryControl.patchValue(ad.category!);
      });
  }

  goBack(): void {
    this.location.back();
  }

  changeEditStatus(): void {
    this.editMode = !this.editMode;
  }

  saveAdModify(): void {
    let toModifyAd: AdvertisingDto = this.profileForm.value as AdvertisingDto;
    toModifyAd.id = this.advertisingDto.id;
    toModifyAd.category = this.categoryControl.value!;
    this.advertisingService.updateAdvertising(toModifyAd)
      .subscribe((data: any) => {
        console.log(data);
        this.getAd();
      });
    this.editMode = false
  }

  onCategoryRemoved(topping: string): void {
    const toppings: string[] = this.categoryControl.value as string[];
    this.removeFirst(toppings, topping);
    this.categoryControl.setValue(toppings);
  }

  private removeFirst<T>(array: T[], toRemove: T): void {
    const index: number = array.indexOf(toRemove);
    if (index !== -1) {
      array.splice(index, 1);
    }
  }

  ngOnInit(): void {
    this.getAd();
  }
}
