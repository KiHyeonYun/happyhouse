<template>
  <q-layout view="hHh lpR fff">
    <!-- header 시작 -->
    <q-header reveal elevated class="bg-primary text-white" height-hint="98">
      <q-toolbar>
        <q-btn dense flat round icon="menu" @click="left = !left" />

        <q-toolbar-title>
          <q-avatar>
            <img src="https://cdn.quasar.dev/logo/svg/quasar-logo.svg" />
          </q-avatar>
          Happy House
        </q-toolbar-title>

        <q-btn-toggle
          v-if="!loginok"
          align="right"
<<<<<<< HEAD
          toggle-color="primary"
          v-model="model"
          @click="login"
=======
          v-model="model"
          toggle-color="info"
>>>>>>> branch 'master' of https://github.com/KiHyeonYun/happyhouse.git
          flat
          :options="[
            { label: '로그인', value: 'login', to: '/login' },
            { label: '회원가입', value: 'join', to: '/join' }
          ]"
        ></q-btn-toggle>

        <q-btn v-else color="primary" label="회원님 반갑습니다.">
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
      </q-toolbar>
    </q-header>
    <!-- 헤더 끝 -->

    <!-- 왼쪽 사이드바 시작 -->
    <q-drawer v-model="left" show-if-above bordered content-class="bg-grey-1">
      <q-list>
<<<<<<< HEAD
        <q-item-label header class="text-grey-8">
          Memu
        </q-item-label>
        
=======
        <q-item-label header class="text-grey-8"> </q-item-label>
>>>>>>> branch 'master' of https://github.com/KiHyeonYun/happyhouse.git
      </q-list>
    </q-drawer>
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
import search from "components/map/search.vue";
import detail from "components/map/detail.vue";
import itemlist from "components/map/itemlist.vue";

export default {
  name: "MainLayout",
  // components: { EssentialLink },
  data() {
    return {
      model : 'login',
      left: false,
<<<<<<< HEAD
=======
      model: null,
      loginok: false
      //essentialLinks: linksData
>>>>>>> branch 'master' of https://github.com/KiHyeonYun/happyhouse.git
    };
  },
  mounted() {
    this.logincheck();
  },
  methods: {
    login() {
      this.$router.push("/login");
    },
    logincheck() {
      if (this.$store.state.accessToken != null) {
        this.loginok = true;
      } else {
        this.loginok = false;
      }
    },
    logout() {
      this.$router.push("/");
    },
    mypage() {
      this.$router.push("/mypage");
    }
  }
};
</script>
