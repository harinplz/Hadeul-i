import Vue from "vue";
import Vuex from "vuex";
import http from "@/util/http-commons.js";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    users: [],
    attractions: [],
    communities: [],
    community: {},
  },
  getters: {
    users(state) {
      return state.users;
    },
    attractions(state) {
      return state.attractions;
    },
    communities(state) {
      return state.communities;
    },
    community(state) {
      return state.community;
    },
  },
  mutations: {
    USERS(state, payload) {
      state.users = payload.users;
    },
    ATTRACTIONS(state, payload) {
      state.attractions = payload.attractions;
    },
    COMMUNITIES(state, payload) {
      state.communities = payload.communities;
    },
    COMMUNITY(state, payload) {
      state.community = payload.community;
    },
  },
  actions: {
    getUsers(context) {
      //관리자가 회원 정보 가져오기
      http.get(`admin/list`).then((response) => {
        context.commit({
          type: "USERS",
          users: response.data,
        });
      });
    },
    createUser(context, payload) {
      // 관리자가 회원 정보 등록
      http
        .post(`admin/list`, payload.user)
        .then((response) => {
          console.log("응답: " + response.status);
          payload.callback(response.status);
        })
        .catch((response) => {
          payload.callback(response.status);
        });
    },
    modifyUser(context, payload) {
      //관리자가 회원 정보를 수정
      http
        .put(`admin/list/${payload.user.no}`, payload.user)
        .then((response) => {
          payload.callback(response.status);
        })
        .catch((response) => {
          payload.callback(response.status);
        });
    },
    deleteUser(context, payload) {
      //관리자가 회원 정보를 삭제
      http
        .delete(`admin/list/${payload.id}`)
        .then((response) => {
          payload.callback(response.status);
        })
        .catch((response) => {
          payload.callback(response.status);
        });
    },
    attractionSearch(context, payload) {
      http.post("/trips", payload.attractionSearchInfo).then((response) => {
        //뮤테이션 호출 -> 동기화
        context.commit({
          type: "ATTRACTIONS",
          attractions: response.data,
        });
      });
    },
    // 회원 로그인
    userLogin(context, payload) {
      console.log(payload);
      http
        .post("/user/login", payload.user)
        .then((response) => {
          localStorage.setItem("login", payload.user.id);
          payload.callback(response.status);
        })
        .catch((response) => {
          payload.callback(response.status);
        });
    },
    // 커뮤니티 글 불러오기
    getCommunities(context) {
      http.get(`community/list`).then((response) => {
        context.commit({
          type: "COMMUNITIES",
          communities: response.data,
        });
      });
    },
    //커뮤니티 글 작성
    createCommunity(context, payload) {
      http
        .post("community/", payload.community)
        .then((response) => {
          payload.callback(response.status);
        })
        .catch((response) => {
          payload.callback(response.status);
        });
    },
    //커뮤니티 글 상세 조회
    getCommunity(context, payload) {
      http.get(`community/${payload.boardNo}`).then((response) => {
        context.commit({
          type: "COMMUNITY",
          community: response.data,
        });
      });
    },
    //커뮤니티 글 수정
    modifyCommunity(context, payload) {
      http
        .put(`community/`, payload.community)
        .then((response) => {
          payload.callback(response.status);
        })
        .catch((response) => {
          payload.callback(response.status);
        });
    },
    //커뮤니티 글 삭제
    deleteCommunity(context, payload) {
      http
        .delete(`community/${payload.boardNo}`)
        .then((response) => {
          payload.callback(response.status);
        })
        .catch((response) => {
          payload.callback(response.status);
        });
    },
  },
  modules: {},
});
