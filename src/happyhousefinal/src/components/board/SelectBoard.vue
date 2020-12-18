<template>
  <div class=" container fit row justify-center items-center content-center">
    <div class="items-center q-pa-lg" style="width: 1400px;">
      <h6><strong>공지사항</strong></h6>
      <q-table
        :data="articles"
        :columns="columns"
        row-key="name"
        :pagination.sync="pagination"
        :filter="filter"
      >
        <template v-slot:header="props">
          <q-tr :props="props">
            <q-th auto-width />
            <q-th v-for="col in props.cols" :key="col.name" :props="props">
              <strong>{{ col.label }}</strong>
            </q-th>
          </q-tr>
        </template>

        <template v-slot:top>
          <q-btn
            v-show="admincheck"
            color="primary"
            :disable="loading"
            label="글쓰기"
            @click="insertBoard"
          />
          <q-space />
          <q-input
            borderless
            dense
            debounce="300"
            color="primary"
            v-model="filter"
            placeholder="search"
          >
            <template v-slot:append>
              <q-icon name="search" />
            </template>
          </q-input>
        </template>
        <!-- 상세내용 -->
        <template v-slot:body="props">
          <q-tr :props="props">
            <q-td auto-width>
              <q-btn
                size="sm"
                color="accent"
                round
                dense
                @click="props.expand = !props.expand"
                :icon="props.expand ? 'remove' : 'add'"
              />
            </q-td>
            <q-td v-for="col in props.cols" :key="col.name" :props="props">
              {{ col.value }}
            </q-td>
          </q-tr>
          <q-tr v-show="props.expand" :props="props">
            <q-td colspan="100%">
              <div class="text-left">
                <!--여기에 공지사항 이미지 들어갈거임-->
                {{ props.row.content }}
              </div>

              <div class="text-right" v-show="admincheck">
                <q-btn
                  style="margin-right: 10px;"
                  color="green"
                  @click="detailArticle(props.row.no)"
                  >수정</q-btn
                >
                <q-btn color="red" @click="deleteArticle(props.row.no)"
                  >삭제</q-btn
                >
              </div>
            </q-td>
          </q-tr>
        </template>
      </q-table>
    </div>
  </div>
</template>

<script>
import { SessionStorage } from "quasar";
import axios from "axios";
export default {
  name: "BoardList",
  data() {
    return {
      pagination: {
        sortBy: "desc",
        descending: false,
        page: 1,
        rowsPerPage: 10
        // rowsNumber: xx if getting data from a server
      },
      columns: [
        {
          name: "title",
          required: true,
          label: "제목",
          align: "left",
          field: row => row.title,
          format: val => `${val}`,
          sortable: true
        },
        {
          name: "writer",
          align: "center",
          label: "글쓴이",
          field: "writer",
          sortable: true
        },
        {
          name: "no",
          label: "번호",
          field: "no",
          sortable: true,
          style: "width: 10px"
        },
        {
          name: "regtime",
          label: "작성 시간",
          sortable: true,
          field: "regtime"
        }
      ],
      articles: [],
      filter: null,
      loading: true,
      errored: false,
      admincheck: SessionStorage.getItem("admincheck")
    };
  },
  methods: {
    isAdmin() {
      if (SessionStorage.getItem("userId") == "admin") {
        this.admincheck = true;
        SessionStorage.set("admincheck", true);
      }
    },
    insertBoard() {
      this.$router.push("/write");
    },
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
    }
  },
  mounted() {
    this.isAdmin();
    this.retrieveBoard();
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped></style>
