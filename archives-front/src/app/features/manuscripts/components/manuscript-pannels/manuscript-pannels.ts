import { Component, Input } from '@angular/core';
import {MatExpansionModule} from '@angular/material/expansion';

@Component({
  selector: 'app-manuscript-pannel',
  imports: [MatExpansionModule],
  templateUrl: './manuscript-pannels.html',
  styleUrl: './manuscript-pannels.css',
})
export class ManuscriptPannels {

  manuscriptEntries: {key: string, value: any}[] = [];

  @Input()
  set manuscript(value: any) {
    if (!value) {
      this.manuscriptEntries = [];
      return;
    }

    this.manuscriptEntries = Object.entries(value)
      .filter(([_, val]) => val !== null && val !== '')
      .map(([key, val]) => ({
        key,
        value: val
      }));
  }
}
