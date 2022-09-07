<template>

  <div>

    <el-card :body-style="{height: '75px'}">
      <el-row>
        <el-breadcrumb>
          <el-breadcrumb-item :to="{path: '/welcome'}">首页</el-breadcrumb-item>
          <el-breadcrumb-item>企业管理</el-breadcrumb-item>
          <el-breadcrumb-item>区域管理</el-breadcrumb-item>
        </el-breadcrumb>
      </el-row>

      <el-row :gutter="10">
        <el-col :span="8">
          <el-input placeholder="根据区域或省份名称搜索" prefix-icon="el-icon-search" v-model="pager.arDesc" @change="listPage()"
            clearable>
          </el-input>
        </el-col>
        <el-col :span="2">
          <el-button plain icon="el-icon-search" @click="openAddDailog()"></el-button>
        </el-col>
      </el-row>
    </el-card>

    <el-card style="margin-top: 20px;">
      <el-table :data="tableData" stripe style="width: 100%" >
        <el-table-column prop="arId" label="区域ID" width="200px">
        </el-table-column>
        <el-table-column prop="arName" label="区域名称">
        </el-table-column>
        <el-table-column prop="arDesc" label="区域描述">
        </el-table-column>
      </el-table>
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pager.page"
        :page-sizes="[5, 10, 15, 20]" :page-size="pager.size" layout="total, sizes, prev, pager, next, jumper"
        :total="pager.total">
      </el-pagination>
    </el-card>

  </div>

</template>

<script>
export default {
  data () {
    return {
      tableData: [],
      pager: {
        page: 1,
        size: 10,
        total: 0,
        arDesc: null
      },
      tbArea: {
        arId: null,
        arName: null,
        arDesc: null
      }
    }
  },
  methods: {

    listPage () {
      this.$http.get('http://localhost/tbArea/listPage', {
        params: {
          page: this.pager.page,
          size: this.pager.size,
          arDesc: this.pager.arDesc
        }
      }).then(response => {
        this.pager.total = response.data.data.total
        this.tableData = response.data.data.rows
      }).catch(error => {
        this.$message.error(error)
      })
    }
  },
  mounted () {
    this.listPage()
  }

}
</script>

<style>
</style>
