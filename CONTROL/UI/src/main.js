import Vue from "vue";
import App from "./App.vue";
import "./assets/tailwind.css";
import VueRouter from "vue-router";
import login from "./components/login.vue";
import main from "./home.vue";
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
      component:login,
    },
  ],
});

Vue.config.productionTip = false;

new Vue({
  render: (h) => h(App),
  router,
}).$mount("#app");
