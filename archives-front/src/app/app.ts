import { ChangeDetectorRef, Component, OnInit, signal } from '@angular/core';
import { ManuscriptService } from './services/manuscript.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  styleUrl: './app.css',
  imports: [],
  standalone: true,
})
export class App implements OnInit{

  manuscripts: any[] = [];

  constructor(private manuscriptService: ManuscriptService, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.manuscriptService.getAll().subscribe({
      next: data => {
        console.log('DATA API', data);
        this.manuscripts = data;
        this.cdr.detectChanges()
      },
      error: err => {
        console.log('API ERROR', err);
      }
    });
  }
}
