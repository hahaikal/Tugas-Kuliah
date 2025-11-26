
<template>
    <ion-page>
        <ion-header translucent="true">
            <ion-toolbar color="primary">
                <ion-title>Perkiraan Cuaca Jakarta</ion-title>
            </ion-toolbar>
        </ion-header>

        <ion-content fullscreen="true">
            <div id="container">
                <div class="ion-padding">
                    <h2>Data Temperatur per Jam</h2>
                    <p>Sumber: Open Metro API</p>

                    <ion-button expand="block" @click="fetchWeather">Ambil data cuaca</ion-button>
                </div>

                <div v-if="loading" class="ion-text-center ion-padding">
                    <ion-spinner></ion-spinner>
                    <p>Sedang Memuat...</p>
                </div>

                <ion-list v-if="weatherList.length > 0">
                    <ion-item color="light">
                        <ion-label><strong>WAKTU</strong></ion-label>
                        <ion-label slot="end"><strong>Suhu (°C)</strong></ion-label>
                    </ion-item>

                    <ion-item v-for="(item, index) in weatherList" :key="index">
                        <ion-label>{{ formatTime(item.time) }}</ion-label>

                        <ion-badge slot="end" :color="item.temp > 30 ? 'warning' : 'success'">
                            {{ item.temp }} °C
                        </ion-badge>
                    </ion-item>
                </ion-list>
            </div>
        </ion-content>
    </ion-page>
</template>

<script lang="ts">
    import { defineComponent } from 'vue';
    import { IonPage, 
            IonHeader, 
            IonToolbar, 
            IonTitle, 
            IonContent, 
            IonButton, 
            IonList, 
            IonItem, 
            IonLabel, 
            IonBadge, 
            IonSpinner } from '@ionic/vue';
    import WeatherService from './WeatherService';

    export default defineComponent({
        name: 'Home',
        components: {
            IonPage,
            IonHeader,
            IonToolbar,
            IonTitle,
            IonContent,
            IonButton,
            IonList,
            IonItem,
            IonLabel,
            IonBadge,
            IonSpinner
        },
        data() {
            return {
                weatherList: [] as any[],
                loading: false
            }
        },
        methods: {
            async fetchWeather() {
                this.loading = true;
                const data = new WeatherService();

                try {
                    const weatherData = await data.getWeatherData();
                    const times = weatherData.hourly.time;
                    const temps = weatherData.hourly.temperature_2m;

                    this.weatherList = [];

                    for(let i = 0; i < times.length; i++) {
                        this.weatherList.push({
                            time: times[i],
                            temp: temps[i]
                        });
                    }
                } catch (error) {
                    console.error('Error fetching weather data:', error);
                } finally {
                    this.loading = false;
                }
            },

            formatTime(isoString: string) {
                const date = new Date(isoString);
                return date.toLocaleTimeString('id-ID', {
                    day: 'numeric',
                    month: 'short',
                    hour: '2-digit',
                    minute: '2-digit'
                });
            }
        }
    })
</script>

<style scoped>
    ion-badge {
        font-size: 1rem;
    }
</style>
