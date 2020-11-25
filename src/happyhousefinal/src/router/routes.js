// import store from "../store/index";
import MainLayout from "../layouts/MainLayout.vue";
import Mypage from "../pages/member/Mypage.vue";
import Login from "../pages/member/Login.vue";
import Join from "../pages/member/Join.vue";
import Index from "../pages/Index.vue";
import Mapview from "../pages/Mapview.vue";
import Intro from "../pages/Intro.vue";
import { SessionStorage } from "quasar";

const requireAuth = () => (to, from, next) => {
  const nextRoute = to.path;

  if (!SessionStorage.isEmpty()) {
    return next();
  } else {
    next("/login" + nextRoute);
  }
};

const routes = [
  {
    path: "/",
    component: MainLayout,
    children: [
      {
        path: "",
        component: Index
      },
      {
        path: "/mypage",
        component: Mypage,
        beforeEnter: requireAuth()
      },
      {
        path: "/mapview",
        name: "Map",
        component: Mapview,
        props: true
      },
      {
        path: "/intro",
        component: Intro
      }
    ]
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: "*",
    component: () => import("pages/Error404.vue")
  },
  {
    path: "/login",
    component: Login
  },
  {
    path: "/login/:nextRoute",
    component: Login
  },
  {
    path: "/join",
    name: "Join",
    component: Join
  }
];

export default routes;
