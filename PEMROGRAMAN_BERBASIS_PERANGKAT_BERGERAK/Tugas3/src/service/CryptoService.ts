import axios from 'axios';

export default class CryptoService {
  private apiUrl = 'https://api.coinlore.net/api/tickers/';

  async getTickers() {
    try {
      const response = await axios.get(this.apiUrl);
      
      return response.data.data;
    } catch (error) {
      console.error("Gagal mengambil data:", error);
      throw error;
    }
  }
}