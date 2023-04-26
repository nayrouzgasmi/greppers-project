import {AbstractControl, FormControl, ValidationErrors, ValidatorFn} from '@angular/forms';

export class SpaceValidator {

  static onlyContainSpace(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      if (control.value && control.value.trim().length === 0) {
        return {'noSpace': true};
      } else {
        return null;
      }
    };
  }
}