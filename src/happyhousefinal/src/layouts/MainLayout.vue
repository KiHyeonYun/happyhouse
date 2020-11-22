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
          align="right"
          toggle-color="primary"
          v-model="model"
          @click="login"
          flat
          :options="[{ label: '로그인', value: 'login' }]"
        ></q-btn-toggle>
      </q-toolbar>
    </q-header>
    <!-- 헤더 끝 -->

    <!-- 왼쪽 사이드바 시작 -->
    <q-drawer v-model="left" show-if-above bordered content-class="bg-grey-1">
      <q-list>
        <q-item-label header class="text-grey-8">
          Memu
        </q-item-label>
        
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
    };
  },
  methods: {
    login() {
      this.$router.push("/login");
    }
  }
};
</script>
