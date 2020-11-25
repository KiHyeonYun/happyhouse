<template>
  <div class="container">
    <h3>글 쓰기</h3>
    <br />
    <div v-if="!submitted">
      <form
        action
        method="post"
        id="_frmForm"
        name="frmForm"
        @submit.prevent="writeArticle"
      >
        <table class="table">
          <tr>
            <th>작성자</th>
            <td>
              <b-form-select
                style="width:30%"
                v-model="writer"
                :options="writers"
              ></b-form-select>
            </td>
          </tr>
          <tr>
            <th>제목</th>
            <td>
              <b-form-input
                data-msg="제목"
                type="text"
                name="title"
                id="_title"
                size="20"
                v-model="title"
                style="width:30%"
              />
            </td>
          </tr>
          <tr>
            <th>내용</th>
            <td>
              <b-textarea
                style="white-space:preline"
                cols="50"
                rows="10"
                data-msg="내용"
                type="textarea"
                name="content"
                id="_content"
                v-model="content"
              />
            </td>
          </tr>
          <tr>
            <td colspan="2" style="height:50px; text-align:center;">
              <b-button
                size="lg"
                pill
                variant="outline-success"
                type="submit"
                name="button"
                >작성</b-button
              >
            </td>
          </tr>
        </table>
      </form>
    </div>

    <div v-else>
      <br />
      <h4 v-text="successMsg"></h4>
      <br />
      <b-button pill variant="outline-success" v-on:click="newWriteArticle">
        글 추가 작성
      </b-button>
      <b-button pill variant="outline-primary" @click="boardList">
        리스트로 돌아가기
      </b-button>
    </div>
  </div>
</template>
<script>
import axios from "axios";
export default {
  name: "InsertBoard",
  data() {
    return {
      info: null,
      loading: true,
      errored: false,
      writer: "",
      title: "",
      content: "",
      writers: [],
      successMsg: "",
      submitted: false
    };
  },
  mounted() {
    axios
      .get(process.env.VUE_APP_SERVER_URL + "/board/writerList")
      .then(response => (this.writers = response.data))
      .catch(() => {
        this.errored = true;
      })
      .finally(() => (this.loading = false));
  },
  methods: {
    writeArticle() {
      if (this.writer == "") {
        alert("작성자를 입력하세요.");
        return;
      }
      if (this.title == "") {
        alert("제목을 입력하세요.");
        return;
      }
      if (this.content == "") {
        alert("내용을 입력하세요.");
        return;
      }

      axios
        .post(process.env.VUE_APP_SERVER_URL + "/board/write", {
          writer: this.writer,
          title: this.title,
          content: this.content
        })
        .then(response => {
          if (response.data == "success") {
            this.successMsg = "글쓰기를 완료하였습니다!";
          } else {
            this.successMsg = "글쓰기를 실패하였습니다!";
          }
        });
      this.submitted = true;
    },
    newWriteArticle() {
      (this.submitted = false),
        (this.info = null),
        (this.loading = true),
        (this.errored = false),
        (this.writer = ""),
        (this.title = ""),
        (this.content = "");
    },

    boardList: function() {
      location.href = "/BoardList";
    }
  }
};
</script>

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
