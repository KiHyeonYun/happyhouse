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
export default {
  name: "Login",
  data: function() {
    return {
      user: {
        userid: "",
        userpwd: ""
      },
      message: ""
    };
  },
  computed: {
    nextRoute() {
      return this.$route.params.nextRoute ? this.$route.params.nextRoute : "";
    }
  },
  methods: {
    login: function() {
      // LOGIN 액션 실행
      // 서버와 통신(axios)을 해 토큰값을 얻어야 하므로 Actions를 호출.
      this.$store
        .dispatch("LOGIN", this.user)
        .then(() => {
          this.$router.replace(`/${this.nextRoute}`);
          this.$q.notify({
            color: "green-4",
            textColor: "white",
            icon: "cloud_done",
            message: "로그인 성공"
          });
        })
        .catch(({ message }) => {
          this.msg = message;
          this.$q.notify({
            color: "red-5",
            textColor: "white",
            icon: "warning",
            message: this.msg
          });
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
