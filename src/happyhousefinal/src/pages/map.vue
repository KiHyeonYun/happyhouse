<template>
  <div>
  <!-- 왼쪽 사이드바 시작 -->
    <q-drawer v-model="left" show-if-above bordered content-class="bg-grey-1">
    <q-input square outlined v-model="search" label="주소 검색" @keyup.enter="getDongCode" >
      <template v-slot:append>
          <q-icon v-if="search !== ''" name="close" @click="search = ''" class="cursor-pointer" />
          <q-icon name="search" @click="getDongCode"  class="cursor-pointer" />
      </template>

   </q-input>

      <q-list bordered class="rounded-borders">
      <q-expansion-item
        expand-separator
        icon="perm_identity"
        label="Account settings"
        caption="John Doe"
      >
        <q-card>
          <q-card-section>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quidem, eius reprehenderit eos corrupti
            commodi magni quaerat ex numquam, dolorum officiis modi facere maiores architecto suscipit iste
            eveniet doloribus ullam aliquid.
          </q-card-section>
        </q-card>
      </q-expansion-item>

      <q-expansion-item
        expand-separator
        icon="signal_wifi_off"
        label="Wifi settings"
      >
        <q-card>
          <q-card-section>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quidem, eius reprehenderit eos corrupti
            commodi magni quaerat ex numquam, dolorum officiis modi facere maiores architecto suscipit iste
            eveniet doloribus ullam aliquid.
          </q-card-section>
        </q-card>
      </q-expansion-item>

      <q-expansion-item
        expand-separator
        icon="drafts"
        label="Drafts"
        header-class="text-purple"
      >
        <q-card>
          <q-card-section>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quidem, eius reprehenderit eos corrupti
            commodi magni quaerat ex numquam, dolorum officiis modi facere maiores architecto suscipit iste
            eveniet doloribus ullam aliquid.
          </q-card-section>
        </q-card>
      </q-expansion-item>

      <q-expansion-item icon="assessment" label="Disabled" disable>
        <q-card>
          <q-card-section>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quidem, eius reprehenderit eos corrupti
            commodi magni quaerat ex numquam, dolorum officiis modi facere maiores architecto suscipit iste
            eveniet doloribus ullam aliquid.
          </q-card-section>
        </q-card>
      </q-expansion-item>
    </q-list>
    </q-drawer>
    <!-- 왼쪽 사이드바 끝 -->

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
import Axios from "Axios";
const API_KEY = process.env.MAP_DATA_KEY;
const API_URL =
'http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev';

export default {
  name: "Map",
  data() {
    return {
      dongCode : '',
      left: false,
      loginok: false,
      search : '',
      selectedApt: '',
      apts: [],
      //오픈 api에서 받아온 데이터 넣기
       house: [],
      geocodeurl: process.env.GEOCODE_URL,
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
      return 'dssdsdsd';
    }
  },
  watch: {
    '$route' (to, from) {
      console.log(to+"   "+ from);
    },
      // console.log(this.dongCode);
      // sendDongCode(this.dongCode);
  
    },
  methods: {
        //동코드 가지고오기
    getDongCode() {
      console.log("서치 데이터는 : "+this.search);
      Axios.get(process.env.VUE_APP_SERVER_URL+'/map/'+this.search)
      .then(Response => {
        this.dongCode = Response.data;
        console.log("동코드 스프링 서버에서 가지고왔음 : "+this.dongCode);

      })
      .catch(error => {
        console.log("dong load error!  "+error);
      })
    },
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
      // this.aaa.forEach(element => {
      //   this.$navers.naver.maps.Service.geocode(
      //   { query: element },
      //   function(status, response) {

      //     console.log(response);
      //   }
      // );
      // });
    },

    //search
    sendDongCode(dongCode) {
      
      const params = {
        LAWD_CD: this.dongCode,
        DEAL_YMD: '202011',
        //get이 인코딩을 해주는데 키값은 이미 인코딩 되어있는 상태 -> 키값을 디코딩하여 넣어준다.
        serviceKey: decodeURIComponent(API_KEY), 
      };
      Axios
        .get(API_URL, {
          //params = params
          params,
        })
        .then((response) => {
          console.log(response);
          this.apts = response.data.response.body.items.item;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    selectApt(apt) {
      this.selectedApt = apt;
    },
  },
  mounted() {
    this.mapGeocode("사직동 9");
  },
  watch: {
    dongCode : function() {
      if(this.dongCode != '' || this.dongCode != null){
        this.sendDongCode(this.dongCode);
        console.log(this.apt);
      }
    }
  },
};
</script>
<style scoped>
.info-window-container {
  padding: 10px;
  width: 300px;
  height: 100px;
}
</style>
