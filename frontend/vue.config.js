const { defineConfig } = require('@vue/cli-service')

module.exports ={
  publicPath:'/',
  devServer: {
    proxy:{
      '/api':{
        target:'http://localhost:8080',
        changeOrigin:true
      },
    },
  },
};
