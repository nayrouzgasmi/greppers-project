import { Component } from '@angular/core';
declare var $: any;

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.sass']
})
export class CategoriesComponent {
  ngOnInit(){
            /*Carausel 10 columns*/
        $(".carausel-10-columns").each(function (this: any, key: number, item: any) {
          var id = $(this).attr("id");
          var sliderID = "#" + id;
          var appendArrowsClassName = "#" + id + "-arrows";
  
          $(sliderID).slick({
              dots: false,
              infinite: true,
              speed: 1000,
              arrows: true,
              autoplay: false,
              slidesToShow: 10,
              slidesToScroll: 1,
              loop: true,
              adaptiveHeight: true,
              responsive: [
                  {
                      breakpoint: 1025,
                      settings: {
                          slidesToShow: 4,
                          slidesToScroll: 1
                      }
                  },
                  {
                      breakpoint: 768,
                      settings: {
                          slidesToShow: 3,
                          slidesToScroll: 1
                      }
                  },
                  {
                      breakpoint: 480,
                      settings: {
                          slidesToShow: 2,
                          slidesToScroll: 1
                      }
                  }
              ],
              prevArrow: '<span class="slider-btn slider-prev"><i class="fi-rs-arrow-small-left"></i></span>',
              nextArrow: '<span class="slider-btn slider-next"><i class="fi-rs-arrow-small-right"></i></span>',
              appendArrows: appendArrowsClassName
          });
      });
  }

}
