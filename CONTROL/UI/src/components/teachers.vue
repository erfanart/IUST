<template>
  <div class="xl:h-[1500px] w-full flex flex-wrap justify-around">
    <p class="w-full text-center mt-10 font-bold text-[30px] h-[10%]">
      اعضای هیات علمی
    </p>
    <ul
      class="w-full h-[88%] xl:gap-x-[10%] sm:gap-x-[25%] gap-y-2 flex justify-center xl:justify-around flex-wrap"
    >
      <li
        class="h-[410px] w-[51%] sm:w-[20%] rounded-3xl overflow-hidden flex justify-center flex-wrap content-start"
        v-for="info in contents"
        :key="info.id"
      >
        <img
          :src="`${API_BASE_URL}/home/fileSystem/${info.imageDto.name}`"
          alt=""
          class="h-[40%] w-[50%] mt-5"
        />
        <p class="w-full font-bold mt-4 text-center">
          دکتر {{ info.firstName }} {{ info.lastName }}
        </p>
        <p>{{ info.description }}</p>
      </li>
    </ul>
  </div>
</template>
<script>
import { API } from "@/config/api";
import axios from "axios";
export default {
  name: "siteTeachers",
  data() {
    return {
      contents: null,
      API_BASE_URL: API.BASE_URL,
    };
  },
  mounted() {
    axios.get(API.HOME.SHOW_SCIENCE_COMMITTEES).then((Response) => {
      this.contents = Response.data;
      console.log(this.contents[0]);
    });
  },
};
</script>
<style scoped>
li {
  box-shadow: 0px 4px 10px 5px rgba(0, 0, 0, 0.25);
}
</style>
