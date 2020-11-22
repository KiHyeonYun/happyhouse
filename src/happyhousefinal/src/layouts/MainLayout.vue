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
          v-model="model"
          toggle-color="primary"
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
        <EssentialLink
          v-for="link in essentialLinks"
          :key="link.title"
          v-bind="link"
        />
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
import EssentialLink from "components/EssentialLink.vue";

const linksData = [
  {
    title: "Docs",
    caption: "quasar.dev",
    icon: "school",
    link: "https://quasar.dev"
  },
  {
    title: "Github",
    caption: "github.com/quasarframework",
    icon: "code",
    link: "https://github.com/quasarframework"
  },
  {
    title: "Discord Chat Channel",
    caption: "chat.quasar.dev",
    icon: "chat",
    link: "https://chat.quasar.dev"
  },
  {
    title: "Forum",
    caption: "forum.quasar.dev",
    icon: "record_voice_over",
    link: "https://forum.quasar.dev"
  },
  {
    title: "Twitter",
    caption: "@quasarframework",
    icon: "rss_feed",
    link: "https://twitter.quasar.dev"
  },
  {
    title: "Facebook",
    caption: "@QuasarFramework",
    icon: "public",
    link: "https://facebook.quasar.dev"
  },
  {
    title: "Quasar Awesome",
    caption: "Community Quasar projects",
    icon: "favorite",
    link: "https://awesome.quasar.dev"
  }
];

export default {
  name: "MainLayout",
  components: { EssentialLink },
  data() {
    return {
      left: false,
      essentialLinks: linksData
    };
  },
  methods: {
    login() {
      this.$router.push("/login");
    }
  }
};
</script>
