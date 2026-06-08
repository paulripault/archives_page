import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-manuscript-upload',
  imports: [MatButtonModule, MatIconModule],
  templateUrl: './manuscript-upload.html',
  styleUrl: './manuscript-upload.css',
})
export class ManuscriptUpload {}
