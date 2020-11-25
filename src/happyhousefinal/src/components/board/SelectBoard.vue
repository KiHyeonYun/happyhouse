<template>
  <div class="container">
    <h3>글 목록</h3>
    <br />

    <b-row>
      <b-col lg="2" class="my-1">
        <b-button variant="outline-success" href="/write">글쓰기</b-button>
      </b-col>
      <b-col lg="7" class="my-1">
        <b-form-group
          label-cols-sm="6"
          label-align-sm="right"
          label-size="sm"
          description="아무것도 선택하지 않으면 모든 컬럼에서 검색합니다."
          class="mb-0"
        >
          <b-form-checkbox-group v-model="filterOn" class="mt-1">
            <b-form-checkbox value="no">번호</b-form-checkbox>
            <b-form-checkbox value="writer">작성자</b-form-checkbox>
            <b-form-checkbox value="title">제목</b-form-checkbox>
            <b-form-checkbox value="content">내용</b-form-checkbox>
          </b-form-checkbox-group>
        </b-form-group>
      </b-col>

      <b-col lg="3" class="my-1">
        <b-form-group>
          <b-input-group size="sm">
            <b-form-input
              v-model="filter"
              type="search"
              id="filterInput"
              placeholder="Type to Search"
            ></b-form-input>
            <b-input-group-append>
              <b-button :disabled="!filter" @click="filter = ''"
                >Clear</b-button
              >
            </b-input-group-append>
          </b-input-group>
        </b-form-group>
      </b-col>
    </b-row>
    <b-table
      striped
      hover
      :items="articles"
      :fields="fields"
      :per-page="perPage"
      :current-page="currentPage"
      :filter="filter"
      :filter-included-fields="filterOn"
      @filtered="onFiltered"
    >
      <template #cell(show_details)="row">
        <b-button
          variant="outline-info"
          size="sm"
          @click="row.toggleDetails"
          class="mr-2"
        >
          {{ row.detailsShowing ? "Hide" : "Show" }} Detail
        </b-button>
      </template>

      <template #row-details="row">
        <b-card>
          <b-row class="mb-2">
            <b-col sm="3" class="text-lg-right"><b>내용 : </b></b-col>
          </b-row>
          <b-row class="mb-2">
            <b-col sm="3" class="text-sm-right"></b-col>
            <b-col>{{ row.item.content }}</b-col>
          </b-row>
          <b-row align-h="between">
            <b-col cols="auto" class="mr-auto p-3"
              ><b-button
                pill
                variant="outline-success"
                @click="detailArticle(row.item.no)"
                >수정</b-button
              ></b-col
            >
            <b-col cols="auto" class="p-3"
              ><b-button
                pill
                variant="outline-danger "
                @click="deleteArticle(row.item.no)"
                >삭제</b-button
              ></b-col
            >
          </b-row>
        </b-card>
      </template>
    </b-table>
    <br />
    <div class="container text-center">
      <b-pagination
        align="center"
        v-model="currentPage"
        pills
        :total-rows="totalRows"
        :per-page="perPage"
      ></b-pagination>
    </div>
  </div>
</template>

<script>
import axios from "axios";

// Optionally install the BootstrapVue icon components plugin

export default {
  name: "BoardList",
  data() {
    return {
      fields: [
        { key: "no", label: "글 번호", sortable: true },
        { key: "writer", label: "글쓴이", sortable: true },
        { key: "title", label: "제목", sortable: true },
        { key: "regtime", label: "쓴 시간", sortable: true },
        { key: "show_details", label: "자세히" }
      ],
      upHere: false,
      articles: [],
      totalRows: 1,
      perPage: 8,
      currentPage: 1,
      filter: null,
      filterOn: [],
      loading: true,
      errored: false
    };
  },
  methods: {
    detailArticle(did) {
      this.$router.push("/detail/" + did);
    },
    retrieveBoard() {
      axios
        .get(process.env.VUE_APP_SERVER_URL + "/board/retrieve")
        .then(response => {
          this.articles = response.data;
          this.totalRows = this.articles.length;
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => (this.loading = false));
    },
    deleteArticle(did) {
      ///deleteEmployee/
      alert(did + "를 삭제합니다.");
      axios
        .delete(process.env.VUE_APP_SERVER_URL + "/board/delete/" + did)
        .then(response => {
          if (response.data == "success") {
            alert("글을 삭제 했습니다.");
            this.retrieveBoard();
          } else {
            alert("글을 삭제하지 못했습니다.");
          }
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => (this.loading = false));
    },
    onFiltered(filteredItems) {
      // Trigger pagination to update the number of buttons/pages due to filtering
      this.totalRows = filteredItems.length;
      this.currentPage = 1;
    }
  },

  mounted() {
    this.retrieveBoard();
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
