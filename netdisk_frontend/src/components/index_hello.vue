<template>
      <el-container style="height: 100vh;">
        <el-aside width="200px"><aside_/></el-aside>
        <el-container>
          <el-header><header_/></el-header>
          <el-main><router-view/></el-main>
          <el-footer><footer_/></el-footer>
        </el-container>
      </el-container>
  </template>
  
  <script>
  import header_ from "./header_.vue";
  import aside_ from "./aside_.vue";
  import footer_ from "./footer_.vue";

  export default {
    name: "index_hello",
    props: {
        msg: String
    },
    components: { 
        header_, 
        aside_,
        footer_
    },
    methods: {
      open() {
        const h = this.$createElement;
        const user = JSON.parse(sessionStorage.getItem("Userms"));

        if(user != null){
          this.$notify({
          // title: '',
          message: h('i', { style: 'color: teal'}, `欢迎${user["userName"]}！！！`)
          });
        }

        this.$router.replace("/home").catch(console.log);
      }
    },
    beforeMount() {
      this.open();
    }
}
  </script>
  <style>
    .el-header, .el-footer {
      background-color: #fff;
      color: #333;
      text-align: center;
      line-height: 60px;
    }
  
    .el-footer {
      line-height: 70px;
      border-bottom: #fff 1px;
      box-shadow: 0 -2px 3px 0px rgba(53, 53, 53, 0.482);
    }

    .el-aside {
      background-color: #eee;
      text-align: center;
      line-height: 200px;
    }
  
    .el-main {
      background-color: #E9EEF3;
      color: #333;
      text-align: center;
    }
  
    body > .el-container {
      margin-bottom: 40px;
      height: 100vh;
    }

    /* body::-webkit-scrollbar{
      display: none;
    } */

    body {
      overflow: hidden;
    }

  </style>
  