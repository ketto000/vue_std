// const {defineConfig} = require('@vue/cli-service')
// module.exports = defineConfig({

module.exports = {
    devServer: {
        proxy: {
            '/api/': {
                target: 'http://localhost:8080',
            }
        }
    },
    transpileDependencies: true,
    lintOnSave: false,
}

