<template>
  <el-container>
    <el-header>
      <el-row :gutter="10">
        <!-- <el-col :span="4"> -->
        <!-- </el-col> -->
        <!-- <el-col :span="16"> -->
        <div class="grid-content">
          <el-menu router class="el-menu-demo" mode="horizontal">
            <el-menu-item index="1">
              <el-link id="logo" href="#/welcome" :underline="false" style="font-size: 20px;">就业分析平台</el-link>
            </el-menu-item>
            <el-submenu index="2">
              <template slot="title">学生信息管理</template>
              <el-menu-item index="/college">学校管理</el-menu-item>
              <el-menu-item index="/dept">院系管理</el-menu-item>
              <el-menu-item index="/specialty">专业管理</el-menu-item>
              <el-menu-item index="/clazz">班级管理</el-menu-item>
              <el-menu-item index="/student">学生管理</el-menu-item>
            </el-submenu>

            <el-submenu index="3">
              <template slot="title">企业信息管理</template>
              <el-menu-item index="/area">区域管理</el-menu-item>
              <el-menu-item index="/region">省/市/区管理</el-menu-item>
              <el-menu-item index="/company">企业管理</el-menu-item>
            </el-submenu>

            <el-submenu index="4">
              <template slot="title">就业信息管理</template>
              <el-menu-item index="/job">就业信息</el-menu-item>
              <el-menu-item index="/direction">就业方向管理</el-menu-item>
              <el-menu-item index="/protocol">协议信息管理</el-menu-item>
            </el-submenu>
            <el-submenu index="5">
              <template slot="title">用户信息管理</template>
              <el-menu-item index="/user">用户信息</el-menu-item>
            </el-submenu>
            <el-menu-item id="info" index="6">

              当前登陆用户：<el-tag size="medium">{{uname}}</el-tag>
            </el-menu-item>
          </el-menu>
        </div>
        <!-- </el-col> -->
        <!-- <el-col :offset="2" :span="2"> -->
        <!-- </el-col> -->
      </el-row>
    </el-header>

    <el-main>
      <!-- 路由显示器 -->
      <router-view>

      </router-view>
    </el-main>

  </el-container>
</template>

<script>
export default {

  data () {
    return {
      uname: null
    }
  },
  methods: {

    show () {
      let userInfo = JSON.parse(sessionStorage.getItem('user'))
      this.uname = userInfo.uname
    }
  },
  mounted () {
    this.show()
  }

}
</script>

<style>
  #info {
    float: right;
    right: 10px;
  }

  body {
    background: #F8F8F8;
  }

  .el-header {
    color: #333;
    text-align: center;
    line-height: 60px;
  }

  .el-main {
    color: #333;
    text-align: center;
  }

  body>.el-container {
    margin-bottom: 40px;
    line-height: 100%;
  }

  .el-row {
    margin-bottom: 20px;

    &:last-child {
      margin-bottom: 0;
    }
  }

  .el-col {
    border-radius: 4px;
  }

  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }

  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
</style>
