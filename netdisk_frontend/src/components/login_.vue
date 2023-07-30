<template>
    <div class="login">
      <el-image
            style="width: 100%; height: 50%;"
            :src="url"></el-image>
      <el-form ref="loginForm" :model="loginForm" label-width="80px" class="login-form">
        <h2 style="color: grey; margin-top: 10px;text-align: center;">个人网盘</h2>
        <el-form-item label="用户名" prop="userName">
          <el-input v-model="loginForm.userName" 
          @keyup.enter.native="handleLogin"
          @keyup.space.native="toregister"
          placeholder="请输入用户名或邮箱，注册请按[space]"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="userPassword">
          <el-input v-model="loginForm.userPassword" 
          @keyup.enter.native="handleLogin"
          type="password" 
          show-password autocapitalize="off"
          placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item >
          <el-button style="margin-left: -10%;" type="primary" @click="handleLogin">登录</el-button>
          <el-button @click="toregister">注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </template>
<script>
export default {
  name: 'login_',
  data () {
    return {
      loginForm: {
        userName: '',
        userPassword: ''
      },
      url: 'https://picx.zhimg.com/80/v2-141e925b4bdf4543c9e3ad20cd0d3a5a_720w.webp?source=1940ef5c'
    }
  },
  methods: {
    handleLogin () {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.$axios.post('http://localhost:8088/user/login', this.loginForm)
            .then(response => {
              // 处理登陆成功的逻辑
              console.log(response);
              // sessionStorage.setItem("Userms",JSON.stringify(response.data));

              if(response.data.userName === this.loginForm.userName){
                response.data.userPassword = null;
                sessionStorage.setItem("Userms",JSON.stringify(response.data));
                this.$router.push("/index");
              }else{
                alert("验证失败，用户名或密码错误");
              }
              //this.$router.push("/index");
            })
            // .catch(error => {
            //   // 处理登陆失败的逻辑
            //   console.log(error);
            //   alert("验证失败，用户名或密码错误");
            //   //this.$router.push("/regist");
            // })
        }
      })
    },
    toregister() {
      this.$router.push("/regist");
    }
  }
}
</script>
<style scoped>

 .login {
  user-select: none;
  width: 30%;
  height: 25%;
  margin: auto;
 }

 .el-form {
  /* margin-top: 45%; */
  border: 2px #c3c3c3 solid;
  border-radius: 15px;
  box-shadow: 0 3px 6px rgba(0, 0, 0, .12), 0 2px 6px rgba(0, 0, 0, .04);
 }

  .el-form-item {
    margin-top: 20px;
    width: 95%;
  }

</style>
