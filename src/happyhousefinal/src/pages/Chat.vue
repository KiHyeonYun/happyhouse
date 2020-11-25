<template>
  <div class="q-pa-md row justify-center">
    <q-page-sticky
      class="shadow-box shadow-9"
      style="z-index : 5; background-color: #FFFFFF; border-radius: 10px;"
      position="top-right"
      :offset="[10, -20]"
    >
      <div>
        <q-input
          label="무엇이든 물어보세요!"
          outlined
          v-model="message"
          @keyup.enter="putChat"
        >
          <template v-slot:append>
            <q-btn round flat icon="send" />
          </template>
        </q-input>
      </div>
      <div
        class="overflow-auto"
        style="height: 350px; width: 400px; max-width: 500px"
      >
        <q-chat-message
          v-for="(chat, index) in chatList"
          :key="index"
          :name="chat.userid"
          :avatar="chat.avatar"
          :[chat.send]="true"
          :text="[chat.message]"
          :bg-color="chat.color"
        />
      </div>
    </q-page-sticky>
  </div>
</template>
<script>
import axios from "axios";
import { SessionStorage } from "quasar";

export default {
  data() {
    return {
      chatList: null,
      dense: false,
      tmp: "sent",
      messageColor: "amber-7",
      message: "",
      chatUrl: "https://cdn.quasar.dev/img/avatar3.jpg"
    };
  },

  methods: {
    getChat() {
      axios
        .get(
          process.env.VUE_APP_SERVER_URL +
            "/chat/" +
            SessionStorage.getItem("userId")
        )
        .then(response => {
          this.chatList = response.data;
          console.log(this.chatList);
        })
        .catch(error => {
          this.$q.notify({
            type: "negative",
            message: "데이터 로딩 실패 : " + error,
            position: "top"
          });
        });
    },
    putChat() {
      console.log(sessionStorage.getItem("userId"));
      if (this.message == "") {
        this.$q.notify({
          type: "negative",
          message: "질문을 입력해주세요. : " + error,
          position: "top"
        });
      } else {
        axios
          .post(process.env.VUE_APP_SERVER_URL + "/chat", {
            message: this.message,
            userid: SessionStorage.getItem("userId")
          })
          .then(response => {
            this.message = "";
            this.getChat();
          })
          .catch(error => {
            this.$q.notify({
              type: "negative",
              message: "데이터 전달 : " + error,
              position: "top"
            });
          });
      }
    }
  },

  mounted() {
    setInterval(() => {
      this.getChat();
    }, 100000);
  }
};
</script>
<style scoped>
.chat {
  background-color: #67f5b5;
}
</style>
