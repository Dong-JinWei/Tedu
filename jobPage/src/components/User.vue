<template>

  <div>

    <el-card :body-style="{height: '75px'}">
      <el-row>
        <el-breadcrumb>
          <el-breadcrumb-item :to="{path: '/welcome'}">首页</el-breadcrumb-item>
          <el-breadcrumb-item>用户信息管理</el-breadcrumb-item>
          <el-breadcrumb-item>用户管理</el-breadcrumb-item>
        </el-breadcrumb>
      </el-row>

      <el-row :gutter="10">
        <el-col :span="6">
          <el-input placeholder="输入用户名搜索" prefix-icon="el-icon-search" v-model="pager.uName" @change="listPage()"
            clearable>
          </el-input>
        </el-col>
        <el-col :span="8">
          <el-button plain icon="el-icon-search"></el-button>
          <el-button plain icon="el-icon-plus" @click="openAddDailog()"></el-button>
          <el-button plain icon="el-icon-edit" @click="openEditDailog()"></el-button>
          <el-button plain icon="el-icon-delete" @click="remove()"></el-button>
        </el-col>
      </el-row>
    </el-card>

    <el-card style="margin-top: 20px;">
      <el-table :data="tableData" stripe style="width: 100%" highlight-current-row @current-change="selectOneRow">
        <el-table-column prop="uid" label="用户ID" width="70px">
        </el-table-column>
        <el-table-column prop="uname" label="用户名">
        </el-table-column>
        <el-table-column prop="uemail" label="用户邮箱">
        </el-table-column>
        <el-table-column prop="upassword" label="用户密码">
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
    <el-dialog title="添加用户" :visible.sync="addFlag" destroy-on-close>
      <el-form>
        <el-row :gutter="10">
          <el-form-item label="用户名称">
            <el-input v-model="tbUser.uname"></el-input>
          </el-form-item>
        </el-row>
        <el-row :gutter="10">
          <el-form-item label="用户密码">
            <el-input v-model="tbUser.upassword"></el-input>
          </el-form-item>
        </el-row>
        <el-row :gutter="10">
          <el-form-item label="用户邮箱">
            <el-input v-model="tbUser.uemail"></el-input>
          </el-form-item>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="closeDailog">取 消</el-button>
        <el-button type="primary" @click="save()">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 修改对话框-->
    <el-dialog title="修改学校" :visible.sync="editFlag" destroy-on-close>
     <el-form>
       <el-row :gutter="10">
         <el-form-item label="用户名称">
           <el-input v-model="tbUser.uname"></el-input>
         </el-form-item>
       </el-row>
       <el-row :gutter="10">
         <el-form-item label="用户密码">
           <el-input v-model="tbUser.upassword"></el-input>
         </el-form-item>
       </el-row>
       <el-row :gutter="10">
         <el-form-item label="用户邮箱">
           <el-input v-model="tbUser.uemail"></el-input>
         </el-form-item>
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
        uName: null
      },
      tbUser: {
        uid: null,
        uname: null,
        uemail: null,
        upassword: null
      },
      rowData: null,
      addFlag: false,
      editFlag: false,
      options: []
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
          let uId = this.rowData.uid
          this.$http.post('http://localhost/tbUser/remove?uId=' + uId)
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
      this.$http.post('http://localhost/tbUser/update', this.tbUser)
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
      this.$http.post('http://localhost/tbUser/save', this.tbUser)
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
    },

    closeDailog () {
      this.addFlag = false
      this.editFlag = false
      this.tbUser = {
        uid: null,
        uname: null,
        uemail: null,
        upassword: null
      }
    },
    openEditDailog () {
      if (this.rowData == null) {
        this.$message.error('请选择要修改的一行数据')
      } else {
        this.editFlag = !this.editFlag
        this.tbUser = {
          uid: this.rowData.uid,
          uname: this.rowData.uname,
          uemail: this.rowData.uemail,
          upassword: this.rowData.upassword
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
      this.$http.get('http://localhost/tbUser/listPage', {
        params: {
          page: this.pager.page,
          size: this.pager.size,
          uName: this.pager.uName
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
  .el-tooltip__popper {
    width: 300px;
    color: #777 !important;
    background: #D5DCE5 !important;
    margin-bottom: -20px !important;
  }
</style>
