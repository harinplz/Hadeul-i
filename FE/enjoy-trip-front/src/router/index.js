import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

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
    path: "/attractions",
    name: "attractions",
    component: () => import("@/views/AppAttraction.vue"),
  },
  {
    path: "/community",
    name: "communities",
    component: () => import("@/views/AppCommunity.vue"),
    redirect: "/community/list",
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
        path: "detail",
        name: "HotplaceDetail",
        component: () => import("@/components/hotplace/HotplaceDetail"),
      }
    ]
    
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
