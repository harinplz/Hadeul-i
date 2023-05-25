import Vue from "vue";
import VueRouter from "vue-router";
import store from "@/store";

Vue.use(VueRouter);

const onlyAuthUser = (to, from, next) => {
  console.log(to, from, next);
  //현재 로그인 된 상태
  if (store.getters.userInfo != null && store.getters.accessToken) {
    // this.$store.dispatch("getUserInfo")
    store.dispatch("getUserInfo", {
      accessToken: store.getters.accessToken,
      callback: (status) => {
        if (status == 200) {
          console.log("현재 accessToken은 유효함!!");
          next();
        } else if (status == 401) {
          // accessToken이 만료되었으므로 refreshToken을 이용하여 새로운 accessToken 얻기
          console.log("refreshToken: " + store.getters.refreshToken);
          store.dispatch("getAccessToken", {
            refreshToken: store.getters.refreshToken,
            callback: (status) => {
              if (status == 200) {
                // 새로운 accessToken을 얻었으므로, 다시 사용자 정보 얻어오기
                store.dispatch("getUserInfo", {
                  accessToken: store.getters.accessToken,
                  callback: () => {
                    console.log("새로운 accessToken을 획득!!");
                    next();
                  },
                });
              } else if (status == 401) {
                // refreshToken도 만료된 상태이므로, 사용자에게 재로그인을 하도록 유도
                alert("토큰이 만료되었습니다. 재로그인 해주세요.");
                store.dispatch("logout"); //로그아웃하고
              }
            },
          });
        }
      },
    });
  } else {
    //로그인이 되어있지 않다면 로그인 페이지로 이동
    alert("로그인이 필요한 페이지입니다.");
  }
};

console.log(onlyAuthUser); //오류 안나도록

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("@/views/AppView.vue"),
  },
  // {
  //   path: '/about',
  //   name: 'about',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  // }
  {
    path: "/admin",
    name: "admin",
    component: () => import("@/views/AppAdmin"),
  },
  {
    path: "/mypage/:userNo",
    name: "Mypage",
    component: () => import("@/components/user/UserMyPage.vue"),
    beforeEnter: onlyAuthUser,
  },
  {
    path: "/attractions",
    name: "attractions",
    component: () => import("@/views/AppAttraction.vue"),
    beforeEnter: onlyAuthUser,
  },
  {
    path: "/community",
    name: "communities",
    component: () => import("@/views/AppCommunity.vue"),
    redirect: "/community/list",
    beforeEnter: onlyAuthUser,
    children: [
      {
        path: "list",
        name: "CommunityList",
        component: () => import("@/components/community/CommunityList.vue"),
      },
      {
        path: "create",
        name: "CommunityCreate",
        component: () => import("@/components/community/CommunityCreate"),
      },
      {
        path: "detail/:boardNo",
        name: "CommunityDetail",
        component: () => import("@/components/community/CommunityDetail"),
      },
      {
        path: "modify/:boardNo",
        name: "CommunityModify",
        component: () => import("@/components/community/CommunityModify"),
      },
    ],
  },
  {
    path: "/hotplace",
    name: "Hotplace",
    component: () => import("@/views/AppHotplace.vue"),
    redirect: "/hotplace/list",
    beforeEnter: onlyAuthUser,
    children: [
      {
        path: "list",
        name: "HotplaceList",
        component: () => import("@/components/hotplace/HotplaceList"),
      },
      {
        path: "create",
        name: "HotplaceCreate",
        component: () => import("@/components/hotplace/HotplaceCreate"),
      },
      {
        path: "detail/:hotplaceNo",
        name: "HotplaceDetail",
        component: () => import("@/components/hotplace/HotplaceDetail"),
      },
    ],
  },
  {
    path: "/travelroute",
    name: "TravelRoute",
    component: () => import("@/views/AppTravelRoute.vue"),
    redirect: "/travelroute/list",
    beforeEnter: onlyAuthUser,
    children: [
      {
        path: "list",
        name: "TravelRouteList",
        component: () =>
          import("@/components/travelroutes/TravelRouteList.vue"),
      },
      {
        path: "create",
        name: "TravelRouteCreate",
        component: () =>
          import("@/components/travelroutes/TravelRouteCreate.vue"),
      },
      {
        path: "detail/:travelRouteNo",
        name: "TravelRouteDetail",
        component: () =>
          import("@/components/travelroutes/TravelRouteDetail.vue"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
