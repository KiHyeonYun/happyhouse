<template>
  <div class="home">
    <input type="hidden" name="act" id="act" value="search" />
    <select
      id="dealType"
      name="dealType"
      style="display: none;"
      v-model="searchAddr.dealType"
    >
      <option value="1" selected></option>
      <option value="2"></option>
      <option value="3"></option>
      <option value="4"></option>
    </select>
    <select
      class="custom-select"
      id="searchType"
      name="searchType"
      v-model="searchAddr.searchType"
    >
      <option value="0" selected>동으로 검색</option>
      <option value="1">건물명으로 검색</option>
      <option value="2">역이름으로 검색</option>
    </select>

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
