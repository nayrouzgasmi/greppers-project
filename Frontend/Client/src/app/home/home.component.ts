import { Component } from '@angular/core';

declare var $: any;
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.sass'],
})
export class HomeComponent {
  ngOnInit() {
    /*Carausel 4 columns*/
    $('.carausel-4-columns').each(function (this: any, key: number, item: any) {
      var id = $(this).attr('id');
      var sliderID = '#' + id;
      var appendArrowsClassName = '#' + id + '-arrows';

      $(sliderID).slick({
        dots: false,
        infinite: true,
        speed: 1000,
        arrows: true,
        autoplay: true,
        slidesToShow: 4,
        slidesToScroll: 1,
        loop: true,
        adaptiveHeight: true,
        responsive: [
          {
            breakpoint: 1025,
            settings: {
              slidesToShow: 3,
              slidesToScroll: 3,
            },
          },
          {
            breakpoint: 480,
            settings: {
              slidesToShow: 1,
              slidesToScroll: 1,
            },
          },
        ],
        prevArrow:
          '<span class="slider-btn slider-prev"><i class="fi-rs-arrow-small-left"></i></span>',
        nextArrow:
          '<span class="slider-btn slider-next"><i class="fi-rs-arrow-small-right"></i></span>',
        appendArrows: appendArrowsClassName,
      });
    });

  }
}