export class LabseqService {
  private apiUrl = 'http://localhost:8080/labseq'; // backend URL

  async getValue(n: number): Promise<{ n: number; value: string }> {
    const response = await fetch(`${this.apiUrl}/${n}`);
    if (!response.ok) {
      throw new Error('Failed to fetch value');
    }
    return await response.json();
  }
}
