module.exports = {
  baseUrl: './',
  devServer: {
    host: 'localhost',
    port: 8083,
    open: false,

    proxy: {
      "/api": {
        target: "http://101.200.147.50:8999",
        ws: true,
        changeOrigin: true,
        pathRewrite: {
          "^/api": "/",
        }
      }
    }
  },
  lintOnSave: false
}
