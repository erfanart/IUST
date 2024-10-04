<template>
  <div class="h-full w-full font-[main]">
    <header1></header1>
    <div class="h-full w-full flex justify-center items-center imgBox">
      <div class="h-[200px] w-[400px]" />
    </div>
    <news></news>
    <teacher></teacher>
    <footer1></footer1>
  </div>
</template>
<script>
import teacher from "./components/teachers.vue";
import header1 from "./components/header.vue";
import news from "./components/news.vue";
import footer1 from "./components/footer.vue";
export default {
  name: "mainPage",
  components: { header1, news, footer1, teacher },
};
</script>
<style scoped>
.imgBox div {
  background-image: url("@/assets/دانشگاه\ علم\ و\ صنعت\ ایران.svg");
  background-repeat: no-repeat;
  background-size: contain;
}
@font-face {
  font-family: main;
  src: url("@/assets/fonts/Vazirmatn-Black.woff2");
  src: url("@/assets/fonts/Vazirmatn-Bold.woff2");
  src: url("@/assets/fonts/Vazirmatn-ExtraBold.woff2");
  src: url("@/assets/fonts/Vazirmatn-ExtraLight.woff2");
  src: url("@/assets/fonts/Vazirmatn-Light.woff2");
}
</style>
