import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'ellipsis'
})
export class EllipsisPipe implements PipeTransform {

  transform(value: string, ...args: unknown[]): unknown {
    if(args[0] === 'full'){return value;}
    else if (value.length > 30) {
      return value.substring(0, 30) + " ....";
    }
    console.log(value);
    return value;
  }

}
