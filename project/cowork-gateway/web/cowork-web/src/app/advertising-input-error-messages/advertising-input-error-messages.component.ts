import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-advertising-input-error-messages',
  templateUrl: './advertising-input-error-messages.component.html',
  styleUrls: ['./advertising-input-error-messages.component.scss']
})
export class AdvertisingInputErrorMessagesComponent {
  userAddressValidations: FormGroup;
  constructor(private formBuilder: FormBuilder) {}
  ngOnInit() {
    this.userAddressValidations = this.formBuilder.group({
      address: [
        '',
        [
          Validators.required,
          Validators.minLength(4),
          Validators.maxLength(20),
        ],
      ],
    });
  }
}
