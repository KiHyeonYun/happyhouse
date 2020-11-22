<template>
  <div class="flex absolute-center	">
    <div class="q-pa-md" style="max-width: 400px">
      <div class="q-pa-md"></div>
      <h6><strong>회원가입</strong></h6>
      <!-- 회원가입 폼 시작 -->
      <q-form @submit="onSubmit" @reset="onReset" class="q-gutter-md">
        <q-input
          bottom-slots
          v-model="member.userid"
          label="아이디"
          counter
          :dense="dense"
          lazy-rules
          :rules="[val => (val && val.length > 0) || 'Please type something']"
        >
          <template v-slot:prepend>
            <q-icon name="place"></q-icon>
          </template>
          <template v-slot:append>
            <q-icon
              name="close"
              @click="member.userid = ''"
              class="cursor-pointer"
            ></q-icon>
          </template>

          <template v-slot:hint>
            4글자 이상 12글자 이하로 입력해주세요.
          </template>
        </q-input>

        <!-- 비밀번호 입력창 -->
        <q-input
          bottom-slots
          type="password"
          v-model="member.userpwd"
          label="비밀번호"
          counter
          :dense="dense"
          lazy-rules
          :rules="[val => (val && val.length > 0) || 'Please type something']"
        >
          <template v-slot:prepend>
            <q-icon name="place"></q-icon>
          </template>
          <template v-slot:append>
            <q-icon
              name="close"
              @click="member.userpwd = ''"
              class="cursor-pointer"
            ></q-icon>
          </template>

          <template v-slot:hint>
            4글자 이상 12글자 이하로 입력해주세요.
          </template>
        </q-input>
        <!-- 비밀번호 입력창 끝 -->

        <!-- 비밀번호 확인창 시작 -->
        <q-input
          type="password"
          bottom-slots
          v-model="confirmpwd"
          label="비밀번호 확인"
          counter
          :dense="dense"
          lazy-rules
          :rules="[
            val => (val && val.length > 0) || 'Please type something',
            val => val == member.userpwd || '위 비밀번화와 다릅니다!'
          ]"
        >
          <template v-slot:prepend>
            <q-icon name="place"></q-icon>
          </template>
          <template v-slot:append>
            <q-icon
              name="close"
              @click="confirmpwd = ''"
              class="cursor-pointer"
            ></q-icon>
          </template>

          <template v-slot:hint>
            위 비밀번호와 동일하게 입력해주세요.
          </template>
        </q-input>
        <!-- 비밀번호 확인창 끝 -->

        <!-- 사용자 이름 입력창 끝 -->
        <q-input
          bottom-slots
          v-model="member.username"
          label="이름"
          counter
          :dense="dense"
          lazy-rules
          :rules="[val => (val && val.length > 0) || 'Please type something']"
        >
          <template v-slot:prepend>
            <q-icon name="place"></q-icon>
          </template>
          <template v-slot:append>
            <q-icon
              name="close"
              @click="member.username = ''"
              class="cursor-pointer"
            ></q-icon>
          </template>

          <template v-slot:hint>
            당신의 이름을 입력해주세요.
          </template>
        </q-input>
        <!-- 사용자 이름 입력창 끝 -->

        <!-- 사용자 이메일 입력 -->
        <q-input
          type="email"
          bottom-slots
          v-model="member.useremail"
          label="이메일"
          counter
          :dense="dense"
          lazy-rules
          :rules="[val => (val && val.length > 0) || 'Please type something']"
        >
          <template v-slot:prepend>
            <q-icon name="place"></q-icon>
          </template>
          <template v-slot:append>
            <q-icon
              name="close"
              @click="member.useremail = ''"
              class="cursor-pointer"
            ></q-icon>
          </template>

          <template v-slot:hint>
            이메일 형식으로 입력해주세요.
          </template>
        </q-input>
        <!-- 사용자 이메일 입력 끝 -->

        <!-- 사용자 주소 입력 -->
        <q-input
          type="address"
          bottom-slots
          v-model="member.useraddress"
          label="주소"
          counter
          :dense="dense"
          lazy-rules
          :rules="[val => (val && val.length > 0) || 'Please type something']"
        >
          <template v-slot:prepend>
            <q-icon name="place"></q-icon>
          </template>
          <template v-slot:append>
            <q-icon
              name="close"
              @click="member.useraddress = ''"
              class="cursor-pointer"
            ></q-icon>
          </template>

          <template v-slot:hint>
            주소를 입력해주세요.
          </template>
        </q-input>
        <!-- 사용자 주소 입력 끝 -->

        <q-toggle v-model="accept" label="가입 동의" />
        <div>
          <q-btn label="가입하기" type="submit" color="primary" />
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
  </div>
</template>

<script>
import Axios from "axios";
export default {
  data() {
    return {
      member: {
        userid: "",
        username: "",
        userpwd: "",
        useremail: "",
        useraddress: ""
      },
      confirmpwd: "",
      dense: false,
      accept: false
    };
  },
  methods: {
    onSubmit() {
      if (this.accept !== true) {
        this.$q.notify({
          color: "red-5",
          textColor: "white",
          icon: "warning",
          message: "가입 동의가 필요합니다."
        });
      } else {
        Axios.post(
          process.env.VUE_APP_SERVER_URL + "/member/regist",
          this.member
        )
          .then(Response => {
            alert(Response.data);
            if (Response.data.state == "success") {
              this.$q.notify({
                color: "green-4",
                textColor: "white",
                icon: "cloud_done",
                message: "회원가입 성공"
              });
              this.$router.push("/login");
            }
          })
          .catch(() => {
            this.$q.notify({
              color: "red-5",
              textColor: "white",
              icon: "warning",
              message: "이미 해당 아이디가 존재합니다."
            });
          });
      }
    },

    onReset() {
      this.userid = null;
      this.username = null;
      this.userpwd = null;
      this.confirmpwd = null;
      this.useremail = null;
      this.useraddress = null;
      this.accept = false;
    }
  }
};
</script>
<style scoped>
b-form-group {
  width: 100px;
}
</style>
