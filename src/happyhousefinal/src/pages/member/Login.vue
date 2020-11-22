<template>
  <div class="q-pa-md" style="max-width: 400px">
    <q-form @submit="login" @reset="onReset" class="q-gutter-md">
      <q-input
        filled
        v-model="user.userid"
        label="Your id"
        lazy-rules
        :rules="[val => (val && val.length > 0) || 'Please type something']"
      />

      <q-input
        filled
        type="passowrd"
        v-model="user.userpwd"
        label="your password"
        lazy-rules
        :rules="[
          val => (val !== null && val !== '') || 'Please type your password'
        ]"
      />
      <div>
        <q-btn label="로그인" type="submit" color="primary" />
        <q-btn
          label="초기화"
          type="reset"
          color="primary"
          flat
          class="q-ml-sm"
        />
      </div>
    </q-form>
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
        .then(() => this.$router.replace(`/${this.nextRoute}`))
        .catch(({ message }) => (this.msg = message));
    },
    onReset: function() {
      this.userid = null;
      this.userpwd = null;
    }
  }
};
</script>

<style scope>
#login-div {
  text-align: center;
}
</style>
