// import store from "../store/index";
import MainLayout from "../layouts/MainLayout.vue";
import Mypage from "../pages/member/Mypage.vue";
import Login from "../pages/member/Login.vue";
import Join from "../pages/member/Join.vue";
import Index from "../pages/Index.vue";
import Mapview from "../pages/Mapview.vue";
import SelectBoard from "../components/board/SelectBoard.vue";
import SelectBoardByNo from "../components/board/SelectBoardByNo.vue";
import InsertBoard from "../components/board/InsertBoard.vue";

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
        path: "/write",
        name: "InsertBoard",
        component: InsertBoard
      },
      {
        path: "/detail/:no",
        name: "SelectBoardByNo",
        component: SelectBoardByNo,
        props: true
      },
      {
        path: "/BoardList",
        name: "BoardList",
        component: SelectBoard
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
    path: "/chat",
    component: () => import("pages/Chat.vue")
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
