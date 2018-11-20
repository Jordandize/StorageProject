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
@Pipe({ name: 'defaultImage' })
export class DefaultImagePipe implements PipeTransform {

    transform(imageUrl: string, defaultUrl: string): string {
        if (imageUrl == null) {
            return defaultUrl;
        } else {
            return imageUrl;
        }
    }

}
