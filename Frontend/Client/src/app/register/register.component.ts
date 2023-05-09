import { Component, OnInit } from '@angular/core';
import { AuthService } from '../security/auth.service';
import { Request } from '../model/sign-up-request';

import { ActivatedRoute, Router } from '@angular/router';
import { FacebookLoginProvider, GoogleLoginProvider, SocialAuthService } from 'angularx-social-login';
import { SocialService } from '../login/social.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.sass']
})
export class RegisterComponent implements OnInit{
  constructor(private authService: AuthService,private route: ActivatedRoute, private router: Router,private social: SocialService,private auth: SocialAuthService) { }

	username: string = '';
	password: string = '';
  email: string = '';
	phone_number: string = '';

	user_roles: any = [
		{name:'Client', value:'Client', selected: false},
		{name:'Marchant', value:'Marchant', selected: false},
	]

	selectedRoles: string[] = [];

	error: string = '';
	success: string = '';
	showAdditionalInputs: boolean = false;
	options: string[] = ['Option 1', 'Option 2', 'Option 3'];
	selectedOption: string ='';

	ngOnInit(): void {
	}

	onChangeCategory(event: any, role: any) {
		// set the selected state of the role based on the checkbox state
		this.selectedRoles.push(role.value);
		// check if the "Doctor" role is selected
		if (role.name === 'Doctor' && role.selected) {
		  this.showAdditionalInputs = true;
		} else {
		  this.showAdditionalInputs = false;
		}
	  }

	doSignup() {
		if(this.username !== '' && this.username !== null && this.password !== '' && this.password !== null && this.selectedRoles.length > 0) {
			const request: Request = { username: this.username, password: this.password,email: this.email,Phone_number: this.phone_number, roles: this.selectedRoles};

			this.authService.signup(request).subscribe((result)=> {
				//console.log(result);
				//this.success = 'Signup successful';
        this.router.navigateByUrl('login');
				this.success = result;
			}, (err) => {
				//console.log(err);
				this.error = 'Something went wrong during signup';
			});
		} else {
			this.error = 'All fields are mandatory';
		}
	}

	signInWithGoogle(): void {
		this.auth.signIn(GoogleLoginProvider.PROVIDER_ID).then(
		  data => {
			this.social.loginWithGoogle(data.idToken).subscribe({
			  next: response =>{
				this.router.navigateByUrl("/")
			  }
			})
			console.log(data.idToken)
		  }
		);
	  }

	  signInWithFB(): void {
		this.auth.signIn(FacebookLoginProvider.PROVIDER_ID).then(
		  data => {
			this.social.loginWithFacebook(data.authToken).subscribe({
			  next: response =>{
				this.router.navigateByUrl("/")
				let name=response.user.userRoles[0].roleName;
				console.log('role:', name);
				console.log(sessionStorage.getItem("id"));
			  }
			})
			console.log(data.authToken)
		  }
		);
	  }

}
