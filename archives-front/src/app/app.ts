import { ChangeDetectorRef, Component, OnInit, signal } from '@angular/core';
import { ManuscriptService } from './features/manuscripts/services/manuscript.service';
import { ManuscriptPannels } from './features/manuscripts/components/manuscript-pannels/manuscript-pannels';
import { ManuscriptUpload } from './features/manuscripts/components/manuscript-upload/manuscript-upload/manuscript-upload';

@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  styleUrl: './app.css',
  imports: [ManuscriptPannels, ManuscriptUpload],
  standalone: true,
})
export class App implements OnInit {

  constructor(private manuscriptService: ManuscriptService, private cdr: ChangeDetectorRef) { }

  manuscripts: any[] = [];
  selectedManuscript: any = null;

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

  onManuscriptSelected(event: Event): void {
    const id = Number((event.target as HTMLSelectElement).value);
    this.selectedManuscript = this.manuscripts.find(m => m.id == id);
  }
}
