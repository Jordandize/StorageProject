import { Pipe, PipeTransform } from '@angular/core';

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
