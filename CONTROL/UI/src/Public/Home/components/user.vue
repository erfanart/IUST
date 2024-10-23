<template>
  <div>
    <ul>
      <li v-for="user in users" :key="user.id">
        <a href="#" class="">{{ user.name }}-{{ user.age }}</a>
        <button @click="deleteUser(user.id)">delete</button>
        <input type="text" />
      </li>
    </ul>
  </div>
</template>
<script>
import eventBus from "../services/eventBus";
export default {
  name: "userTag",
  props: ["users"],
  methods: {
    deleteUser(id) {
      eventBus.$emit("deleteUser", id);
    },
  },
};
</script>
<style scoped>
ul {
  list-style: none;
}
a {
  text-decoration: none;
  color: black;
}
</style>