import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-page-errors',
  templateUrl: './page-errors.component.html',
  styleUrls: ['./page-errors.component.scss']
})
export class PageErrorsComponent {

  @Input()
  errors?: Error[]

}
