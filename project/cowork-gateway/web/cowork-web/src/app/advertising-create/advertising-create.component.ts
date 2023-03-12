import {Component, OnInit} from '@angular/core';
import {AdvertisingDto, AdvertisingService} from "../generated";
import {Router} from "@angular/router";
import {Location} from "@angular/common";
import {AdvertisingFormComponent} from "../advertising-form/advertising-form.component";
import {FormBuilder, Validators} from "@angular/forms";

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
    private location: Location,
    private fb: FormBuilder) {
  }
  public createAd(): void{
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
  profileForm = this.fb.group({
    userId: ['', [Validators.required, Validators.pattern('^[0-9]*$')]],
    email: ['', [Validators.required, Validators.email]],
    text: ['', [Validators.required, Validators.maxLength(1000)]],
    address: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(100)]],
    priority: ['', [Validators.required, Validators.pattern('^[0-9]*$')]],
  });

  getUserIdErrorMessage(): string {
    if (this.profileForm.controls.userId.hasError('required')) {
      return 'You must enter a value';
    }
    if (this.profileForm.controls.userId.hasError('pattern')) {
      return 'Incorrect pattern';
    }

    return this.profileForm.controls.userId.hasError('required') ? 'Not a valid userId' : '';
  }

  getEmailErrorMessage(): string {
    if (this.profileForm.controls.email.hasError('required')) {
      return 'You must enter a value';
    }
    if (this.profileForm.controls.email.hasError('email')) {
      return 'Incorrect pattern';
    }

    return this.profileForm.controls.email.hasError('required') ? 'Not a valid email' : '';
  }

  getTextErrorMessage(): string {
    if (this.profileForm.controls.text.hasError('required')) {
      return 'You must enter a value';
    }
    if (this.profileForm.controls.text.hasError('maxlength')) {
      return 'Maximum 1000 characters';
    }

    return this.profileForm.controls.text.hasError('required') ? 'Not a valid text' : '';
  }

  getAddressErrorMessage(): string {
    if (this.profileForm.controls.address.hasError('required')) {
      return 'You must enter a value';
    }
    if (this.profileForm.controls.address.hasError('minlength')) {
      return 'Must be at least 3 characters';
    }
    if (this.profileForm.controls.address.hasError('maxlength')) {
      return 'Maximum 1000 characters';
    }

    return this.profileForm.controls.address.hasError('required') ? 'Not a valid address' : '';
  }

  getPriorityErrorMessage(): string {
    if (this.profileForm.controls.priority.hasError('required')) {
      return 'You must enter a value';
    }
    if (this.profileForm.controls.priority.hasError('pattern')) {
      return 'Incorrect pattern';
    }

    return this.profileForm.controls.priority.hasError('required') ? 'Not a valid priority' : '';
  }
}
