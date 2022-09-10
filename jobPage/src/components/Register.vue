<template>

  <el-card class="box-card">
    <div slot="header" class="header-card">
      <span>用户注册</span>
    </div>
    <el-form :model="tbUser" :rules="rules" ref="tbUser">
      <el-form-item prop="uname">
        <el-input suffix-icon="el-icon-user-solid" v-model="tbUser.uname" clearable placeholder="请输入用户名称"></el-input>
      </el-form-item>
      <el-form-item prop="upassword">
        <el-input suffix-icon="el-icon-s-goods" v-model="tbUser.upassword" clearable type="password" placeholder="请输入密码">
        </el-input>
      </el-form-item>
      <el-form-item prop="uemail">
        <el-input suffix-icon="el-icon-s-goods" v-model="tbUser.uemail" clearable placeholder="请输入邮箱">
        </el-input>
      </el-form-item>
      <el-row :gutter="10">
        <el-col :span="8">
          <el-button type="success" plain @click="register">注册</el-button>
        </el-col>
        <el-col :span="8">
          <el-button plain>
            <a href="#/login" :underline="false">已有账号？点击登陆</a>
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
        }],
        uemail: [{
          required: true,
          message: '邮箱',
          trigger: 'blur'
        }]
      },
      tbUser: {
        uid: null,
        uname: null,
        uemail: null,
        upassword: null
      }
    }
  },
  methods: {
    register () {
      this.$refs['tbUser'].validate((valid) => {
        if (valid) {
          this.$http.post('http://localhost:80/tbUser/register', this.tbUser)
            .then(response => {
              if (response.data.message === 'has') {
                this.$message.error('用户名被使用！')
              } else if (response.data.message === 'ok') {
                this.$message({
                  message: '恭喜你，注册成功',
                  type: 'success'
                })
              }
            })
        }
      })
    }
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
