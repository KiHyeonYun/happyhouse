<template>
  <div>
    <template class="row justify-center">
      <div class="row  justify-center">
        <h6><strong>My page</strong></h6>
      </div>
    </template>

    <div class="q-pa-md">
      <div class="row justify-center">
        <div class="col-12 col-md-5">
          <q-form @submit="onSubmit" class="q-gutter-md">
            <q-input
              readonly
              bottom-slots
              v-model="user.userid"
              label="아이디"
              counter
              style="width: 100%"
            >
              <template v-slot:prepend>
                <q-icon name="perm_identity"></q-icon>
              </template>
            </q-input>

            <!-- 비밀번호 입력창 -->
            <q-input
              bottom-slots
              type="password"
              v-model="user.userpwd"
              label="비밀번호"
              counter
              :dense="dense"
              style="width: 100%"
              lazy-rules
              :rules="[
                val => (val && val.length > 0) || 'Please type something'
              ]"
            >
              <template v-slot:prepend>
                <q-icon name="lock"></q-icon>
              </template>
              <template v-slot:append>
                <q-icon
                  name="close"
                  @click="user.userpwd = ''"
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
              style="width: 100%"
              lazy-rules
              :rules="[
                val => (val && val.length > 0) || 'Please type something',
                val => val == user.userpwd || '위 비밀번화와 다릅니다!'
              ]"
            >
              <template v-slot:prepend>
                <q-icon name="lock"></q-icon>
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
              v-model="user.username"
              label="이름"
              counter
              :dense="dense"
              lazy-rules
              style="width: 100%"
              :rules="[
                val => (val && val.length > 0) || 'Please type something'
              ]"
            >
              <template v-slot:prepend>
                <q-icon name="account_circle"></q-icon>
              </template>
              <template v-slot:append>
                <q-icon
                  name="close"
                  @click="user.username = ''"
                  class="cursor-pointer"
                ></q-icon>
              </template>

              <template v-slot:hint> 당신의 이름을 입력해주세요. </template>
            </q-input>
            <!-- 사용자 이름 입력창 끝 -->

            <!-- 사용자 이메일 입력 -->
            <q-input
              type="email"
              bottom-slots
              v-model="user.useremail"
              label="이메일"
              counter
              :dense="dense"
              style="width: 100%"
              lazy-rules
              :rules="[
                val => (val && val.length > 0) || 'Please type something'
              ]"
            >
              <template v-slot:prepend>
                <q-icon name="email"></q-icon>
              </template>
              <template v-slot:append>
                <q-icon
                  name="close"
                  @click="user.useremail = ''"
                  class="cursor-pointer"
                ></q-icon>
              </template>

              <template v-slot:hint> 이메일 형식으로 입력해주세요. </template>
            </q-input>
            <!-- 사용자 이메일 입력 끝 -->

            <!-- 사용자 주소 입력 -->
            <q-input
              type="address"
              bottom-slots
              v-model="user.useraddress"
              label="주소"
              counter
              style="width: 100%"
              :dense="dense"
              lazy-rules
              :rules="[
                val => (val && val.length > 0) || 'Please type something'
              ]"
            >
              <template v-slot:prepend>
                <q-icon name="place"></q-icon>
              </template>
              <template v-slot:append>
                <q-icon
                  name="close"
                  @click="user.useraddress = ''"
                  class="cursor-pointer"
                ></q-icon>
              </template>

              <template v-slot:hint> 주소를 입력해주세요. </template>
            </q-input>
            <div>
              <q-btn label="수정 완료" type="submit" color="primary" />
              <q-btn
                label="회원 탈퇴"
                color="primary"
                type="submit"
                class="q-ml-sm"
              />
            </div>
          </q-form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { SessionStorage } from "quasar";

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  data() {
    return {
      user: {
        userid: "",
        username: "",
        userpwd: "",
        useraddress: "",
        useremail: ""
      },
      confirmpwd: "",
      dense: false
    };
  },
  created() {
    axios
      .get(`${SERVER_URL}/member/info`, {
        headers: {
          "auth-token": SessionStorage.getItem("accessToken")
        }
      })
      .then(response => {
        this.user = response.data.user;
      })
      .catch(() => {
        this.$store.dispatch("LOGOUT").then(() => this.$router.replace("/"));
      });
  },
  methods: {
    onSubmit() {
      axios
        .put(process.env.VUE_APP_SERVER_URL + "/member/update", this.user)
        .then(Response => {
          if (Response.data.state == "success") {
            SessionStorage.set("userName", this.user.username);
            this.$q.notify({
              color: "green-4",
              textColor: "white",
              icon: "cloud_done",
              message: "수정 성공"
            });
            location.href = "/";
          }
        })
        .catch(() => {
          this.$q.notify({
            color: "red-5",
            textColor: "white",
            icon: "warning",
            message: "수정안됐어용..."
          });
        });
    }
  }
};
</script>

<style></style>
