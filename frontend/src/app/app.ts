import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('frontend');
}

import { LabseqService } from './labseq';

const service = new LabseqService();

window.addEventListener('DOMContentLoaded', () => {
  const input = document.getElementById('nInput') as HTMLInputElement;
  const button = document.getElementById('calculateButton') as HTMLButtonElement;
  const resultDiv = document.getElementById('resultDiv') as HTMLDivElement;
  const errorDiv = document.getElementById('errorDiv') as HTMLDivElement;

  button.addEventListener('click', async () => {
    resultDiv.innerHTML = '';
    errorDiv.innerHTML = '';

    const n = Number(input.value);

    if (isNaN(n) || n < 0) {
      errorDiv.innerHTML = 'Please enter a positive integer';
      return;
    }

    try {
      const data = await service.getValue(n);
      resultDiv.innerHTML = `<p><strong>n:</strong> ${data.n}</p>
                             <p><strong>value:</strong> ${data.value}</p>`;
    } catch (err) {
      errorDiv.innerHTML = 'Error fetching value';
    }
  });
});
