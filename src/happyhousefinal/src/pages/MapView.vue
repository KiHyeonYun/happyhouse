<template>
  <div>
    <!-- 왼쪽 사이드바 시작 -->
    <q-drawer v-model="left" show-if-above bordered content-class="bg-grey-1">
      <search-bar @search-addr="searchAddress" :noData="no"> </search-bar>
      <list :aptlist="aptlist" @select-apt="selectApt"></list>
    </q-drawer>
    <!-- 왼쪽 사이드바 끝 -->

    <!-- 지도 여기다가 넣어야함 -->
    <maps :aptlist="aptlist" :apt="selectedApt"></maps>
    <!-- 지도 넣기 -->
  </div>
</template>

<script>
import axios from "axios";
import SearchBar from "../components/map/SearchBar.vue";
import List from "../components/map/List.vue";
import Maps from "../components/map/Maps.vue";

export default {
  name: "Mapview",
  props: {
    no: {}
  },
  components: {
    List,
    Maps,
    SearchBar
  },
  data() {
    return {
      dongCode: "",
      selectedApt: "",
      aptlist: [],
      apts: [],
      left: true
    };
  },
  methods: {
    searchAddress: function(searchItem) {
      axios
        .get(process.env.VUE_APP_SERVER_URL + "/map/searchHouse/", {
          params: {
            dealType: "1", //아파트 검색만 가능(현)
            searchType: searchItem.searchType,
            keyword: searchItem.keyword
          }
        })
        .then(response => {
          console.log(response); //apt list
          this.aptlist = response.data;
        })
        .catch(error => {
          console.log("값 받아오기 애러 : " + error);
        });
    },
    selectApt: function(apt) {
      this.selectedApt = apt;
    }
  },
  mounted() {
    console.log(this.no);
    axios
      .get(process.env.VUE_APP_SERVER_URL + "/map/searchHouse/", {
        params: {
          dealType: "1",
          searchType: this.no.searchType,
          keyword: this.no.keyword
        }
      })
      .then(response => {
        console.log(response); //apt list

        this.aptlist = response.data;
      })
      .catch(error => {
        console.log(error);
      });
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
