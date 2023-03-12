import {Component} from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Validators } from '@angular/forms';

@Component({
  selector: 'app-advertising-create-form',
  templateUrl: './advertising-form.component.html',
  styleUrls: ['./advertising-form.component.scss']
})
export class AdvertisingFormComponent {
  profileForm = this.fb.group({
    userId: ['', [Validators.required, Validators.pattern('^[0-9]*$')]],
    email: ['', [Validators.required, Validators.email]],
    text: ['', [Validators.required, Validators.maxLength(1000)]],
    address: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(100)]],
    priority: ['', [Validators.required, Validators.pattern('^[0-9]*$')]],
  });

  constructor(private fb: FormBuilder) { }

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
