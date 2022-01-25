import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: Home,
    meta: {
      requiresAuth: true,
    },
  },
  {
    path: "/login",
    name: "login",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/Login.vue"),
    meta: {
      requiresVisitor: true,
    },
  },
  {
    path: "/register",
    name: "register",
    component: () => import("../views/Register.vue"),
    meta: {
      requiresVisitor: true,
    },
  },
  {
    path: "/logout",
    name: "logout",
    component: () => import("../views/Logout"),
    meta: {
      requiresAuth: true,
    },
  },
];

const router = new VueRouter({
  routes,
});

export default router;
