const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  devServer: {
    allowedHosts: "control.rcsis.ir",
    host: "0.0.0.0",
    port: 8084, // your desired port number
  },
  transpileDependencies: true,
});
