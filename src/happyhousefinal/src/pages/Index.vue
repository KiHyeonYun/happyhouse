<template>
  <q-page class="flex flex-center">
    <div class="q-pa-md">
      <form
        class="form-inline"
        id="searchForm"
        @keyup.enter="searchMove(searchAddr)"
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
        </div>
        <input
          class="form-control mr-sm-3"
          id="keyword"
          name="keyword"
          type="search"
          placeholder="Search"
          aria-label="Search"
          v-model="searchAddr.keyword"
        />
        <button
          class="btn my-2 my-sm-0"
          type="submit"
          @click="searchMove(searchAddr)"
        >
          검색
        </button>
      </form>
      <q-carousel
        v-model="slide"
        vertical
        transition-prev="slide-down"
        transition-next="slide-up"
        swipeable
        animated
        control-color="white"
        navigation-icon="radio_button_unchecked"
        navigation
        padding
        arrows
        height="300px"
        class="bg-purple text-white shadow-1 rounded-borders"
      >
        <q-carousel-slide name="style" class="column no-wrap flex-center">
          <q-icon name="style" size="56px" />
          <div class="q-mt-md text-center">
            {{ lorem }}
          </div>
        </q-carousel-slide>
        <q-carousel-slide name="tv" class="column no-wrap flex-center">
          <q-icon name="live_tv" size="56px" />
          <div class="q-mt-md text-center">
            {{ lorem }}
          </div>
        </q-carousel-slide>
        <q-carousel-slide name="layers" class="column no-wrap flex-center">
          <q-icon name="layers" size="56px" />
          <div class="q-mt-md text-center">
            {{ lorem }}
          </div>
        </q-carousel-slide>
        <q-carousel-slide name="map" class="column no-wrap flex-center">
          <q-icon name="terrain" size="56px" />
          <div class="q-mt-md text-center">
            {{ lorem }}
          </div>
        </q-carousel-slide>
      </q-carousel>
    </div>
  </q-page>
</template>

<script>
export default {
  name: "PageIndex",
  data() {
    return {
      slide: "style",
      lorem: "토큰 값은 : " + this.$store.state.accessToken,
      searchAddr: {
        dealType: "1",
        searchType: "", //동? 건물명?
        keyword: "" //검색명
      }
    };
  },
  methods: {
    searchMove(searchAddr) {
      this.$router.push({ path: "/map/", params: { no: searchAddr } });
    }
  },
  mounted() {
    this.searchAddr.searchType = "0";
  }
};
</script>
