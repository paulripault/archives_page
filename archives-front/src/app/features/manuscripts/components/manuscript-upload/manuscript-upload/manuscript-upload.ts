import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { ManuscriptService } from '../../../services/manuscript.service';

@Component({
  selector: 'app-manuscript-upload',
  imports: [MatButtonModule, MatIconModule, MatProgressSpinnerModule],
  templateUrl: './manuscript-upload.html',
  styleUrl: './manuscript-upload.css',
})
export class ManuscriptUpload {
  isUploading = false;
  selectedFile: File | null = null;
  message = '';

  constructor(private manuscriptService: ManuscriptService) {}

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    this.selectFile(input.files?.[0]);
    input.value = '';

    if (this.selectedFile) {
      this.uploadCsv();
    }
  }

  uploadCsv(): void {
    if (!this.selectedFile) {
      this.message = 'Sélectionne un fichier CSV avant de lancer l’import.';
      return;
    }

    this.isUploading = true;
    this.message = '';

    this.manuscriptService.importCsv(this.selectedFile).subscribe({
      next: () => {
        this.reloadPage();
      },
      error: () => {
        this.message = 'Impossible d’importer ce fichier CSV.';
        this.isUploading = false;
      },
    });
  }

  private selectFile(file: File | undefined): void {
    if (!file) return;

    if (!file.name.toLowerCase().endsWith('.csv')) {
      this.selectedFile = null;
      this.message = 'Le fichier doit être au format CSV.';
      return;
    }

    this.selectedFile = file;
    this.message = '';
  }

  private reloadPage(): void {
    window.location.href = window.location.href;
  }
}
