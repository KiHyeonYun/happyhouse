<template>
<<<<<<< HEAD
  <q-page>
    <div style="padding-right:100px; padding-left:100px;">
      <div class="row" style="text-align:center">
        <div class="col">
          <div class="q-pa-md">
            <!-- {{ searchAddr.searchType }} -->
            <form
              class="form-inline row inline"
              id="searchForm"
              @keyup.enter="searchMove(searchAddr)"
            >
              <div class="col-auto my-1">
                <q-select
                  class="custom-select mr-sm-2"
                  outlined
                  style="width:120px; margin-right:10px"
                  v-model="searchoption"
                  :options="options"
                />
              </div>
              <q-input
                id="keyword"
                name="keyword"
                type="search"
                placeholder="원하시는 지역명 or 동을 입력해주세요"
                aria-label="Search"
                outlined
                debounce="300"
                color="primary"
                v-model="searchAddr.keyword"
                style="width:500px;"
              >
                <template v-slot:append>
                  <q-icon name="search" />
                </template>
              </q-input>
            </form>
          </div>
=======
  <q-page class="flex flex-center">
    <div class="q-pa-md">
      <form
        class="form-inline"
        id="searchForm"
        @submit.prevent="searchMove(searchAddr)"
      >
        <div class="col-auto my-1">
          <select
            class="custom-select mr-sm-2"
            id="searchType"
            name="searchType"
            v-model="searchAddr.searchType"
          >
            <option value="0" selected>동으로 검색</option>
            <option value="1">건물명으로 검색</option>
          </select>
>>>>>>> branch 'master' of https://github.com/KiHyeonYun/happyhouse.git
        </div>
      </div>
      <div class="row">
        <div class="col-8">
          <q-carousel
            style="height:550px; border-radius: 13px;"
            animated
            v-model="slide1"
            infinite
            :autoplay="autoplay"
            arrows
            transition-prev="slide-right"
            transition-next="slide-left"
            @mouseenter="autoplay = false"
            @mouseleave="autoplay = true"
            class="rounded-borders"
          >
            <q-carousel-slide :name="1" img-src="~assets/house1.jpg" />
            <q-carousel-slide :name="2" img-src="~assets/house2.jpg" />
            <q-carousel-slide :name="3" img-src="~assets/apart1.jpg" />
          </q-carousel>
        </div>
        <div class="col" style="margin-left:20px">
          <q-carousel
            style="height:550px; border-radius: 13px;"
            animated
            v-model="slide2"
            infinite
            :autoplay="autoplay"
            arrows
            transition-prev="slide-right"
            transition-next="slide-left"
            @mouseenter="autoplay = false"
            @mouseleave="autoplay = true"
          >
            <q-carousel-slide :name="1" img-src="~assets/adv1.png" />
            <q-carousel-slide :name="2" img-src="~assets/adv2.png" />
            <q-carousel-slide :name="3" img-src="~assets/adv3.png" />
            <q-carousel-slide :name="4" img-src="~assets/adv4.png" />
            <q-carousel-slide :name="5" img-src="~assets/adv5.png" />
          </q-carousel>
        </div>
      </div>
    </div>
  </q-page>
</template>

<script>
export default {
  name: "PageIndex",
  data() {
    return {
      slide1: "1",
      slide2: "1",
      autoplay: true,
      searchAddr: {
        dealType: "1",
        searchType: "", //동? 건물명?
        keyword: "" //검색명
      },
      options: ["동으로 검색", "건물명으로 검색"],
      searchoption: "옵션"
    };
  },
  watch: {
    searchoption: function(newVal, oldVal) {
      if (this.searchoption == this.options[0]) {
        this.searchAddr.searchType = "0";
      } else {
        this.searchAddr.searchType = "1";
      }
    }
  },
  methods: {
    searchMove(searchAddr) {
      this.$router.push({ name: "Map", params: { no: searchAddr } });
    }
  },
  mounted() {
    this.searchAddr.searchType = "0";
  }
};
</script>
<style scoped>
.custom-caption {
  text-align: center;
  padding: 12px;
  color: white;
  background-color: rgba(0, 0, 0, 0.3);
}
</style>
