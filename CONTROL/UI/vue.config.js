const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  	devServer: {
   		port: 8085, // your desired port number
  	},
	transpileDependencies: true
})
