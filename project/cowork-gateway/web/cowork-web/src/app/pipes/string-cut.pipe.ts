import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'stringCut'
})
export class StringCutPipe implements PipeTransform {

  transform(value: string): string{
    const withoutExtension = value.lastIndexOf('.');
    if (withoutExtension < 8) {
      return value;
    } else {
      let newValue = value.substring(0,8);
      return newValue.concat('...');
    }
  }

}
