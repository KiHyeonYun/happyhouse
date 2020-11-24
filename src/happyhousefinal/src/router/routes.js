import store from "../store";
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
    component: () => import("layouts/MainLayout.vue"),
    children: [
      {
        path: "",
        component: () => import("pages/Index.vue")
      },
      {
        path: "/mypage",
        component: () => import("pages/member/Mypage.vue"),
        beforeEnter: requireAuth()
      },
      {
        path: "/login/:nextRoute",
        component: () => import("pages/member/Login.vue")
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
    component: () => import("pages/member/Login.vue")
  },
  {
    path: "/join",
    name: "Join",
    component: () => import("pages/member/join.vue")
  },
  {
    path: "/map/",
    name: "Map",
    component: () => import("pages/map.vue"),
    props: true,
  },
];

export default routes;
