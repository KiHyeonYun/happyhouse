<template>
  <div class="home">
    <q-slide-item
      class="q-pa-md"
      @left="onLeft"
      @right="onRight"
      left-color="purple"
      right-color="blue"
    >
      <template v-slot:left>
        <div class="row items-center">
          <q-icon left name="done" /> 동명으로 검색
        </div>
      </template>
      <template v-slot:right>
        <div class="row items-center">
          아파트명으로 검색<q-icon right name="done" />
        </div>
      </template>

      <q-item :class="bgcolor">
        <q-item-section avatar>
          <q-icon color="white" name="sync_alt" />
        </q-item-section>
        <q-item-section
          style="color:white;"
          v-text="slideText"
        ></q-item-section>
      </q-item>
    </q-slide-item>
    <q-input
      square
      outlined
      v-model="searchAddr.keyword"
      label="주소 검색"
      @keyup.enter="searchAddress"
    >
      <template v-slot:append>
        <q-icon
          v-if="searchAddr.keyword !== ''"
          name="close"
          @click="searchAddr.keyword = ''"
          class="cursor-pointer"
        />
        <q-icon name="search" @click="searchAddress" class="cursor-pointer" />
      </template>
    </q-input>

    <button type="button" id="searchbtn" @click="searchAddress">
      <i class="fa fa-search"></i> 아이콘 머지
    </button>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: "SearchBar",

  props: {
    noData: {}
  },

  data() {
    return {
      slideText: "좌우로 밀어주세요!",
      bgcolor: "bg-orange",
      searchModel: null,
      searchAddr: {
        dealType: "",
        searchType: "",
        keyword: ""
      }
    };
  },
  methods: {
    searchAddress: function() {
      this.$emit("search-addr", this.searchAddr);
    },
    onLeft({ reset }) {
      this.slideText = "동명으로 검색";
      this.bgcolor = "bg-purple";
      this.searchAddr.searchType = "0";
      this.$q.notify("검색 타입 : '동'");
      this.finalize(reset);
    },

    onRight({ reset }) {
      this.slideText = "아파트명으로 검색";
      this.bgcolor = "bg-blue";
      this.searchAddr.searchType = "1";
      this.$q.notify("검색 타입 : '아파트'");
      this.finalize(reset);
    },

    finalize(reset) {
      reset();
    }
  },
  mounted() {
    this.searchAddr.searchType = "0";
    this.searchAddr.keyword = this.noData.keyword;
    this.searchAddr.searchType = this.noData.searchType;
  }
};
</script>

<style scoped></style>
