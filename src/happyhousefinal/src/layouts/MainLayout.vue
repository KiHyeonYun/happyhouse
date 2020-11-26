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
            @click="intro"
            label="소개"
            style="font-weight:800; margin-right:30px"
          >
          </q-btn>

          <q-btn
            class="hver"
            flat
            text-color="#666666"
            label="공지사항"
            style="font-weight:800;margin-right:30px"
            @click.prevent="goBoard"
          >
          </q-btn>
          <q-btn
            class="hver"
            flat
            text-color="#666666"
            label="질문과 답변"
            style="font-weight:800;"
            @click="chatOpen"
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
    <!-- <q-footer reveal elevated class="bg-white text-black">
      <q-toolbar>
        <div>
          <strong style="height=50px; font-size:15px;"
            >YunKihyeon & LeeSumin All rights reserved.</strong
          >
        </div>
        <p></p
      ></q-toolbar>
    </q-footer> -->
    <q-page-sticky position="top-right" :offset="[18, 18]">
      <q-btn round color="secondary" icon="maps_ugc" @click="chatOpen" />
      <chat v-show="chatview"></chat>
    </q-page-sticky>
  </q-layout>
</template>

<script>
import axios from "axios";
import routes from "src/router/routes";
import Chat from "src/pages/Chat.vue";
import { SessionStorage } from "quasar";

export default {
  name: "MainLayout",
  components: { Chat },
  data() {
    return {
      chatview: false,
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
    goBoard() {
      this.$router.push("/BoardList");
    },
    chatOpen() {
      if (this.chatview) this.chatview = false;
      else this.chatview = true;

      console.log(this.chatview);
    },
    login() {
      this.$router.push("/login");
    },
    join() {
      this.$router.push("/join");
    },
    intro() {
      this.$router.push("/intro");
    },
    logincheck() {
      if (!SessionStorage.isEmpty()) {
        this.loginok = true;
      } else {
        this.loginok = false;
      }
    },

    searchMove(searchAddr) {
      this.$router.push({ path: "/map/", params: { no: searchAddr } });
    },
    f_main() {
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
