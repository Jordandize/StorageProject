import { Pipe, PipeTransform } from '@angular/core';
/*
 * Raise the value exponentially
 * Takes an exponent argument that defaults to 1.
 * Usage:
 *   value | exponentialStrength:exponent
 * Example:
 *   {{ 2 | exponentialStrength:10 }}
 *   formats to: 1024
*/
@Pipe({ name: 'strLimit' })
export class StrLimitPipe implements PipeTransform {

    transform(str: string, limit: number): string {
        if (str.length > limit) {
            str = str.substring(0, limit - 3).trim();
            str += '...';
        }
        return str;
    }

}
