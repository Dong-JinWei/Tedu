<template>

  <el-card class="box-card">
    <div slot="header" class="header-card">
      <span>用户登陆</span>
    </div>
    <el-form :model="tbUser" :rules="rules" ref="tbUser">
      <el-form-item prop="uname">
        <el-input suffix-icon="el-icon-user-solid" v-model="tbUser.uname" clearable placeholder="请输入用户名称"></el-input>
      </el-form-item>
      <el-form-item prop="upassword">
        <el-input suffix-icon="el-icon-s-goods" v-model="tbUser.upassword" clearable type="password"
          placeholder="请输入密码">
        </el-input>
      </el-form-item>
      <el-form-item >
        <el-col :offset="4" :span="6">
          <el-input style="width: 100px;" v-model="code" @change="checkCaptcha">
          </el-input>
        </el-col>
        <el-col :span="6">
          <img @click="replace" :src="imgCode" alt="">
        </el-col>
      </el-form-item>
      <el-row class="button" :gutter="10">
        <el-col :span="8">
          <el-button type="success" plain @click="login">登录</el-button>
        </el-col>
        <el-col :span="8">
          <el-button plain>
            <a href="#/register" :underline="false">没有账号，点击注册</a>
          </el-button>
        </el-col>
      </el-row>
    </el-form>

  </el-card>
</template>

<script>
export default {
  data () {
    return {
      rules: {
        uname: [{
          required: true,
          message: '请输入用户名称',
          trigger: 'blur'
        }],
        upassword: [{
          required: true,
          message: '请输入密码',
          trigger: 'blur'
        }]
      },
      tbUser: {
        uid: null,
        uname: null,
        uemail: null,
        upassword: null
      },
      code: null,
      imgCode: null
    }
  },
  methods: {

    replace () {
      const num = Math.ceil(Math.random() * 10)
      this.imgCode = 'http://localhost:80//getCaptchaImg?id=' + num
    },

    login () {
      this.$http.get('http://localhost:80/checkCaptcha?code=' + this.code)
        .then(response => {
          if (response.data) {
            this.$refs['tbUser'].validate((valid) => {
              if (valid) {
                this.$http.get('http://localhost:80/tbUser/login', {
                  params: {
                    uName: this.tbUser.uname,
                    uPassword: this.tbUser.upassword
                  }
                })
                  .then(response => {
                    if (response.data.message === 'no') {
                      this.$message.error('不存在此用户，请检查用户名是否正确')
                    } else if (response.data.message === 'error') {
                      this.$message.error('密码错误，请重新输入')
                    } else {
                      sessionStorage.setItem('user', JSON.stringify(response.data.data))
                      this.$router.push('/welcome')
                    }
                  })
                  .catch(e => {
                    this.$message.error('网络异常，请稍后重试')
                  })
              }
            })
          } else {
            this.$message.error('验证码错误，请重试')
          }
        })
    }
  },
  mounted () {
    this.replace()
  }
}
</script>

<style scoped>
  .box-card {
    width: 550px;
    height: 380px;
    border-radius: 10px;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
    box-shadow: 10px 20px 30px #888888;
  }

  .login-form {
    position: absolute;
    bottom: 0px;
    width: 100%;
    padding: 0px 20px;
    box-sizing: border-box;
  }

  .el-button {
    display: flex;
    justify-content: center;
    font-weight: bold;
    height: 40px;
    font-size: 18px;
    border-radius: 10px;
    margin-left: 90px;
  }

  .header-card {
    display: flex;
    justify-content: center;
    font-size: 18px;
  }

  .el-input {
    border-radius: 10px;
    height: 50px;
    font-size: 18px;
    border-bottom-color: green;

  }
</style>
