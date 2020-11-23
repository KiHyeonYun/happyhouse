import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";
import naver from "vue-naver-maps";
import VueNaverMap from "vue-naver-map";
// import example from './module-example'

Vue.use(Vuex);
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
const MAP_URL = process.env.NAVER_CLIENT_ID;

Vue.use(naver, {
  clientID: MAP_URL,
  useGovAPI: false, //공공 클라우드 API 사용 (선택)
  subModules: "geocoder" // 서브모듈 (선택)
});

Vue.use(VueNaverMap, {
  key: MAP_URL /* your key, type: String */,
  libraries: ["geocoder"] /* type: Array */
});
/*
 * If not building with SSR mode, you can
 * directly export the Store instantiation;
 *
 * The function below can be async too; either use
 * async/await or return a Promise which resolves
 * with the Store instance.
 */

export default function(/* { ssrContext } */) {
  const Store = new Vuex.Store({
    state: {
      accessToken: null,
      userId: "",
      userName: ""
    },
    // plugins: [createPersistedState()],
    getters: {
      getAccessToken(state) {
        return state.accessToken;
      },
      getUserId(state) {
        return state.userId;
      },
      getUserName(state) {
        return state.userName;
      }
    },
    mutations: {
      LOGIN(state, payload) {
        state.accessToken = payload["auth-token"];
        state.userId = payload["user-id"];
        state.userName = payload["user-name"];
      },
      LOGOUT(state) {
        state.accessToken = null;
        state.userId = "";
        state.userName = "";
      }
    },
    actions: {
      LOGIN(context, user) {
        return axios
          .post(`${SERVER_URL}/member/confirm/login`, user)
          .then(response => {
            context.commit("LOGIN", response.data);
            axios.defaults.headers.common[
              "auth-token"
            ] = `${response.data["auth-token"]}`;
          });
      },
      LOGOUT(context) {
        context.commit("LOGOUT");
        axios.defaults.headers.common["auth-token"] = undefined;
      }
    },

    modules: {}

    // enable strict mode (adds overhead!)
    // for dev mode only
    //strict: process.env.DEBUGGING
  });

  return Store;
}
