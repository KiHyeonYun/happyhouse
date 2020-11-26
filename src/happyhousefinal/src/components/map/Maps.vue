<template>
  <div class="home">
    <div style="display:none">{{ this.aptlist }}</div>
    <q-page-sticky style="z-index : 5;" position="top-left" :offset="[18, 18]">
      <q-fab
        v-model="fab"
        label="지역정보"
        label-position="left"
        color="purple"
        icon="keyboard_arrow_right"
        direction="right"
      >
        <q-fab-action
          color="secondary"
          @click="commInfo"
          icon="local_hospital"
          label="상가정보"
        />
        <q-fab-action
          color="primary"
          @click="getCorona(0)"
          icon="add_alert"
          label="선별 진료소"
        />
        <q-fab-action
          color="warning"
          @click="getCorona(1)"
          icon="local_hospital"
          label="안심병원"
        />
      </q-fab>
    </q-page-sticky>
    <div id="map" style="margin: 0; height : 876px;" class="map"></div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "Maps",
  props: {
    apt: null,
    aptlist: null
  },

  data() {
    return {
      commList: {
        item: []
      }, // comm
      loading1: false,
      map: null,
      tmpApt: null,
      fab: false, //플로팅 버튼
      marker: null,
      multimarker: "",
      markers: [],
      hospMarkers: [],
      cliMarkers: [],
      dealicon: "",
      hospIcon: "",
      cliIcon: "",
      curGungu: "",
      curDong: "", //
      hospFlag: "", // = false;
      cliFlag: "", // = false;
      commicon: "", // 상권정보
      commMarkers: [],
      commflag: "",
      sport: "",
      tourism: "",
      edu: "",
      realestate: "",
      food: "",
      lodgment: "",
      lifeService: "",
      commi: ""
    };
  },

  methods: {
    getCorona(t) {
      var avglat = 0.0;
      var avglng = 0.0;
      if (t == 0) {
        if (this.hospFlag) {
          while (this.hospMarkers.length > 0) {
            this.hospMarkers.pop().setMap(null);
          }
          this.hospFlag = false;
        } else {
          axios
            .get(process.env.VUE_APP_SERVER_URL + "/map/corona/", {
              params: {
                type: t,
                code: this.curGungu
              }
            })
            .then(response => {
              var tmp;
              console.log("맵 마커 찍는중...");
              response.data.forEach(vo => {
                tmp = new kakao.maps.Marker({
                  position: new kakao.maps.LatLng(
                    parseFloat(vo.lat),
                    parseFloat(vo.lng)
                  ),
                  image: this.hospIcon
                });
                //
                var infowindow = new kakao.maps.InfoWindow({
                  content:
                    "<div>" +
                    vo.name +
                    "</div>" +
                    "<div>" +
                    vo.tel +
                    " " +
                    vo.weekdayop +
                    "</div>" // 인포윈도우에 표시할 내용
                });

                kakao.maps.event.addListener(
                  tmp,
                  "mouseover",
                  this.makeOverListener(this.map, tmp, infowindow)
                );
                kakao.maps.event.addListener(
                  tmp,
                  "mouseout",
                  this.makeOutListener(infowindow)
                );

                avglat += parseFloat(vo.lat);
                avglng += parseFloat(vo.lng);

                tmp.setMap(this.map);
                this.hospMarkers.push(tmp);
                // this.map.setLevel(6);
              });

              //

              this.hospFlag = true;

              avglat /= this.hospMarkers.length;
              avglng /= this.hospMarkers.length;

              this.map.setCenter(new kakao.maps.LatLng(avglat, avglng));
              this.map.setLevel(4);
            })
            .catch(() => {});
        }
      } else {
        if (this.cliFlag) {
          while (this.cliMarkers.length > 0) this.cliMarkers.pop().setMap(null);
          this.cliFlag = false;
        } else {
          axios
            .get(process.env.VUE_APP_SERVER_URL + "/map/corona/", {
              params: {
                type: t,
                code: this.curGungu
              }
            })
            .then(response => {
              var tmp;
              response.data.forEach(vo => {
                tmp = new kakao.maps.Marker({
                  position: new kakao.maps.LatLng(
                    parseFloat(vo.lat),
                    parseFloat(vo.lng)
                  ),
                  image: this.cliIcon
                });

                ////
                var infowindow = new kakao.maps.InfoWindow({
                  content:
                    "<div>" +
                    vo.name +
                    "</div>" +
                    "<div>" +
                    vo.tel +
                    " " +
                    vo.weekdayop +
                    "</div>" // 인포윈도우에 표시할 내용
                });

                kakao.maps.event.addListener(
                  tmp,
                  "mouseover",
                  this.makeOverListener(this.map, tmp, infowindow)
                );
                kakao.maps.event.addListener(
                  tmp,
                  "mouseout",
                  this.makeOutListener(infowindow)
                );

                avglat += parseFloat(vo.lat);
                avglng += parseFloat(vo.lng);

                tmp.setMap(this.map);
                this.cliMarkers.push(tmp);
                //     this.map.setLevel(6);
              });
              this.cliFlag = true;
              avglat /= this.cliMarkers.length;
              avglng /= this.cliMarkers.length;

              this.map.setCenter(new kakao.maps.LatLng(avglat, avglng));
              this.map.setLevel(4);
            })
            .catch(() => {});
        }
      }
    },

    commInfo() {
      var avglat = 0.0;
      var avglng = 0.0;
      if (this.commflag) {
        while (this.commMarkers.length > 0) this.commMarkers.pop().setMap(null);
        this.commflag = false;
      } else {
        axios
          .get(process.env.VUE_APP_SERVER_URL + "/map/comm/" + this.curDong)
          .then(response => {
            var tmp;
            response.data.forEach(vo => {
              if (vo.bigClass == "음식") {
                this.commicon = this.food;
              } else if (vo.bigClass == "소매") {
                this.commicon = this.commi;
              } else if (vo.bigClass == "생활서비스") {
                this.commicon = this.lifeService;
              } else if (vo.bigClass == "학문/교육") {
                this.commicon = this.edu;
              } else if (vo.bigClass == "관광/여가/오락") {
                this.commicon = this.tourism;
              } else if (vo.bigClass == "부동산") {
                this.commicon = this.realestate;
              } else if (vo.bigClass == "숙박") {
                this.commicon = this.lodgment;
              } else if (vo.bigClass == "스포츠") {
                this.commicon = this.sport;
              } else {
                this.commicon = this.commi;
              }
              tmp = new kakao.maps.Marker({
                position: new kakao.maps.LatLng(
                  parseFloat(vo.lat),
                  parseFloat(vo.lng)
                ),

                image: this.commicon
              });

              ////
              var infowindow = new kakao.maps.InfoWindow({
                content:
                  "<div>" +
                  vo.storeName +
                  "</div>" +
                  "<div>" +
                  vo.bigClass +
                  " " +
                  "</div>" // 인포윈도우에 표시할 내용
              });

              kakao.maps.event.addListener(
                tmp,
                "mouseover",
                this.makeOverListener(this.map, tmp, infowindow)
              );
              kakao.maps.event.addListener(
                tmp,
                "mouseout",
                this.makeOutListener(infowindow)
              );

              avglat += parseFloat(vo.lat);
              avglng += parseFloat(vo.lng);

              tmp.setMap(this.map);
              this.commMarkers.push(tmp);
              //     this.map.setLevel(6);
            });
            this.commflag = true;
            avglat /= this.commMarkers.length;
            avglng /= this.commMarkers.length;

            this.map.setCenter(new kakao.maps.LatLng(avglat, avglng));
            this.map.setLevel(4);
          })
          .catch(error => {
            console.log(error);
          });
      }
    },

    addKakaoMapScript() {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?appkey=" +
        process.env.KAKAO_KEY +
        "&libraries=drawing,services,clusterer";
      document.head.appendChild(script);
    },

    initMap() {
      var container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
      var options = {
        //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(37.527541, 126.999239), //지도의 중심좌표.
        level: 3 //지도의 레벨(확대, 축소 정도)
      }; //var map = new kakao.maps.Map(container, options);
      this.map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

      this.hospIcon = new kakao.maps.MarkerImage(
        "https://cdn3.iconfinder.com/data/icons/buildings-1-4/129/1-256.png",
        new kakao.maps.Size(40, 40)
      );
      this.cliIcon = new kakao.maps.MarkerImage(
        "https://cdn2.iconfinder.com/data/icons/jetflat-buildings/90/008_010_hospital_clinic_building-256.png",
        new kakao.maps.Size(40, 40)
      );
      //소매
      this.commi = new kakao.maps.MarkerImage(
        "https://cdn1.iconfinder.com/data/icons/travel-and-vacation-16/80/vector_825_18-256.png",
        new kakao.maps.Size(40, 40)
      );
      //숙박
      this.lodgment = new kakao.maps.MarkerImage(
        "https://cdn3.iconfinder.com/data/icons/needs-of-the-hotel/640/HOTEL-512.png",
        new kakao.maps.Size(40, 40)
      );
      //음식
      this.food = new kakao.maps.MarkerImage(
        "https://cdn4.iconfinder.com/data/icons/ios-web-user-interface-multi-circle-flat-vol-6/512/Food_fork_kitchen_knife_meanns_restaurant-256.png",
        new kakao.maps.Size(40, 40)
      );
      //생활서비스
      this.lifeService = new kakao.maps.MarkerImage(
        "https://cdn2.iconfinder.com/data/icons/occupations-people-scenes/64/mechanic-256.png",
        new kakao.maps.Size(40, 40)
      );
      //교육
      this.edu = new kakao.maps.MarkerImage(
        "https://cdn0.iconfinder.com/data/icons/business-finance-vol-5-11/512/9-512.png",
        new kakao.maps.Size(40, 40)
      );
      //관광 여가 오락
      this.tourism = new kakao.maps.MarkerImage(
        "https://cdn1.iconfinder.com/data/icons/set-4/76/tent-512.png",
        new kakao.maps.Size(40, 40)
      );
      //부동산
      this.realestate = new kakao.maps.MarkerImage(
        "https://cdn3.iconfinder.com/data/icons/personal-business-finance-2/380/Home_Loan-256.png",
        new kakao.maps.Size(40, 40)
      );
      this.sport = new kakao.maps.MarkerImage(
        "https://cdn4.iconfinder.com/data/icons/sport-fitness-vol-01/512/01-football-soccor-sport-256.png",
        new kakao.maps.Size(40, 40)
      );
    },
    addMarkersAll(data) {
      var tmp;
      // var hoverText;
      var avglat = 0.0;
      var avglng = 0.0;
      while (this.markers.length > 0) {
        this.markers.pop().setMap(null);
      }
      while (this.hospMarkers.length > 0) {
        this.hospMarkers.pop().setMap(null);
        this.hospFlag = false;
      }
      while (this.cliMarkers.length > 0) {
        this.cliMarkers.pop().setMap(null);
        this.cliFlag = false;
      }
      while (this.commMarkers.length > 0) {
        this.commMarkers.pop().setMap(null);
        this.commflag = false;
      }
      if (this.marker != null) this.marker.setMap(null);

      data.forEach(vo => {
        tmp = new kakao.maps.Marker({
          position: new kakao.maps.LatLng(
            parseFloat(vo.lat),
            parseFloat(vo.lng)
          )
        });
        //
        var infowindow = new kakao.maps.InfoWindow({
          content:
            "<div>" + vo.aptName + "</div>" + "<div>" + vo.dealAmount + "</div>" // 인포윈도우에 표시할 내용
        });

        avglat += parseFloat(vo.lat);
        avglng += parseFloat(vo.lng);
        this.curGungu = vo.gunguCode;
        this.curDong = vo.dong;

        tmp.setMap(this.map);
        this.markers.push(tmp);
        //
        kakao.maps.event.addListener(
          tmp,
          "mouseover",
          this.makeOverListener(this.map, tmp, infowindow)
        );
        kakao.maps.event.addListener(
          tmp,
          "mouseout",
          this.makeOutListener(infowindow)
        );
      });
      //alert(this.curGungu);
      // this.guMethod(this.curGungu);

      avglat /= this.markers.length;
      avglng /= this.markers.length;

      this.map.setCenter(new kakao.maps.LatLng(avglat, avglng));
      this.map.setLevel(4);
    },

    //
    makeOverListener(map, marker, infowindow) {
      return function() {
        infowindow.open(map, marker);
      };
    },

    // 인포윈도우를 닫는 클로저를 만드는 함수입니다
    makeOutListener(infowindow) {
      return function() {
        infowindow.close();
      };
    },

    //

    addMarkersSeleted(vo) {
      while (this.markers.length > 0) {
        this.markers.pop().setMap(null);
      }
      while (this.hospMarkers.length > 0) {
        this.hospMarkers.pop().setMap(null);
        this.hospFlag = false;
      }
      while (this.cliMarkers.length > 0) {
        this.cliMarkers.pop().setMap(null);
        this.cliFlag = false;
      }
      while (this.commMarkers.length > 0) {
        this.commMarkers.pop().setMap(null);
        this.commflag = false;
      }
      if (this.marker != null) this.marker.setMap(null);

      this.marker = new kakao.maps.Marker({
        map: this.map,
        position: new kakao.maps.LatLng(vo.lat, vo.lng)
      });

      //
      var infowindow = new kakao.maps.InfoWindow({
        content:
          "<div>" + vo.aptName + "</div>" + "<div>" + vo.dealAmount + "</div>" // 인포윈도우에 표시할 내용
      });

      kakao.maps.event.addListener(
        this.marker,
        "mouseover",
        this.makeOverListener(this.map, this.marker, infowindow)
      );
      kakao.maps.event.addListener(
        this.marker,
        "mouseout",
        this.makeOutListener(infowindow)
      );
      //

      this.map.setCenter(new kakao.maps.LatLng(vo.lat, vo.lng));
      this.map.setLevel(3);
    }
  },
  mounted() {
    console.log("map create");
    this.tmpApt = this.apt;
    window.kakao && window.kakao.maps
      ? this.initMap()
      : this.addKakaoMapScript();
  },

  watch: {
    apt: function(newVal, oldVal) {
      this.addMarkersSeleted(newVal);
    },
    aptlist: function(newVal, oldVal) {
      this.addMarkersAll(newVal);
    }
  }
};
</script>

<style scoped></style>
