import Vue from "vue";
import Vuex from "vuex";
import http from "@/util/http-commons.js";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    users: [],
  },
  getters: {
    users(state) {
      return state.users;
    },
  },
  mutations: {
    USERS(state, payload) {
      state.users = payload.users;
    },
  },
  actions: {
    getUsers(context) {
      http.get(`admin/list`).then((response) => {
        context.commit({
          type: "USERS",
          users: response.data,
        });
      });
    },
    modifyUser(context, payload) {
      http.put(`admin/list/${payload.user.id}`, payload.user)
        .then((response) => {
          payload.callback(response.status);
        })
        .catch((response) => {
          payload.callback(response.status);
        });
    }
  },
  modules: {},
});
