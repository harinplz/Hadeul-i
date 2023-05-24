import Vue from "vue";
import Vuex from "vuex";
import http from "@/util/http-commons.js";
import jwtDecode from "jwt-decode";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    hotplaces: [],
    hotplace: null,
    hotplaceLike: 0,
    hotplaceLikeCheck: 0,
    user: null,
    users: [],
    attractions: [],
    communities: [],
    community: {},
    accessToken: null,
    refreshToken: null,
    userInfo: null,
    routeAttractions: [],
    travelRoutes: [],
    travelRoute: {},
    travelRouteLike: 0,
    travelRouteLikeCheck: 0,
  },
  getters: {
    hotplaceLikeCheck(state) {
      return state.hotplaceLikeCheck;
    },
    hotplaceLike(state) {
      return state.hotplaceLike;
    },
    hotplace(state) {
      return state.hotplace;
    },
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
    user(state) {
      return state.user;
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
    routeAttractions(state) {
      return state.routeAttractions;
    },
    travelRoutes(state) {
      return state.travelRoutes;
    },
    travelRoute(state) {
      return state.travelRoute;
    },
    travelRouteLike(state) {
      return state.travelRouteLike;
    },
    travelRouteLikeCheck(state) {
      return state.travelRouteLikeCheck;
    },
  },
  mutations: {
    HOTPLACE_LIKE_CHECK(state, payload) {
      state.hotplaceLikeCheck = payload.hotplaceLikeCheck;
    },
    HOTPLACE_LIKE(state, payload) {
      state.hotplaceLike = payload.hotplaceLike;
    },
    HOTPLACE(state, payload) {
      state.hotplace = payload.hotplace;
    },
    HOTPLACES(state, payload) {
      state.hotplaces = payload.hotplaces;
    },
    LOGOUT(state) {
      state.accessToken = null;
      state.refreshToken = null;
      state.userInfo = null;
      state.hotplaceLikeCheck = 0;
    },
    USER_INFO(state, payload) {
      state.userInfo = payload.userInfo;
    },
    TOKEN(state, payload) {
      state.accessToken = payload.accessToken;
      state.refreshToken = payload.refreshToken;
    },
    USER(state, payload) {
      state.user = payload.user;
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
    ROUTE_ATTRACTIONS(state, payload) {
      state.routeAttractions = payload.routeAttractions;
    },
    TRAVEL_ROUTES(state, payload) {
      state.travelRoutes = payload.travelRoutes;
    },
    TRAVEL_ROUTE(state, payload) {
      state.travelRoute = payload.travelRoute;
    },
    TRAVEL_ROUTE_LIKE(state, payload) {
      state.travelRouteLike = payload.travelRouteLike;
    },
    TRAVEL_ROUTE_LIKE_CHECK(state, payload) {
      state.travelRouteLikeCheck = payload.travelRouteLikeCheck;
    },
  },
  actions: {
    // 여행 계획 action입니다. //
    getRouteAttractions(context, payload) {
      http.get(`trips/${payload.searchTitle}`).then((response) => {
        context.commit({
          type: "ROUTE_ATTRACTIONS",
          routeAttractions: response.data,
        });
      });
    },
    createTravelRoute(context, payload) {
      http
        .post("/route", payload.travelRoutes)
        .then((response) => {
          payload.callback(response.status);
        })
        .catch((response) => {
          payload.callback(response.status);
        });
    },
    getTravelRoutes(context) {
      http.get("/routes").then((response) => {
        context.commit({
          type: "TRAVEL_ROUTES",
          travelRoutes: response.data,
        });
      });
    },
    getTravelRoute(context, payload) {
      http.get(`/route/${payload.travelRouteNo}`).then((response) => {
        context.commit({
          type: "TRAVEL_ROUTE",
          travelRoute: response.data,
        });
      });
    },
    // 여행 계획 좋아요 구현
    // 여행 계획 좋아요 수 조회
    getTravelRouteLike(context, payload) {
      http.get(`route/like/${payload.travelRouteNo}`).then((response) => {
        context.commit({
          type: "TRAVEL_ROUTE_LIKE",
          travelRouteLike: response.data,
        });
      });
    },
    // 여행 계획 좋아요 했는지 확인
    getTravelRouteLikeCheck(context, payload) {
      http
        .post("route/like/check", payload.travelRouteLike)
        .then((response) => {
          context.commit({
            type: "TRAVEL_ROUTE_LIKE_CHECK",
            travelRouteLikeCheck: response.data,
          });
        });
    },
    // 여행 계획 좋아요 누르기
    createTravelRouteLike(context, payload) {
      http
        .post("route/like", payload.travelRouteLike)
        .then((response) => {
          payload.callback(response.status);
        })
        .catch((response) => {
          payload.callback(response.status);
        });
    },
    // 여행 계획 좋아요 취소
    deleteTravelRouteLike(context, payload) {
      console.log(payload);
      http
        .delete(
          `route/like/${payload.travelRouteLike.travelRouteNo}/${payload.travelRouteLike.userNo}`
        )
        .then((response) => {
          payload.callback(response.status);
        })
        .catch((response) => {
          payload.callback(response.status);
        });
    },
    // 관광지로 추가
    createUserAttraction(context, payload) {
      http
        .post(`hotplace/addAttraction`, payload.hotplace)
        .then((response) => {
          payload.callback(response.status);
        })
        .catch((response) => {
          payload.callback(response.status);
        });
    },
    // 핫플레이스 좋아요
    // 핫플레이스 좋아요 수 조회
    getHotplaceLike(context, payload) {
      http.get(`hotplace/like/${payload.hotplaceNo}`).then((response) => {
        context.commit({
          type: "HOTPLACE_LIKE",
          hotplaceLike: response.data,
        });
      });
    },
    // 핫플레이스 좋아요 했는지 확인
    getCheckLike(context, payload) {
      http
        .post("hotplace/like/check", payload.hotplaceLike)
        .then((response) => {
          context.commit({
            type: "HOTPLACE_LIKE_CHECK",
            hotplaceLikeCheck: response.data,
          });
        });
    },
    // 핫플레이스 좋아요 누르기
    createHotplaceLike(context, payload) {
      http
        .post("hotplace/like/", payload.hotplaceLike)
        .then((response) => {
          payload.callback(response.status);
        })
        .catch((response) => {
          payload.callback(response.status);
        });
    },
    // 핫플레이스 좋아요 취소
    deleteHotplaceLike(context, payload) {
      http
        .delete(
          `hotplace/like/${payload.hotplaceLike.hotplaceNo}/${payload.hotplaceLike.userNo}`
        )
        .then((response) => {
          payload.callback(response.status);
        })
        .catch((response) => {
          payload.callback(response.status);
        });
    },
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
            payload.callback(status);
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
    // 핫플레이스 글 한개만 조회
    getHotplace(context, payload) {
      http.get(`hotplace/${payload.hotplaceNo}`).then((response) => {
        context.commit({
          type: "HOTPLACE",
          hotplace: response.data,
        });
      });
    },
    // 핫플레이스 글 삭제
    deleteHotplace(context, payload) {
      http
        .delete(`hotplace/${payload.hotplaceNo}`)
        .then((response) => {
          payload.callback(response.status);
        })
        .catch((response) => {
          payload.callback(response.status);
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
    // 회원 정보 하나 가져오기
    getUser(context, payload) {
      http.get(`user/mypageno/${payload.userNo}`).then((response) => {
        context.commit({
          type: "USER",
          user: response.data,
        });
      });
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
