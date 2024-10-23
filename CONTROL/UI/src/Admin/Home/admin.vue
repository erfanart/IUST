<template>
  <div class="h-full w-full flex">
    <div class="sideBox h-full w-[20%] border border-red-500 flex flex-wrap">
      <div class="h-1/5 w-full flex justify-center items-center">
        <button class="w-1/2 h-[50%] rounded-3xl bg-slate-700 text-white">
          news
        </button>
      </div>
    </div>
    <div class="main w-[80%] h-full border border-black">
      <table class="border border-pink-700">
        <tr class="border border-pink-700">
          <th></th>
        </tr>
      </table>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import API from "@/config/api";
export default {
  name: "siteAdmin",
  data() {
    return {
      API: API,
      news: null,
    };
  },
  mounted() {
    axios.get(API.PUBLIC.BASE).then((Response) => {
      this.news = Response.data;
      console.log(this.news);
    });
  },
};
</script>
<style scoped></style>
