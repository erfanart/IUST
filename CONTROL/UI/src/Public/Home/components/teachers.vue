<template>
  <div class="xl:h-[1500px] w-full flex flex-wrap justify-around">
    <p class="w-full text-center mt-10 font-bold text-[30px] h-[10%]">
      اعضای هیات علمی
    </p>
    <ul
    class="w-full h-[88%] xl:gap-x-[10%] sm:gap-x-[25%] gap-y-2 flex justify-center xl:justify-around flex-wrap"
    >
    <section id="faculties" class="section">
      <li
        class="h-[410px] w-[51%] sm:w-[20%] rounded-3xl overflow-hidden flex justify-center flex-wrap content-start"
        v-for="info in contents"
        :key="info.id"
      >
        <img
          :src="`${API.PUBLIC.FACULTIES.IMAGE}/${info.image.id}`"
          alt=""
          class="h-[40%] w-[50%] mt-5"
        />
        <p class="w-full font-bold mt-4 text-center">
          دکتر {{ info.firstName }} {{ info.lastName }}
        </p>
        <p>{{ info.description }}</p>
      </li>
    </section>
    </ul>
  </div>
</template>
<script>

import axios from "axios";
import { fetchApiConfig } from "@/config/api.js";
export const API = await fetchApiConfig("public"); 

export default {
  name: "siteTeachers",
  data() {
    return {
      contents: null,
      API: API,
    };
  },
  mounted() {
    axios.get(API.PUBLIC.FACULTIES.SHOW).then((Response) => {
      this.contents = Response.data;
    });
  },
};
// console.log(API);

</script>
<style scoped>
li {
  box-shadow: 0px 4px 10px 5px rgba(0, 0, 0, 0.25);
}
</style>
