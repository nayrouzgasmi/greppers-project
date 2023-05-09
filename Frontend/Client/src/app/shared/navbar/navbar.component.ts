import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SocialAuthService } from 'angularx-social-login';
import { SocialService } from 'src/app/login/social.service';
import { AuthService } from 'src/app/security/auth.service';
declare var $: any;

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.sass']
})
export class NavbarComponent implements  OnInit{
  isSignedin = false;
  toggleCategories:boolean=false;
	constructor(private route: ActivatedRoute, private router: Router, private auth: AuthService,private social: SocialService,private authService: SocialAuthService,) {}

  ngOnInit(){
        /*--- VSticker ----*/
        $("#news-flash").vTicker({
          speed: 500,
          pause: 3000,
          animation: "fade",
          mousePause: false,
          showItems: 1
      });
  }
  doSignout() {
		this.auth.signout();
	}
  clickEventCategories(){
    this.toggleCategories = !this.toggleCategories;       
  }
}
