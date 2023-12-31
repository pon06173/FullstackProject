const { VueLoaderPlugin } = require("vue-loader");

const {BASE_DIR, PROJECT_NAME, SERVICE_STATUS} = require("./Global");

module.exports = {
    name: PROJECT_NAME,
    mode: "development",//production
    devtool: 'eval',
    resolve: {fallback: { "constants": false }},

    entry: {
        app: [`${BASE_DIR}/client/views/pages/index.js`]
    },

    module: {
        rules: [{
            test: /\.vue?$/,
            loader: 'vue-loader',
        }, {
            test: /\.(js|jsx)?$/,
            loader: 'babel-loader',
        /*}, {
            test: /\.css$/,
            use: ['vue-style-loader', 'css-loader']
        }, {
        test: /\.(jpe?g|png|gif|svg|ttf|eot|woff|woff2)$/i,
        use: [{
            loader:'url-loader',
            options:{
            limit:8192,
            fallback:require.resolve('file-loader')
            }*/
        }]
    },

    plugins: [new VueLoaderPlugin()],

    output: {
        path: `${BASE_DIR}/client/build`,   // __dirname: webpack.config.js 파일이 위치한 경로
        filename: 'bundle.js'
    },
}