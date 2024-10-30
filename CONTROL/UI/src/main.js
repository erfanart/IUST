import Vue from "vue";
import App from "./App.vue";
import "./assets/tailwind.css";
import VueRouter from "vue-router";
import admin from "./Admin/Home/admin.vue";
import main from "./Public/Home/home.vue";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import "./tailwind.css";
Vue.use(VueRouter);

const router = new VueRouter({
  mode: "history",
  routes: [
    {
      path: "/",
      component: main,
    },
    {
      path: "/admin",
      component: admin,
    },
  ],
});
Vue.config.productionTip = false;

new Vue({
  render: (h) => h(App),
  router,
}).$mount("#app");
