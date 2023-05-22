import Vue from "vue";
import Vuex from "vuex";
import http from "@/util/http-commons.js";
import jwtDecode from "jwt-decode";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    hotplaces: [],
    users: [],
    attractions: [],
    communities: [],
    community: {},
    accessToken: null,
    refreshToken: null,
    userInfo: null,
  },
  getters: {
    hotplaces(state) {
      return state.hotplaces;
    },
    userInfo(state) {
      return state.userInfo;
    },
    accessToken(state) {
      return state.accessToken;
    },
    refreshToken(state) {
      return state.refreshToken;
    },
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
    HOTPLACES(state, payload) {
      state.hotplaces = payload.hotplaces;
    },
    LOGOUT(state) {
      state.accessToken = null;
      state.refreshToken = null;
      state.userInfo = null;
    },
    USER_INFO(state, payload) {
      state.userInfo = payload.userInfo;
    },
    TOKEN(state, payload) {
      state.accessToken = payload.accessToken;
      state.refreshToken = payload.refreshToken;
    },
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
    // 핫플레이스
    // 핫플레이스 검색
    searchHotplaces({ commit }, payload) {
      http
        .post("/hotplace/search", payload.searchCondition)
        .then(({ status, data }) => {
          if (status == 200) {
            commit({
              type: "HOTPLACES",
              hotplaces: data,
            });
          }
        });
    },
    // 핫플레이스 글 모두 조회
    getHotplaces(context) {
      http.get(`hotplace/`).then((response) => {
        context.commit({
          type: "HOTPLACES",
          hotplaces: response.data,
        });
      });
    },
    createHotplace(context, payload) {
      http
        .post("hotplace/", payload.frm, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then((response) => {
          console.log(payload.hotplace);
          console.log(payload.frm);
          payload.callback(response.status);
        })
        .catch((response) => {
          payload.callback(response.status);
        });
    },
    getAccessToken({ commit }, payload) {
      http
        .post(`/user/refresh`, payload.refreshToken)
        .then(({ status, data }) => {
          if (status == 200) {
            commit("TOKEN", {
              accessToken: data["access-token"],
              refreshToken: data["refresh-token"],
            });
          }
          payload.callback(status);
        })
        .catch((error) => {
          payload.callback(error.response.status);
        });
    },
    logout({ commit }) {
      commit("LOGOUT");
    },
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
    login({ commit }, payload) {
      console.log(payload);
      http
        .post("/user/login", payload.user)
        .then(({ status, data }) => {
          //localStorage.setItem("login", payload.user.id);
          if (status == 200) {
            // 서버로 부터 토크 전달받음
            console.log(data);
            commit("TOKEN", {
              accessToken: data["access-token"],
              refreshToken: data["refresh-token"],
            });
          }
          payload.callback(status);
        })
        .catch((response) => {
          payload.callback(response.status);
        });
    },
    async getUserInfo({ commit }, payload) {
      const decodeToken = jwtDecode(payload.accessToken);
      const config = {
        headers: {
          "access-token": payload.accessToken,
        },
      };

      http
        .get(`user/info/${decodeToken.userid}`, config)
        .then(({ status, data }) => {
          if (status == 200) {
            console.log(data.userInfo);
            commit("USER_INFO", {
              userInfo: data.userInfo,
            });
          }
          payload.callback(status);
        })
        .catch((error) => {
          payload.callback(error.response.status);
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
