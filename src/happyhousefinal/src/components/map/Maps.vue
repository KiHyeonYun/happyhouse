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
      commicon: "", // 환경정보
      commMarkers: [],
      commflag: ""
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
              this.map.setLevel(6);
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
              this.map.setLevel(6);
            })
            .catch(() => {});
        }
      }
    },

    commInfo() {
      if (this.commflag) {
        while (this.commMarkers.length > 0) {
          this.commMarkers.pop().setMap(null);
        }
        this.commflag = false;
      } else {
        //this.guMethod(this.curGungu);
        var geocoder = new kakao.maps.services.Geocoder();
        //alert(this.commList[1].WRKP_ADDR);
        var tmpDong = this.curDong;
        var tmp;

        var map2 = this.map;
        var commMarkers2 = this.commMarkers;
        var icons = this.commicon;

        this.commList.forEach(function(element) {
          if (element.WRKP_ADDR != "") {
            geocoder.addressSearch(element.WRKP_ADDR, function(result, status) {
              if (status === kakao.maps.services.Status.OK) {
                if (tmpDong == result[0].address.region_3depth_name) {
                  tmp = new kakao.maps.Marker({
                    position: new kakao.maps.LatLng(
                      parseFloat(result[0].y),
                      parseFloat(result[0].x)
                    ),
                    //image: this.commicon,
                    //content: '<div>카카오</div>',
                    image: icons
                  });
                  //
                  var infowindow = new kakao.maps.InfoWindow({
                    content:
                      "<div>" +
                      element.WRKP_NM +
                      "</div>" +
                      "<div>" +
                      element.UPCH_COB_NM +
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
                  //
                  tmp.setMap(map2);
                  commMarkers2.push(tmp);
                }
              }
            });
          }
        });

        this.commflag = true;
        alert("final");
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
      this.commicon = new kakao.maps.MarkerImage(
        "https://cdn1.iconfinder.com/data/icons/travel-and-vacation-16/80/vector_825_18-256.png",
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
      while (this.commMarkers.length > 0) {
        this.commMarkers.pop().setMap(null);
      }
      this.addMarkersSeleted(newVal);
    },
    aptlist: function(newVal, oldVal) {
      while (this.commMarkers.length > 0) {
        this.commMarkers.pop().setMap(null);
      }
      this.addMarkersAll(newVal);
    }
  }
};
</script>

<style scoped></style>
