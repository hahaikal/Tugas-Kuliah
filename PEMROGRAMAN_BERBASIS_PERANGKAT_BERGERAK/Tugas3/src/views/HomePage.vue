<template>
  <ion-page>
    <ion-header :translucent="true">
      <ion-toolbar color="primary">
        <ion-title>Crypto Market</ion-title>
      </ion-toolbar>
    </ion-header>

    <ion-content :fullscreen="true" class="ion-padding">
      
      <div class="refresh-area">
        <ion-button @click="loadData" size="default" color="primary">
          Refresh
        </ion-button>
      </div>

      <div v-if="isLoading" class="loading-text">
        <p>Sedang memuat data...</p>
      </div>
      <ion-card v-for="item in cryptoList" :key="item.id" class="koin-card">
        <ion-grid>
          <ion-row class="ion-align-items-center">
            
            <ion-col size="2" class="kolom-rank">
              <div class="label-kecil">Rank</div>
              <div class="text-besar">{{ item.rank }}</div>
            </ion-col>

            <ion-col size="5" class="kolom-nama">
              <div class="label-kecil">{{ item.name }}</div>
              <div class="text-besar">{{ item.symbol }}</div>
            </ion-col>

            <ion-col size="5" class="kolom-harga">
              <div class="label-kecil">USD</div>
              <div class="text-besar">{{ item.price_usd }}</div>
            </ion-col>

          </ion-row>
        </ion-grid>
      </ion-card>

    </ion-content>
  </ion-page>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import { 
  IonContent, IonHeader, IonPage, IonTitle, IonToolbar, 
  IonButton, IonCard, IonGrid, IonRow, IonCol 
} from '@ionic/vue';
import CryptoService from '../service/CryptoService';

export default defineComponent({
  name: 'HomePage',
  components: {
    IonContent, IonHeader, IonPage, IonTitle, IonToolbar,
    IonButton, IonCard, IonGrid, IonRow, IonCol
  },
  data() {
    return {
      cryptoList: [] as any[],
      isLoading: false
    };
  },
  mounted() {
    this.loadData();
  },
  methods: {
    async loadData() {
      this.isLoading = true;
      const service = new CryptoService();
      
      try {
        const data = await service.getTickers();
        this.cryptoList = data;
      } catch (error) {
        alert("Gagal mengambil data. Cek koneksi internet.");
      } finally {
        this.isLoading = false;
      }
    }
  }
});
</script>

<style scoped>
.refresh-area {
  text-align: center;
  margin-bottom: 15px;
  margin-top: 10px;
}

.koin-card {
  --background: #FFF8E1;
  margin-bottom: 8px;
  border: 1px solid #ddd;
}

.label-kecil {
  font-size: 0.75rem;
  color: #666;
}

.text-besar {
  font-size: 1.1rem;
  font-weight: bold;
  color: #000;
}

.kolom-rank {
  text-align: center;
  border-right: 1px solid #ccc;
}

.kolom-nama {
  padding-left: 10px;
  border-right: 1px solid #ccc;
}

.kolom-harga {
  padding-left: 10px;
}

.loading-text {
  text-align: center;
  color: #888;
  font-style: italic;
}
</style>

