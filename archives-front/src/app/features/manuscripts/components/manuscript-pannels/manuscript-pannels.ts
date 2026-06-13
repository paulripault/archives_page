import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatExpansionModule } from '@angular/material/expansion';

@Component({
  selector: 'app-manuscript-pannel',
  imports: [
    MatExpansionModule,
    CommonModule],
  templateUrl: './manuscript-pannels.html',
  styleUrl: './manuscript-pannels.css',
})
export class ManuscriptPannels {

  @Input() manuscript: any;

  displayFields = [
    { label: 'Titre', key: 'title' },
    { label: 'Cote', key: 'cote' },
    { label: 'Theme du manuscrit', key: 'theme' },
    { label: 'Nom du Manuscript', key: 'manuscriptName' },
    { label: 'Page', key: 'folios.page' },
    { label: 'Lieu de fabrication', key: 'manufacturingPlace' },
    { label: 'Support', key: 'support' },
    { label: 'Dimension', key: 'dimension' },
    { label: 'Enlumineur', key: 'illuminator' },
    { label: 'Traducteur', key: 'translator' },
    { label: 'Destinataire', key: 'recipient' },
    { label: 'Date', key: 'date' },
    { label: 'Lieu de conservation', key: 'conservationPlace' },
    { label: 'Nom de la rubrique', key: 'folios.sectionName' },
    { label: 'Place enluminure', key: 'folios.illuminationPosition' },
    { label: 'Folio', key: 'folios.folio', type: 'image' },
    // { label: 'Zoom', key: 'folios.zoom' },
    { label: 'Transcription', key: 'folios.transcription' },
    { label: 'Type enluminure', key: 'folios.illuminationType' },
    { label: 'Description', key: 'folios.description' },
    { label: 'Index personnes', key: 'personTags' },
    { label: 'Index lieux', key: 'placeTags' },
    { label: 'Index mots', key: 'wordTags' },
    { label: 'Liens', key: 'link', type: 'link' },
  ];

  get entries() {
    if (!this.manuscript) return [];

    return this.displayFields
      .map(f => ({
        label: f.label,
        type: f.type,
        value: this.formatValue(this.getValue(this.manuscript, f.key))
      }))
      .filter(e => e.value !== null && e.value !== undefined && e.value !== '');
  }

  private getValue(source: any, path: string): any {
    return path.split('.').reduce((currentValue, key) => {
      if (currentValue === null || currentValue === undefined) {
        return null;
      }

      if (Array.isArray(currentValue)) {
        return currentValue.map(item => item?.[key]);
      }

      return currentValue[key];
    }, source);
  }

  private formatValue(value: any): string | number | boolean | null {
    if (value === null || value === undefined || value === '') {
      return null;
    }

    if (Array.isArray(value)) {
      const formattedValues = value
        .map(item => this.formatValue(item))
        .filter(item => item !== null && item !== undefined && item !== '');

      return formattedValues.length ? formattedValues.join(', ') : null;
    }

    if (typeof value === 'object') {
      if ('name' in value) {
        return this.formatValue(value.name);
      }

      const formattedEntries = Object.entries(value)
        .filter(([key]) => key !== 'id')
        .map(([key, item]) => {
          const formattedItem = this.formatValue(item);
          return formattedItem ? `${this.formatLabel(key)} : ${formattedItem}` : null;
        })
        .filter(item => item !== null);

      return formattedEntries.length ? formattedEntries.join(', ') : null;
    }

    return value;
  }

  private formatLabel(key: string): string {
    return key
      .replace(/([A-Z])/g, ' $1')
      .replace(/^./, firstLetter => firstLetter.toUpperCase());
  }
}
