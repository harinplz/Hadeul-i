import Vue from "vue";
import Vuex from "vuex";
import http from "@/util/http-commons.js";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    users: [],
    attractions: [],
  },
  getters: {
    users(state) {
      return state.users;
    },
    attractions(state) {
      return state.attractions;
    },
  },
  mutations: {
    USERS(state, payload) {
      state.users = payload.users;
    },
    ATTRACTIONS(state, payload) {
      state.attractions = payload.attractions;
    },
  },
  actions: {
    getUsers(context) { //관리자가 회원 정보 가져오기
      http.get(`admin/list`).then((response) => {
        context.commit({
          type: "USERS",
          users: response.data,
        });
      });
    },
    createUser(context, payload) { // 관리자가 회원 정보 등록
      http.post(`admin/list`, payload.user)
        .then((response) => {
          console.log("응답: " + response.status)
          payload.callback(response.status);
      })
        .catch((response) => {
          payload.callback(response.status);
      })
    },
    modifyUser(context, payload) { //관리자가 회원 정보를 수정
      http.put(`admin/list/${payload.user.no}`, payload.user)
        .then((response) => {
          payload.callback(response.status);
        })
        .catch((response) => {
          payload.callback(response.status);
        });
    },
    deleteUser(context, payload) { //관리자가 회원 정보를 삭제
      http.delete(`admin/list/${payload.id}`)
        .then((response) => {
          payload.callback(response.status);
        })
        .catch((response) => {
          payload.callback(response.status);
        });
    },
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
