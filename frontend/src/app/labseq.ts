export class LabseqService {
  private apiUrl = 'http://localhost:8080/labseq'; // backend URL

  async getValue(n: number): Promise<{ n: number; value: string }> {
    console.log("Fetching from API:", `${this.apiUrl}/${n}`);
    const response = await fetch(`${this.apiUrl}/${n}`, {
      method: 'GET',
      headers:{
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      mode: 'cors',
      cache: 'no-cache'
    });
    if (!response.ok) {
      throw new Error('Failed to fetch value');
    }
    return await response.json();
  }
}
