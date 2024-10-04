<template>
  <div
    class="xl:h-[1500px] w-full border border-blue-600 flex flex-wrap justify-around"
  >
    <p class="w-full text-center mt-10 font-bold text-[30px] h-[10%]">
      اعضای هیات علمی
    </p>
    <ul
      class="w-full h-[88%] border border-cyan-600 xl:gap-x-[10%] sm:gap-x-[25%] gap-y-2 flex justify-center xl:justify-around flex-wrap"
    >
      <li
        class="h-[410px] w-[51%] sm:w-[20%] rounded-3xl overflow-hidden flex justify-center flex-wrap content-start"
        v-for="info in contents"
        :key="info.id"
      >
        <img
          :src="`http://77.237.73.68:8084/api/v1/admin/fileSystem/${info.imageDto.name}`"
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
import axios from "axios";
export default {
  name: "siteTeachers",
  data() {
    return {
      contents: null,
    };
  },
  mounted() {
    axios
      .get("http://77.237.73.68:8084/api/v1/admin/show_science_committees")
      .then((Response) => {
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