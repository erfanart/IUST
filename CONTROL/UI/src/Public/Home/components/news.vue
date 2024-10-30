<template>
  <div class="h-full w-full">
    <div
      class="h-[10%] w-full flex justify-center items-center font-bold text-[30px]"
    >
      <p>آخرین اخبار</p>
    </div>
    <div class="h-[90%] w-full">
      <section id="news" class="section">
        <div class="h-[86%] flex flex-wrap overflow-hidden">
          <div class="w-full h-full flex justify-around flex-wrap gap-2">
            <ul
              class="h-full w-full flex flex-wrap gap-x-30 justify-evenly gap-y-20"
            >
              <li
                class="h-[200px] mt-10 relative rounded-xl overflow-hidden"
                v-for="info in news"
                :key="info.id"
              >
                <img
                  :src="`${API.PUBLIC.NEWS.IMAGE}/${info.image.id}`"
                  class="h-full"
                  @mouseover="show()"
                />

                <p
                  class="absolute w-full h-full bg-black opacity-45 top-0 text-white flex justify-center items-center"
                  v-if="isShowing"
                  @mouseleave="hide()"
                >
                  {{ info.news }}
                </p>
              </li>
            </ul>
          </div>
        </div>
      </section>
        <div class="h-[14%] flex justify-center buttonBox items-center">
          <button
            class="lg:w-1/5 h-full w-1/4 bg-green-600 font-bold text-[20px] rounded-[35px] opacity-80"
          >
            مطالب بیشتر
          </button>
        </div>
      </div>
  </div>
</template>
<script>
import axios from "axios";
import { fetchApiConfig } from "@/config/api.js";
export const API = await fetchApiConfig("public");
export default {
  name: "siteNews",
  data() {
    return {
      news: null,
      isShowing: false,
      index: null,
      API: API,
    };
  },

  methods: {
    show() {
      this.isShowing = true;
    },
    hide() {
      this.isShowing = false;
    },
  },

  mounted() {
    axios.get(API.PUBLIC.NEWS.SHOW).then((Response) => {
      this.news = Response.data;
      this.index = this.news.length;
      console.log((this.index -= 1));
    });
  },
};
</script>
<style scoped>
.buttonBox:hover:after {
  content: "";
  width: 30%;
  height: 1px;
  border: 1px dashed black;
}
.buttonBox:hover:before {
  content: "";
  width: 30%;
  height: 1px;
  border: 1px dashed black;
}
</style>
