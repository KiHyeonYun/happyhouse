<template>
  <div class="container ">
    <h3>글 정보</h3>
    <br />
    <table class="table">
      <tr>
        <td>글번호</td>
        <td v-html="article.no"></td>
      </tr>
      <tr>
        <td>작성자</td>
        <td v-html="article.writer"></td>
      </tr>
      <tr>
        <td>제목</td>
        <td><b-form-input v-model="article.title" /></td>
      </tr>
      <tr>
        <td>작성시간</td>
        <td v-html="article.regtime"></td>
      </tr>
      <tr>
        <td>내용</td>
        <td colspan="2">
          <b-textarea
            style="white-space:preline"
            cols="135"
            rows="5"
            v-model="article.content"
          ></b-textarea>
        </td>
      </tr>
      <tr></tr>
      <br />
    </table>

    <div class="container text-center">
      <b-button
        size="lg"
        pill
        variant="outline-success"
        v-on:click="updateboard"
        >수정</b-button
      >
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "SelectBoardByNo",
  props: ["no"],
  data() {
    return {
      upHere: false,
      article: {},
      loading: true,
      errored: false
    };
  },
  methods: {
    updateboard: function() {
      axios
        .put(
          process.env.VUE_APP_SERVER_URL + "/board/update/" + this.no,
          this.article
        )
        .then(response => {
          if (response.data == "success") {
            alert("수정 성공");
            location.href = "/BoardList";
          } else {
            alert("수정 실패");
          }
        });
    }
  },
  mounted() {
    axios
      .get(process.env.VUE_APP_SERVER_URL + "/board/detail/" + this.no)
      .then(response => (this.article = response.data))
      .catch(() => {
        this.errored = true;
      })
      .finally(() => (this.loading = false));
  } //
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
