<template>
  <div>
    <naver-maps
      :height="height"
      :width="width"
      :mapOptions="mapOptions"
      :initLayers="initLayers"
      @load="onLoad"
    >
      <naver-info-window
        class="info-window"
        @load="onWindowLoad"
        :isOpen="info"
        :marker="marker"
      >
        <div class="info-window-container">
          <h1>{{ hello }}</h1>
        </div>
      </naver-info-window>
      <naver-marker
        v-for="(item, index) in location"
        :key="index"
        :lat="item.lat"
        :lng="item.lng"
        @click="onMarkerClicked"
        @load="onMarkerLoaded"
      />
    </naver-maps>
  </div>
</template>

<script>
import Axios from 'axios';
let config = {
  headers : {
    "X-NCP-APIGW-API-KEY-ID" : process.env.NAVER_CLIENT_ID,
    "X-NCP-APIGW-API-KEY" : process.env.NAVER_CLIENT_SECRET,
    "Access-Control-Allow-Origin": "*",
    "Content-Type": "application/json; charset = utf-8"
  }
}
export default {
  name: "Map",
  data() {
    return {
      geocodeurl : process.env.GEOCODE_URL,
      location: [
        { lat: 37, lng: 127 },
        { lat: 37, lng: 128 },
        { lat: 37, lng: 129 },
        { lat: 37, lng: 125 }
      ],
      width: window.innerWidth,
      height: window.innerHeight,
      info: false,
      marker: null,
      count: 1,
      map: null,
      isCTT: false,
      mapOptions: {
        lat: 37,
        lng: 127,
        zoom: 10,
        zoomControl: true,
        zoomControlOptions: { position: "TOP_RIGHT" },
        mapTypeControl: true
      },
      initLayers: [
        "BACKGROUND",
        "BACKGROUND_DETAIL",
        "POI_KOREAN",
        "TRANSIT",
        "ENGLISH",
        "CHINESE",
        "JAPANESE"
      ]
    };
  },
  computed: {
    hello() {
      return `Hello, World! × ${this.count}`;
    }
  },
  methods: {
    onLoad(vue) {
      this.map = vue;
    },
    onWindowLoad(that) {},
    onMarkerClicked(event) {
      this.info = !this.info;
    },
    onMarkerLoaded(vue) {
      this.marker = vue.marker;
    },
    mapGeocode(item) {
      // Case geocode
      Axios.get(this.geocodeurl+item, config)
      .then(Response => {
        console.log(Response.data);
      })
    }
  },
  mounted() {
    this.mapGeocode("사직동 9");
    //setInterval(() => this.count++, 1000);
  }
};
</script>
<style scoped>
.info-window-container {
  padding: 10px;
  width: 300px;
  height: 100px;
}
</style>
