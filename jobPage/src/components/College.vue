<template>

  <div>

    <el-card :body-style="{height: '75px'}">
      <el-row>
        <el-breadcrumb>
          <el-breadcrumb-item :to="{path: '/welcome'}">首页</el-breadcrumb-item>
          <el-breadcrumb-item>学生信息管理</el-breadcrumb-item>
          <el-breadcrumb-item>学校管理</el-breadcrumb-item>
        </el-breadcrumb>
      </el-row>

      <el-row :gutter="10">
        <el-col :span="6">
          <el-input placeholder="输入学校名称搜索" prefix-icon="el-icon-search" v-model="pager.coName" @change="listPage()"
            clearable>
          </el-input>
        </el-col>
        <el-col :span="2">
          <el-button plain icon="el-icon-search"></el-button>
        </el-col>
        <el-col :span="4" v-if="isAdmin">
          <el-button plain icon="el-icon-plus" @click="openAddDailog()"></el-button>
          <el-button plain icon="el-icon-edit" @click="openEditDailog()"></el-button>
          <el-button plain icon="el-icon-delete" @click="remove()"></el-button>
        </el-col>
      </el-row>
    </el-card>

    <el-card style="margin-top: 20px;">
      <el-table :data="tableData" stripe style="width: 100%" highlight-current-row @current-change="selectOneRow">
        <el-table-column prop="coId" label="学校ID" width="70px">
        </el-table-column>
        <el-table-column prop="coName" label="学校名称">
        </el-table-column>
        <el-table-column prop="coAddr" label="学校地址">
        </el-table-column>
        <el-table-column prop="coNum" label="学校编号" width="120px">
        </el-table-column>
        <el-table-column prop="coEmail" label="学校邮箱">
        </el-table-column>
        <el-table-column prop="createTime" label="创建时间">
        </el-table-column>
        <el-table-column prop="modifyTime" label="修改时间">
        </el-table-column>
      </el-table>
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pager.page"
        :page-sizes="[5, 10, 15, 20]" :page-size="pager.size" layout="total, sizes, prev, pager, next, jumper"
        :total="pager.total">
      </el-pagination>

    </el-card>
    <!-- 添加对话框 -->
    <el-dialog title="添加学校" :visible.sync="addFlag" destroy-on-close>
      <el-form :model="tbCollage" :rules="rules" ref="tbCollage">
        <el-row :gutter="10">
          <el-col :span="12">
            <el-form-item label="学校名称" prop="coName">
              <el-input v-model="tbCollage.coName"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="学校地址" prop="coAddr">
              <el-input v-model="tbCollage.coAddr"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col :span="12">
            <el-form-item label="学校编号" prop="coNum">
              <el-input v-model="tbCollage.coNum"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="学校邮箱" prop="coEmail">
              <el-input v-model="tbCollage.coEmail"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="closeDailog">取 消</el-button>
        <el-button type="primary" @click="save()">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 修改对话框-->
    <el-dialog title="修改学校" :visible.sync="editFlag" destroy-on-close>
      <el-form :model="tbCollage" :rules="rules" ref="tbCollage">
        <el-row :gutter="10">
          <el-col :span="12">
            <el-form-item label="学校名称" prop="coName">
              <el-input v-model="tbCollage.coName"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="学校地址" prop="coAddr">
              <el-input v-model="tbCollage.coAddr"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col :span="12">
            <el-form-item label="学校编号" prop="coNum">
              <el-input v-model="tbCollage.coNum"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="学校邮箱" prop="coEmail">
              <el-input v-model="tbCollage.coEmail"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="closeDailog">取 消</el-button>
        <el-button type="primary" @click="update()">确 定</el-button>
      </div>
    </el-dialog>
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
        coName: null
      },
      tbCollage: {
        coId: null,
        coName: null,
        coAddr: null,
        coNum: null,
        coEmail: null
      },
      rowData: null,
      addFlag: false,
      editFlag: false,
      options: [],
      isAdmin: false,
      rules: {
        coName: [{
          required: true,
          message: '请输入学校名称',
          trigger: 'blur'
        }],
        coAddr: [{
          required: true,
          message: '请输入学校地址',
          trigger: 'blur'
        }],
        coNum: [{
          required: true,
          message: '请输入学校编号',
          trigger: 'blur'
        }],
        coEmail: [{
          required: true,
          message: '请输入学校邮箱',
          trigger: 'blur'
        }]
      }
    }
  },
  methods: {
    remove () {
      if (this.rowData == null) {
        this.$message.error('请选择要删除的一行数据')
      } else {
        this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let coId = this.rowData.coId
          this.$http.post('http://101.200.147.50/tbCollege/remove?coId=' + coId)
            .then(response => {
              if (response.data.code === 200) {
                this.$message({
                  message: '恭喜你，删除成功',
                  type: 'success'
                })
                this.closeDailog()
              } else {
                this.$message.error('删除失败，请重试')
              }
              this.listPage()
            }).catch(error => {
              this.$message.error(error)
            })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
      }
    },
    update () {
      this.$http.post('http://101.200.147.50/tbCollege/update', this.tbCollage)
        .then(response => {
          if (response.data.code === 200) {
            this.$message({
              message: '恭喜你，修改成功',
              type: 'success'
            })
            this.closeDailog()
          } else {
            this.$message.error('修改失败，请重试')
          }
          this.listPage()
        }).catch(error => {
          this.$message.error(error)
        })
    },
    save () {
      this.$refs['tbCollage'].validate((valid) => {
        if (valid) {
          this.$http.post('http://101.200.147.50/tbCollege/save', this.tbCollage)
            .then(response => {
              if (response.data.code === 200) {
                this.$message({
                  message: '恭喜你，添加成功',
                  type: 'success'
                })
                this.closeDailog()
              } else {
                this.$message.error('添加失败，请重试')
              }
              this.listPage()
            }).catch(error => {
              this.$message.error(error)
            })
        }
      })
    },

    closeDailog () {
      this.addFlag = false
      this.editFlag = false
      this.tbCollage = {
        coId: null,
        coName: null,
        coAddr: null,
        coNum: null,
        coEmail: null
      }
    },
    openEditDailog () {
      if (this.rowData == null) {
        this.$message.error('请选择要修改的一行数据')
      } else {
        this.editFlag = !this.editFlag
        this.tbCollage = {
          coId: this.rowData.coId,
          coName: this.rowData.coName,
          coAddr: this.rowData.coAddr,
          coNum: this.rowData.coNum,
          coEmail: this.rowData.coEmail
        }
      }
    },
    openAddDailog () {
      this.addFlag = !this.addFlag
    },
    selectOneRow (val) {
      this.rowData = val
    },
    handleSizeChange (size) {
      this.pager.size = size
      this.listPage()
    },
    handleCurrentChange (page) {
      this.pager.page = page
      this.listPage()
    },
    listPage () {
      this.$http.get('http://101.200.147.50/tbCollege/listPage', {
        params: {
          page: this.pager.page,
          size: this.pager.size,
          coName: this.pager.coName
        }
      }).then(response => {
        this.pager.total = response.data.data.total
        this.tableData = response.data.data.rows
      }).catch(error => {
        console.log(error)
      })
    },
    isAdminUser () {
      let userInfo = JSON.parse(sessionStorage.getItem('user'))
      if (userInfo.uname === 'admin') {
        this.isAdmin = true
      }
    }
  },
  mounted () {
    this.listPage()
    this.isAdminUser()
  }

}
</script>

<style>
  .el-tooltip__popper {
    width: 300px;
    color: #777 !important;
    background: #D5DCE5 !important;
    margin-bottom: -20px !important;
  }
</style>
