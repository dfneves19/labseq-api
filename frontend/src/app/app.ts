import { Component, signal } from '@angular/core';
import { LabseqService } from './labseq';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  imports: [CommonModule],
  styleUrls: ['./app.css'],
  standalone: true
})
export class App {
  n = signal<number | null>(null);
  value = signal<string | null>(null);
  error = signal<string | null>(null);

  private service = new LabseqService();

  async calculate() {
    this.error.set(null);
    this.value.set(null);

    const nValue = this.n();
    if (nValue === null || nValue < 0) {
      this.error.set('Please enter a positive integer');
      return;
    }

    try {
      const data = await this.service.getValue(nValue);
      this.value.set(data.value);
    } catch {
      this.error.set('Error fetching value');
    }
  }
}
