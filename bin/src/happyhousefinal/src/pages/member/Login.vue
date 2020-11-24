<template>
  <div class="flex absolute-center">
    <div class="q-pa-md" style="width: 400px;">
      <q-form @submit="login" @reset="onReset" class="q-gutter-md">
        <img
          src="~assets/login-logo.png"
          @click="main"
          width="300px"
          style="margin-left:35px;"
        />
        <q-input
          outlined
          filled
          v-model="user.userid"
          label="   아이디"
          lazy-rules
          :rules="[val => (val && val.length > 0) || 'Please type something']"
        />

        <q-input
          outlined
          filled
          type="password"
          v-model="user.userpwd"
          label="   비밀번호"
          lazy-rules
          :rules="[
            val => (val !== null && val !== '') || 'Please type your password'
          ]"
        />
        <div style="text-align:center;">
          <q-btn
            label="로그인"
            type="submit"
            color="primary"
            style="width: 130px;"
          />
          <q-btn
            label="초기화"
            type="reset"
            color="primary"
            class="q-ml-sm"
            style="width: 130px;"
          />
        </div>
        <br />
        <div style="text-align:center">
          <a href="#">아이디 찾기</a>
          <a class="q-ml-sm" href="#">비밀번호 찾기</a>
          <a class="q-ml-sm" href="/join">회원가입</a>
        </div>
      </q-form>
    </div>
  </div>
</template>

<script>
import { SessionStorage } from "quasar";
import axios from "axios";
export default {
  name: "Login",
  data: function() {
    return {
      user: {
        userid: "",
        userpwd: ""
      },
      message: "",
      msge: ""
    };
  },
  computed: {
    nextRoute() {
      return this.$route.params.nextRoute ? this.$route.params.nextRoute : "";
    }
  },
  methods: {
    login: function() {
      axios
        .post(
          process.env.VUE_APP_SERVER_URL + "/member/confirm/login",
          this.user
        )
        .then(response => {
          if (response.data["status"] == "success") {
            SessionStorage.set("accessToken", `${response.data["auth-token"]}`);
            SessionStorage.set("userId", `${response.data["user-id"]}`);
            SessionStorage.set("userName", `${response.data["user-name"]}`);
            this.$q.notify({
              color: "green-4",
              textColor: "white",
              icon: "cloud_done",
              message: "로그인 성공"
            });
            this.$router.push("/");
          } else {
            this.$q.notify({
              color: "red-5",
              textColor: "white",
              icon: "warning",
              message: "로그인 실패"
            });
          }
        });
    },
    onReset: function() {
      this.user.userid = "";
      this.user.userpwd = "";
    },
    main: function() {
      this.$router.push("/");
    }
  }
};
</script>

<style scope>
#login-div {
  text-align: center;
}
a:hover {
  text-decoration: underline;
  color: black;
}
a {
  text-decoration: none;
  color: black;
}

img:hover {
  cursor: pointer;
}
</style>
