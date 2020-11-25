<template>
  <q-layout view="hHh lpR fff">
    <!-- header 시작 -->
    <q-header reveal elevated class="bg-white text-black" height-hint="98">
      <q-toolbar style="padding-right:100px; padding-left:100px;">
        <!-- <q-btn dense flat round icon="menu" @click="left = !left" /> -->

        <q-toolbar-title>
          <img src="~assets/login-logo.png" @click="f_main" width="200px" />
        </q-toolbar-title>
        <div style="margin-right:40%">
          <q-btn
            class="hver"
            flat
            text-color="#666666"
            label="소개"
<<<<<<< HEAD
            style="font-weight:800;margin-right:30px"
=======
            style="font-weight:800; margin-right:30px"
>>>>>>> branch 'master' of https://github.com/KiHyeonYun/happyhouse.git
          >
          </q-btn>

          <q-btn
            class="hver"
            flat
            text-color="#666666"
            label="공지사항"
            style="font-weight:800;margin-right:30px"
          >
          </q-btn>
          <q-btn
            class="hver"
            flat
            text-color="#666666"
            label="질문과 답변"
            style="font-weight:800;"
          >
          </q-btn>
          <q-btn color="primary" flat style="font-weight:700;">
            <q-menu transition-show="jump-down" transition-hide="jump-up">
              <q-list style="min-width: 150px">
                <q-item clickable @click="mypage">
                  <q-item-section>My page</q-item-section>
                </q-item>
                <q-item clickable @click="logout">
                  <q-item-section>로그아웃</q-item-section>
                </q-item>
              </q-list>
            </q-menu>
          </q-btn>
        </div>
        <div class="row inline">
          <div v-if="!loginok">
            <q-btn
              class="hver"
              @click="login"
              flat
              text-color="#666666"
              label="로그인"
              style="font-weight:800;"
            ></q-btn>
            |
            <q-btn
              class="hver"
              flat
              @click="join"
              text-color="#666666"
              label="회원가입"
              style="font-weight:800;"
            ></q-btn>
          </div>
          <div v-else>
            <q-btn color="primary" flat :label="nim" style="font-weight:700;">
              <q-menu transition-show="jump-down" transition-hide="jump-up">
                <q-list style="min-width: 150px">
                  <q-item clickable @click="mypage">
                    <q-item-section>My page</q-item-section>
                  </q-item>
                  <q-item clickable @click="logout">
                    <q-item-section>로그아웃</q-item-section>
                  </q-item>
                </q-list>
              </q-menu>
            </q-btn>
          </div>
        </div>
      </q-toolbar>
    </q-header>
    <!-- 헤더 끝 -->

    <!-- 왼쪽 사이드바 시작 -->
    <!-- <q-drawer v-model="left" show-if-above bordered content-class="bg-grey-1">
      <q-list>
        <q-item-label header class="text-grey-8"> </q-item-label>
      </q-list>
    </q-drawer> -->
    <!-- 왼쪽 사이드바 끝 -->

    <!-- 중앙 child view 시작 -->
    <q-page-container>
      <router-view />
    </q-page-container>
    <!-- 중앙 child view 끝 -->

    <!-- 푸터 시작 -->
    <!-- <q-footer reveal elevated class="bg-grey-8 text-white">
      <q-toolbar>
        <q-toolbar-title>
          <q-avatar>
            <img
              src="https://cdn.quasar.dev/logo/svg/quasar-logo.svg"
            /> </q-avatar
          >Title
        </q-toolbar-title>
      </q-toolbar>
    </q-footer> -->
  </q-layout>
</template>

<script>
import axios from "axios";
import routes from "src/router/routes";
import { SessionStorage } from "quasar";

export default {
  name: "MainLayout",
  // components: { EssentialLink },
  data() {
    return {
      model: null,
      loginok: false,
      nim:
        SessionStorage.getItem("userId") +
        "(" +
        SessionStorage.getItem("userName") +
        ")님 환영합니다."
      //essentialLinks: linksData,
    };
  },
  mounted() {
    this.logincheck();
  },
  methods: {
    login() {
      this.$router.push("/login");
    },
    join() {
      this.$router.push("/join");
    },
    logincheck() {
      if (!SessionStorage.isEmpty()) {
        this.loginok = true;
        alert(this.loginok);
      } else {
        this.loginok = false;
      }
    },

    searchMove(searchAddr) {
      this.$router.push({ path: "/map/", params: { no: searchAddr } });
    },
    f_main() {
      alert("해피하우스에용~:->");
      location.href = "/";
      // this.$router.push("/");
    },
    mypage() {
      this.$router.push("/mypage");
    },
    logout() {
      SessionStorage.clear();
      this.loginok = false;
      location.href = "/";
    }
  }
};
</script>
<style scoped>
img:hover {
  cursor: pointer;
}
.hver:hover {
  color: #0c38f8;
}
</style>
