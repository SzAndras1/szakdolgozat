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
  profileForm: FormGroup;
  files: File[] = [];
  uploadedImagesUrl: string[];
  errorMessage: string = '';
  imageMaxNumber: number = 3;

  constructor(
    private advertisingService: AdvertisingService,
    private router: Router,
    private location: Location,
    private fb: FormBuilder) {
  }

  public createAd(): void {
    this.advertisingService.createAd(this.profileForm.value as AdvertisingDto)
      .subscribe((data: AdvertisingDto) => {
        console.log(data);
      });
    this.router.navigate(['advertising'], {state: {successfulCreate: true}});
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
    this.profileForm.addControl('category', this.categoryControl);
  }

  categoryControl = new FormControl([] as string[], Validators.required);
  categoryList: string[] = ['Video editor', 'Programmer', 'Security', 'Visual designer', 'Consultant'];

  onControlRemoved(topping: string): void {
    const toppings: string[] = this.categoryControl.value as string[];
    this.removeFirst(toppings, topping);
    this.categoryControl.setValue(toppings);
  }

  private removeFirst<T>(array: T[], toRemove: T): void {
    const index = array.indexOf(toRemove);
    if (index !== -1) {
      array.splice(index, 1);
    }
  }

  handleUpload(event: any): void {
    const files: File[] = event.target.files;
    const tempArrayForImagesUrls: string[] = [];
    const maximumFileSize: number = 1048576;
    let countFileSizes: number = 0;
    if (files.length > this.imageMaxNumber) {
      this.errorMessage = 'You can upload 3 image maximum.'
      this.files = [];
      return
    }
    for (const file of files) {
      // check that all the files are images
      const extensionIndex: number = file.name.lastIndexOf('.') + 1;
      const extensionType: string = file.name.substring(extensionIndex, file.name.length).toLowerCase();
      const enabledExtensions: string[] = ['jpg', 'jpeg', 'png', 'bmp'];
      if (!enabledExtensions.includes(extensionType)) {
        this.errorMessage = 'You can only upload images.'
        this.files = [];
        return;
      }
      // check file size, if overall size is greather than 1MB, then error throws
      countFileSizes += file.size;
      if (countFileSizes >= maximumFileSize) {
        this.errorMessage = "You can upload maximum 1MB of images."
        this.files = [];
        return;
      }
      // use FileReader to preview images
      const reader = new FileReader();
      reader.readAsDataURL(file)
      reader.onload = (event) => {
        tempArrayForImagesUrls.push(<string>event.target!.result);
      }
      this.uploadedImagesUrl = tempArrayForImagesUrls;
      this.files = files;
      this.errorMessage = '';
    }
  }
}
