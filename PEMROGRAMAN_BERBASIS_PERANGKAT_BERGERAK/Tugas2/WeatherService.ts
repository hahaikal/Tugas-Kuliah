import axios from 'axios';

export default class WeatherService {
    private apiURL = 'https://api.open-meteo.com/v1/forecast?latitude=-6.2&longitude=106.8&hourly=temperature_2m';

    async getWeatherData() {
        try {
            const response = await axios.get(this.apiURL);
            return response.data;
        } catch (error) {
            console.error("Gagal mengambil data cuaca:", error);
            throw error;
        }
    }
}