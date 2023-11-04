import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BaseComponent } from '../../shared/base-component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent extends BaseComponent {

  loginForm = this.formBuilder.group({
    email: ['', [Validators.email, Validators.required]],
    password: ['', [Validators.required]]
  });

  constructor(private router: Router, private formBuilder: FormBuilder) {
    super();
  }

  init(): void {
  }

  destroy(): void {
  }

  onSubmit() {
    if (this.loginForm.invalid) {
      return;
    }
  }

  get controls(): { [p: string]: AbstractControl } {
    return this.loginForm.controls;
  }

}
