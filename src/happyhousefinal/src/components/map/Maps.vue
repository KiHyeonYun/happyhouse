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
          @click="envirInfo"
          icon="local_hospital"
          label="환경정보"
        />
        <q-fab-action
          color="primary"
          @click="getHospital(0)"
          icon="add_alert"
          label="선별 진료소"
        />
        <q-fab-action
          color="warning"
          @click="getHospital(1)"
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
      envirList: {
        item: []
      }, // envir
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
      enviricon: "", // 환경정보
      envirmarkers: [],
      envirflag: ""
    };
  },

  methods: {
    getHospital(t) {
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

    envirInfo() {
      if (this.envirflag) {
        while (this.envirmarkers.length > 0) {
          this.envirmarkers.pop().setMap(null);
        }
        this.envirflag = false;
      } else {
        //this.guMethod(this.curGungu);
        var geocoder = new kakao.maps.services.Geocoder();
        //alert(this.envirList[1].WRKP_ADDR);
        var tmpDong = this.curDong;
        var tmp;

        var map2 = this.map;
        var envirmarkers2 = this.envirmarkers;
        alert(this.curGungu);
        var icons = this.enviricon;

        this.envirList.forEach(function(element) {
          if (element.WRKP_ADDR != "") {
            geocoder.addressSearch(element.WRKP_ADDR, function(result, status) {
              //alert("1");
              if (status === kakao.maps.services.Status.OK) {
                if (tmpDong == result[0].address.region_3depth_name) {
                  tmp = new kakao.maps.Marker({
                    position: new kakao.maps.LatLng(
                      parseFloat(result[0].y),
                      parseFloat(result[0].x)
                    ),
                    //image: this.enviricon,
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
                  envirmarkers2.push(tmp);
                }
              }
            });
          }
        });

        this.envirflag = true;
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
        "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png",
        new kakao.maps.Size(40, 40)
      );
      this.cliIcon = new kakao.maps.MarkerImage(
        "https://cdn2.iconfinder.com/data/icons/health-care-4/512/medical_map_marker-256.png",
        new kakao.maps.Size(40, 40)
      );
      //https://cdn4.iconfinder.com/data/icons/recycle-and-environment/600/pointer-marker-pin-Conservation-green-recycle-recycling-Ecology-environment-packaging-256.png
      this.enviricon = new kakao.maps.MarkerImage(
        "https://cdn4.iconfinder.com/data/icons/recycle-and-environment/600/pointer-marker-pin-Conservation-green-recycle-recycling-Ecology-environment-packaging-256.png",
        new kakao.maps.Size(40, 40)
      );

      //alert("init!");
    },

    guMethod(gugu) {
      //var tmpEnvir = this.envirList;
      if (gugu == "11320") {
        //도봉구
        axios
          .get(
            "http://openAPI.dobong.go.kr:8088/594a4779786f706538344c42526b75/json/DobongListEnvGuideCheck/1/50"
          )
          .then(
            response =>
              (this.envirList = response.data.DobongListEnvGuideCheck.row)
          );
      } else if (gugu == "11530") {
        //구로구
        axios
          .get(
            "http://openAPI.guro.go.kr:8088/594a4779786f706538344c42526b75/json/GuroListEnvGuideCheck/1/50"
          )
          .then(
            response =>
              (this.envirList = response.data.GuroListEnvGuideCheck.row)
          );
      } else if (gugu == "11110") {
        //"종로구"
        axios
          .get(
            "http://openAPI.jongno.go.kr:8088/594a4779786f706538344c42526b75/json/JongnoListEnvGuideCheck/1/982"
          )
          .then(
            response =>
              (this.envirList = response.data.JongnoListEnvGuideCheck.row)
          );
      } else if (gugu == "11140") {
        // "중구"
        axios
          .get(
            "http://openAPI.junggu.seoul.kr:8088/594a4779786f706538344c42526b75/json/JungguListEnvGuideCheck/1/50"
          )
          .then(
            response =>
              (this.envirList = response.data.JungguListEnvGuideCheck.row)
          );
      } else if (gugu == "11560") {
        //영등포구
        axios
          .get(
            "http://openAPI.ydp.go.kr:8088/594a4779786f706538344c42526b75/json/YdpListEnvGuideCheck/1/50"
          )
          .then(
            response =>
              (this.envirList = response.data.YdpListEnvGuideCheck.row)
          );
      } else if (gugu == "11740") {
        //강동구
        axios
          .get(
            "http://openAPI.gd.go.kr:8088/594a4779786f706538344c42526b75/json/GdListEnvGuideCheck/1/50"
          )
          .then(
            response => (this.envirList = response.data.GdListEnvGuideCheck.row)
          );
      } else if (gugu == "11680") {
        //강남구
        axios
          .get(
            "http://openAPI.gangnam.go.kr:8088/594a4779786f706538344c42526b75/json/GnListEnvGuideCheck/1/50"
          )
          .then(
            response => (this.envirList = response.data.GnListEnvGuideCheck.row)
          );
      } else if (gugu == "11200") {
        //"성동구"
        axios
          .get(
            "http://openAPI.sd.go.kr:8088/594a4779786f706538344c42526b75/json/SdListEnvGuideCheck/1/50"
          )
          .then(
            response => (this.envirList = response.data.SdListEnvGuideCheck.row)
          );
      } else if (gugu == "11410") {
        //서대문구
        axios
          .get(
            "http://openAPI.sdm.go.kr:8088/594a4779786f706538344c42526b75/json/SeodaemunListEnvGuideCheck/1/50"
          )
          .then(
            response =>
              (this.envirList = response.data.SeodaemunListEnvGuideCheck.row)
          );
      } else if (gugu == "11440") {
        //마포구
        axios
          .get(
            "http://openAPI.mapo.go.kr:8088/594a4779786f706538344c42526b75/json/MpListEnvGuideCheck/1/50"
          )
          .then(
            response => (this.envirList = response.data.MpListEnvGuideCheck.row)
          );
      } else if (gugu == "11290") {
        //"성북구"
        axios
          .get(
            "http://openAPI.sb.go.kr:8088/594a4779786f706538344c42526b75/json/SbListEnvGuideCheck/1/50"
          )
          .then(
            response => (this.envirList = response.data.SbListEnvGuideCheck.row)
          );
      } else if (gugu == "11650") {
        //서초구
        axios
          .get(
            "http://openAPI.seocho.go.kr:8088/594a4779786f706538344c42526b75/json/ScListEnvGuideCheck/1/50"
          )
          .then(
            response => (this.envirList = response.data.ScListEnvGuideCheck.row)
          );
      } else if (gugu == "11590") {
        //동작구
        axios
          .get(
            "http://openAPI.dongjak.go.kr:8088/594a4779786f706538344c42526b75/json/DjListEnvGuideCheck/1/50"
          )
          .then(
            response => (this.envirList = response.data.DjListEnvGuideCheck.row)
          );
      } else if (gugu == "11710") {
        //송파구
        axios
          .get(
            "http://openAPI.songpa.seoul.kr:8088/594a4779786f706538344c42526b75/json/SpListEnvGuideCheck/1/50"
          )
          .then(
            response => (this.envirList = response.data.SpListEnvGuideCheck.row)
          );
      } else if (gugu == "11470") {
        //양천구
        axios
          .get(
            "http://openAPI.yangcheon.go.kr:8088/594a4779786f706538344c42526b75/json/YcListEnvGuideCheck/1/50"
          )
          .then(
            response => (this.envirList = response.data.YcListEnvGuideCheck.row)
          );
      } else if (gugu == "11230") {
        //"동대문구"
        axios
          .get(
            "http://openAPI.ddm.go.kr:8088/594a4779786f706538344c42526b75/json/DongdeamoonListEnvGuideCheck/1/50"
          )
          .then(
            response =>
              (this.envirList = response.data.DongdeamoonListEnvGuideCheck.row)
          );
      } else if (gugu == "11350") {
        //노원구
        axios
          .get(
            "http://openAPI.nowon.go.kr:8088/594a4779786f706538344c42526b75/json/NwListEnvGuideCheck/1/50"
          )
          .then(
            response => (this.envirList = response.data.NwListEnvGuideCheck.row)
          );
      } else if (gugu == "11170") {
        //"용산구"
        axios
          .get(
            "http://openAPI.yongsan.go.kr:8088/594a4779786f706538344c42526b75/json/YsListEnvGuideCheck/1/50"
          )
          .then(
            response => (this.envirList = response.data.YsListEnvGuideCheck.row)
          );
      } else if (gugu == "11215") {
        //"광진구"
        axios
          .get(
            "http://openAPI.gwangjin.go.kr:8088/594a4779786f706538344c42526b75/json/GwangjinListEnvGuideCheck/1/50"
          )
          .then(
            response =>
              (this.envirList = response.data.GwangjinListEnvGuideCheck.row)
          );
      } else if (gugu == "11500") {
        //강서구
        axios
          .get(
            "http://openAPI.gangseo.seoul.kr:8088/594a4779786f706538344c42526b75/json/GangseoListEnvGuideCheck/1/50"
          )
          .then(
            response =>
              (this.envirList = response.data.GangseoListEnvGuideCheck.row)
          );
      } else if (gugu == "11545") {
        //금천구
        axios
          .get(
            "http://openAPI.geumcheon.go.kr:8088/594a4779786f706538344c42526b75/json/GeumcheonListEnvGuideCheck/1/50"
          )
          .then(
            response =>
              (this.envirList = response.data.GeumcheonListEnvGuideCheck.row)
          );
      } else if (gugu == "11260") {
        //"중랑구"
        axios
          .get(
            "http://openAPI.jungnang.seoul.kr:8088/594a4779786f706538344c42526b75/json/JungnangListEnvGuideCheck/1/50"
          )
          .then(
            response =>
              (this.envirList = response.data.JungnangListEnvGuideCheck.row)
          );
      } else if (gugu == "11620") {
        //관악구
        axios
          .get(
            "http://openAPI.gwanak.go.kr:8088/594a4779786f706538344c42526b75/json/GaListEnvGuideCheck/1/50"
          )
          .then(
            response => (this.envirList = response.data.GaListEnvGuideCheck.row)
          );
      } else if (gugu == "11380") {
        //은평구
        axios
          .get(
            "http://openAPI.ep.go.kr:8088/594a4779786f706538344c42526b75/json/EpListEnvGuideCheck/1/50"
          )
          .then(
            response => (this.envirList = response.data.EpListEnvGuideCheck.row)
          );
      } else {
        alert("에러!");
      }
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

        //hoverText = new

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
      this.guMethod(this.curGungu); /////////////////////////

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
  created() {
    // axios
    // .get('http://openAPI.jongno.go.kr:8088/594a4779786f706538344c42526b75/json/JongnoListEnvGuideCheck/1/5')
    // .then((response) => (this.envirList = response.data.JongnoListEnvGuideCheck.row));
    // axios
    // .get('http://openAPI.junggu.seoul.kr:8088/594a4779786f706538344c42526b75/json/JungguListEnvGuideCheck/1/982')
    // .then((response) => (this.envirList += response.data.JungguListEnvGuideCheck.row));
    // axios
    // .get('http://openAPI.guro.go.kr:8088/594a4779786f706538344c42526b75/json/GuroListEnvGuideCheck/1/100')
    // .then((response) => (this.envirList = response.data.GuroListEnvGuideCheck.row));
    // console.log("완료");
  },
  mounted() {
    console.log("맵을 만들자");

    this.tmpApt = this.apt;
    window.kakao && window.kakao.maps
      ? this.initMap()
      : this.addKakaoMapScript();
  },
  updated() {
    // console.log("맵을 만들자");
    // this.$nextTick(function() {
    //   if (this.tmpApt != this.apt) {
    //     this.addMarkersSeleted(this.apt); //.lat, this.apt.lng
    //     this.tmpApt = this.apt;
    //   } else {
    //     this.addMarkersAll(this.aptlist);
    //   }
    // });
  },
  watch: {
    apt: function(newVal, oldVal) {
      while (this.envirmarkers.length > 0) {
        this.envirmarkers.pop().setMap(null);
      }
      this.addMarkersSeleted(newVal);
    },
    aptlist: function(newVal, oldVal) {
      while (this.envirmarkers.length > 0) {
        this.envirmarkers.pop().setMap(null);
      }
      this.addMarkersAll(newVal);
    }
  }
};
</script>

<style scoped></style>
