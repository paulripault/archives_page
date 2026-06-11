import { Component, OnInit } from '@angular/core';
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

  constructor(private manuscriptService: ManuscriptService) { }

  manuscripts: any[] = [];
  selectedManuscript: any = null;
  isLoadingManuscripts = true;
  errorMessage = '';

  private manuscriptSorter = new Intl.Collator('fr', {
    numeric: true,
    sensitivity: 'base',
  });

  ngOnInit(): void {
    this.manuscriptService.getAll().subscribe({
      next: data => {
        this.manuscripts = data.sort((a, b) =>
          this.manuscriptSorter.compare(a.title ?? '', b.title ?? '')
        );
        this.isLoadingManuscripts = false;
      },
      error: () => {
        this.errorMessage = 'Impossible de charger les manuscrits. Vérifie que le serveur est bien démarré.';
        this.isLoadingManuscripts = false;
      },
    });
  }

  onManuscriptSelected(event: Event): void {
    const id = Number((event.target as HTMLSelectElement).value);
    this.selectedManuscript = this.manuscripts.find(m => m.id == id);
  }
}
