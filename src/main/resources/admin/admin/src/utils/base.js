const base = {
    get() {
        return {
            url : "http://localhost:8080/springbooth56pqtxo/",
            name: "springbooth56pqtxo",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/springbooth56pqtxo/front/dist/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "音乐节购票网站"
        } 
    }
}
export default base
