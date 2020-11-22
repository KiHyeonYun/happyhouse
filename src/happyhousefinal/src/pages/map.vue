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
export default {
  name: "Map",
  data() {
    return {
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
      this.$navers.naver.maps.Service.geocode({ query: item }, function(
        status,
        response
      ) {
        if (status === this.$navers.naver.maps.Service.Status.ERROR) {
          return alert("Something wrong!");
        }
        console.log(response);
      });
    }
  },
  mounted() {
    this.mapGeocode("사직동 9번지");
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
