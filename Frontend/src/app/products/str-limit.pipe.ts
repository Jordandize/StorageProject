import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'strLimit' })
export class StrLimitPipe implements PipeTransform {

    transform(str: string, limit: number): string {
        if (str.length > limit) {
            str = str.substring(0, limit - 3).trim();
            str += '..';
        }
        return str;
    }

}
