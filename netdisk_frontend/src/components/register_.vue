<template>
  <div class="regist">
    <el-form :model="registerForm" label-width="80px" :rules="rules" ref="regForm">
      <h2 style="color: grey; margin-top: 10px;text-align: center;">注册</h2>
      <!-- 用户名 Input -->
      <el-form-item label="用户名" prop="name">
        <el-input v-model="registerForm.name" @keyup.enter.native="handleRegister" placeholder="请输入新的用户名"
          auto-complete="off"></el-input>
      </el-form-item>
      <!-- 密码 Input -->
      <el-form-item label="密码" prop="password">
        <el-input type="password" @keyup.enter.native="handleRegister" v-model="registerForm.password">
        </el-input>
      </el-form-item>
      <!-- 确认密码 Input -->
      <el-form-item label="确认密码" prop="verifyPassword">
        <el-input type="password" @keyup.enter.native="handleRegister" v-model="registerForm.verifyPassword"></el-input>
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input @keyup.enter.native="handleRegister" v-model="registerForm.email"></el-input>
      </el-form-item>
      <el-form-item label="网盘名" prop="diskName">
        <el-input placeholder="默认用户名" @keyup.enter.native="handleRegister" v-model="registerForm.diskName"></el-input>
      </el-form-item>
      <!-- 注册按钮 -->
      <el-form-item>
        <el-button style="margin-left: -10%;" @click="tologin">返回</el-button>
        <el-button type="primary" @click="handleRegister">注册</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'register_',
  data() {
    //验证规则
    let checkPassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.registerForm.verifyPassword !== '') {
          this.$refs.regForm.validateField('verifyPassword');
        }
        callback();
      }
    };
    let checkVerifyPassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.registerForm.password) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      registerForm: {
        name: '',
        password: '',
        verifyPassword: '',
        email: '',
        diskName: ''
      },
      rules: {
        name: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
        password: [{ required: true, validator: checkPassword, trigger: 'blur' }],
        verifyPassword: [{ required: true, validator: checkVerifyPassword, trigger: 'blur' }]
      }
    }
  },
  methods: {
    handleRegister() {
      this.$refs.regForm.validate(valid => {
        // 验证通过
        if (valid) {
          this.axios.post('http://localhost:8088/user/register', {
            userName: this.registerForm.name,
            userPassword: this.registerForm.password,
            userEmail: this.registerForm.email,
            userNickname: this.registerForm.diskName
          }).then(res => {
            console.log(res.data);

            this.insertUserStatus(this.registerForm.name);
            this.$router.go(-1);
          }).catch(() => console.log("出错了！"));

          console.log("注册成功！");
        }
      })

    },
    insertUserStatus(name) {
      this.axios.post('http://localhost:8088/status/insert', {
        userName: name,
        userStatus: 0
      }).then(() => { });
    },
    tologin() {
      // alert("监听到esc键");
      this.$router.go(-1);
    }
  }
}
</script>
<style scoped>
.regist {
  user-select: none;
  width: 30%;
  height: 25%;
  margin: auto;
}

.el-form {
  margin-top: 25%;
  border: 2px #c3c3c3 solid;
  border-radius: 15px;
  box-shadow: 0 3px 6px rgba(0, 0, 0, .12), 0 2px 6px rgba(0, 0, 0, .04);
}

.el-form-item {
  margin-top: 20px;
  width: 95%;
}
</style>