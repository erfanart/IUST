<template>
  <div class="mainTemplate">
    <div class="login-box">
      <h2>Login</h2>
      <form @submit.prevent="submitForm">
        <div class="user-box">
          <input type="text" required="" v-model="adminId" />
          <label>Username</label>
        </div>
        <div class="user-box">
          <input type="password" required="" v-model="password" />
          <label>Password</label>
        </div>
        <div class="w-full flex justify-center">
          <button
            class="w-[200px] h-[50px] border border-black text-center hover:bg-white hover:transition-colors hover:duration-500 rounded-3xl"
          >
            submit
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script>
import axios from "axios";
export default {
  name: "siteLogin",
  data() {
    return {
      adminId: "",
      password: "",
    };
  },
  methods: {
    async submitForm() {
      try {
        const payload = {
          adminId: this.adminId,
          password: this.password,
        };
        const Response = await axios.post(
          "77.237.73.68:8084/api/v1/admin/authenticate",
          payload
        );
        console.log("success:", Response.data);
      } catch (error) {
        console.error("there was error:", error);
      }
    },
  },
};
</script>
<style scoped>
.mainTemplate {
  height: 100%;
}

.login-box {
  position: absolute;
  top: 50%;
  left: 50%;
  width: 400px;
  padding: 40px;
  transform: translate(-50%, -50%);
  background: rgba(0, 0, 0, 0.5);
  box-sizing: border-box;
  box-shadow: 0 15px 25px rgba(0, 0, 0, 0.6);
  border-radius: 10px;
}

.login-box h2 {
  margin: 0 0 30px;
  padding: 0;
  color: #fff;
  text-align: center;
}

.login-box .user-box {
  position: relative;
}

.login-box .user-box input {
  width: 100%;
  padding: 10px 0;
  font-size: 16px;
  color: #fff;
  margin-bottom: 30px;
  border: none;
  border-bottom: 1px solid #fff;
  outline: none;
  background: transparent;
}
.login-box .user-box label {
  position: absolute;
  top: 0;
  left: 0;
  padding: 10px 0;
  font-size: 16px;
  color: #fff;
  pointer-events: none;
  transition: 0.5s;
}

.login-box .user-box input:focus ~ label,
.login-box .user-box input:valid ~ label {
  top: -20px;
  left: 0;
  color: #03e9f4;
  font-size: 12px;
}

.login-box form a {
  position: relative;
  display: inline-block;
  padding: 10px 20px;
  color: #03e9f4;
  font-size: 16px;
  text-decoration: none;
  text-transform: uppercase;
  overflow: hidden;
  transition: 0.5s;
  margin-top: 40px;
  letter-spacing: 4px;
}
</style>
