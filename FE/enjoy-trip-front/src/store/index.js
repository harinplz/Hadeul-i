import Vue from "vue";
import Vuex from "vuex";
import http from "@/util/http-commons.js";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    attractions: [],
  },
  getters: {
    attractions(state) {
      return state.attractions;
    },
  },
  mutations: {
    ATTRACTIONS(state, payload) {
      state.attractions = payload.attractions;
    },
  },
  actions: {
    attractionSearch(context, payload) {
      http.post("/trips", payload.attractionSearchInfo).then((response) => {
        console.log("서버 응답이 왔습니다", response.data);
        // console.log(payload.attractionSearchInfo);
        //뮤테이션 호출 -> 동기화
        context.commit({
          type: "ATTRACTIONS",
          attractions: response.data,
        });
      });
    },
  },
  modules: {},
});
