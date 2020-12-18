<template>
  <div class=" container fit row justify-center items-center content-center">
    <div class="items-center q-pa-lg" style="width: 700px;">
      <h6><strong>공지사항 수정</strong></h6>
      <q-form @submit="updateboard" class="q-gutter-md">
        <q-input
          bottom-slots
          v-model="article.writer"
          label="이름"
          counter
          style="width:100%"
          readonly
        >
          <template v-slot:prepend>
            <q-icon name="perm_identity"></q-icon>
          </template>
        </q-input>

        <q-input
          type="address"
          bottom-slots
          v-model="article.title"
          label="제목"
          counter
          style="width:100%"
          lazy-rules
          :rules="[val => (val && val.length > 0) || 'Please type something']"
        >
          <template v-slot:prepend>
            <q-icon name="title"></q-icon>
          </template>
          <template v-slot:append>
            <q-icon
              name="close"
              @click="title = ''"
              class="cursor-pointer"
            ></q-icon>
          </template>
        </q-input>
        <q-input
          v-model="article.content"
          filled
          clearable
          type="textarea"
          color="primary"
          style="white-space:preline"
          cols="200"
          rows="10"
          label="공지사항 내용을 입력해주세요."
          @keydown="processTextareaFill"
          @focus="processTextareaFill"
        />
        <q-btn style="margin-right: 10px;" color="primary" type="submit"
          >수 정</q-btn
        >
      </q-form>
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
      inputModel: "",
      textareaModel: "",

      inputFillCancelled: false,
      textareaFillCancelled: false,
      article: {
        writer: "",
        no: "",
        title: "",
        content: "",
        regtime: ""
      },
      upHere: false,

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
    },
    processInputFill(e) {
      if (e === void 0) {
        return;
      }

      if (e.keyCode === 27) {
        if (this.inputFillCancelled !== true) {
          this.inputFillCancelled = true;
        }
      } else if (e.keyCode === 9) {
        if (
          this.inputFillCancelled !== true &&
          this.inputShadowText.length > 0
        ) {
          stopAndPrevent(e);
          this.inputModel =
            (typeof this.inputModel === "string" ? this.inputModel : "") +
            this.inputShadowText;
        }
      } else if (this.inputFillCancelled === true) {
        this.inputFillCancelled = false;
      }
    },

    processTextareaFill(e) {
      if (e === void 0) {
        return;
      }

      if (e.keyCode === 27) {
        if (this.textareaFillCancelled !== true) {
          this.textareaFillCancelled = true;
        }
      } else if (e.keyCode === 9) {
        if (
          this.textareaFillCancelled !== true &&
          this.textareaShadowText.length > 0
        ) {
          stopAndPrevent(e);
          this.textareaModel =
            (typeof this.textareaModel === "string" ? this.textareaModel : "") +
            this.textareaShadowText;
        }
      } else if (this.textareaFillCancelled === true) {
        this.textareaFillCancelled = false;
      }
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
