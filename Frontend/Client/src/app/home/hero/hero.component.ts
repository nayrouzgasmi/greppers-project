import { Component } from '@angular/core';
declare var $: any;

@Component({
  selector: 'app-hero',
  templateUrl: './hero.component.html',
  styleUrls: ['./hero.component.sass']
})
export class HeroComponent {
  HeroComponent() {}
  ngOnInit() {
    /*------ Hero slider 1 ----*/
    $('.hero-slider-1').slick({
      slidesToShow: 1,
      slidesToScroll: 1,
      fade: true,
      loop: true,
      dots: true,
      arrows: true,
      prevArrow:
        '<span class="slider-btn slider-prev"><i class="fi-rs-angle-left"></i></span>',
      nextArrow:
        '<span class="slider-btn slider-next"><i class="fi-rs-angle-right"></i></span>',
      appendArrows: '.hero-slider-1-arrow',
      autoplay: true,
    });
  }
}
