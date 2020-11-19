import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";

Vue.use(Vuex);

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default new Vuex.Store({
  state: {
    accessToken: null,
    member_id: "",
    member_name: ""
  },
  getters: {
    getAccessToken(state) {
      return state.accessToken;
    },
    getMemberId(state) {
      return state.member_id;
    },
    getMemberName(state) {
      return state.member_name;
    }
  },
  mutations: {
    LOGIN(state, payload) {
      state.accessToken = payload["auth-token"];
      state.member_id = payload["member_id"];
      state.member_name = payload["member_name"];
    },
    LOGOUT(state) {
      state.accessToken = null;
      state.member_id = "";
      state.member_name = "";
    }
  },
  actions: {
    LOGIN(context, member) {
      return axios
        .post(`${SERVER_URL}/member/confirm/login`, member)
        .then((response) => {
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
});
