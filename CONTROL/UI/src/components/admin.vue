<template>
  <div class="h-full w-full">
    <div class="sideBox h-full w-[20%] border border-red-500">
      <button
        class="w-full h-1/5"
        v-for="item in items"
        :key="item.id"
      ></button>
    </div>
    <div class="main"></div>
  </div>
</template>
<script>
export default {
  name: "siteAdmin",
  data() {},
};
</script>
<style scoped></style>