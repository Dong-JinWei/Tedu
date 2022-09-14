<template>

  <div>

    <el-card :body-style="{height: '75px'}">
      <el-row>
        <el-breadcrumb>
          <el-breadcrumb-item :to="{path: '/welcome'}">首页</el-breadcrumb-item>
          <el-breadcrumb-item>企业信息管理</el-breadcrumb-item>
          <el-breadcrumb-item>企业管理</el-breadcrumb-item>
        </el-breadcrumb>
      </el-row>

      <el-row :gutter="10">
        <el-col :span="6">
          <el-input placeholder="输入公司名搜索" prefix-icon="el-icon-search" v-model="pager.cpName" @change="listPage()"
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
        <el-table-column prop="cpId" label="企业ID" width="70px">
        </el-table-column>
        <el-table-column prop="cpName" label="企业名称">
        </el-table-column>
        <el-table-column prop="cpDesc" label="企业描述">
          <template slot-scope="scope">
            <el-tooltip :content="scope.row.cpDesc" placement="top">
              <div style="overflow: hidden;
              display: -webkit-box;
              text-overflow: ellipsis;
              -webkit-line-clamp: 2;
              -webkit-box-orient: vertical
              white-space: normal">
                {{scope.row.cpDesc}}
              </div>
            </el-tooltip>
          </template>
        </el-table-column>
        <el-table-column prop="tbRegion.reName" label="所在地区" width="120px">
        </el-table-column>
        <el-table-column prop="cpAddr" label="所在地址">
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
    <el-dialog title="添加企业" :visible.sync="addFlag" destroy-on-close>
      <el-form :model="tbCompany" :rules="rules" ref="tbCompany">
        <el-row :gutter="10">
          <el-col :span="12">
            <el-form-item label="企业名称" prop="cpName">
              <el-input v-model="tbCompany.cpName"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="企业描述" prop="cpDesc">
              <el-input v-model="tbCompany.cpDesc"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col :span="12">
            <el-form-item label="所在地区">
              <el-cascader v-model="tbCompany.tbRegion.reId" placeholder="选择或搜索所在地区" :options="options" filterable>
              </el-cascader>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="所在地址" prop="cpAddr">
              <el-input v-model="tbCompany.cpAddr"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="closeDailog">取 消</el-button>
        <el-button type="primary" @click="save()">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 修改对话框 -->
    <el-dialog title="修改企业" :visible.sync="editFlag" destroy-on-close>
      <el-form :model="tbCompany" :rules="rules" ref="tbCompany">
        <el-row :gutter="10">
          <el-col :span="12">
            <el-form-item label="企业名称" prop="cpName">
              <el-input v-model="tbCompany.cpName"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="企业描述" prop="cpDesc">
              <el-input v-model="tbCompany.cpDesc"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col :span="12">
            <el-form-item label="所在地区">
              <el-cascader v-model="tbCompany.tbRegion.reId" placeholder="选择或搜索所在地区" :options="options" filterable>
              </el-cascader>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="所在地址" prop="cpAddr">
              <el-input v-model="tbCompany.cpAddr"></el-input>
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
        cpName: null
      },
      tbCompany: {
        cpId: null,
        cpName: null,
        cpDesc: null,
        cpAddr: null,
        createTime: null,
        modifyTime: null,
        deleted: null,
        tbRegion: {
          reId: null,
          reName: null
        }
      },
      rowData: null,
      addFlag: false,
      editFlag: false,
      options: [],
      rules: {
        cpName: [{
          required: true,
          message: '请输入企业名称',
          trigger: 'blur'
        }],
        cpDesc: [{
          required: true,
          message: '请输入描述',
          trigger: 'blur'
        }],
        cpAddr: [{
          required: true,
          message: '请输入所在地址',
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
          let cpId = this.rowData.cpId
          this.$http.post('http://101.200.147.50/tbCompany/remove?cpId=' + cpId)
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
      this.tbCompany.tbRegion.reId = this.tbCompany.tbRegion.reId[1]
      this.$http.post('http://101.200.147.50/tbCompany/update', this.tbCompany)
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
      this.tbCompany.tbRegion.reId = this.tbCompany.tbRegion.reId[1]
      console.log(this.tbCompany.tbRegion.reId)
      this.$http.post('http://101.200.147.50/tbCompany/save', this.tbCompany)
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
    getRegion () {
      this.$http.get('http://101.200.147.50/tbRegion/getRegion')
        .then(response => {
          this.options = response.data.data
        })
    },
    closeDailog () {
      this.addFlag = false
      this.editFlag = false
      this.tbCompany = {
        cpId: null,
        cpName: null,
        cpDesc: null,
        cpAddr: null,
        createTime: null,
        modifyTime: null,
        deleted: null,
        tbRegion: {
          reId: null,
          reName: null
        }
      }
    },
    openEditDailog () {
      if (this.rowData == null) {
        this.$message.error('请选择要修改的一行数据')
      } else {
        this.editFlag = !this.editFlag
        this.getRegion()
        console.log(this.rowData)
        this.tbCompany = {
          cpId: this.rowData.cpId,
          cpName: this.rowData.cpName,
          cpDesc: this.rowData.cpDesc,
          cpAddr: this.rowData.cpAddr,
          tbRegion: {
            reId: this.rowData.tbRegion.reId,
            reName: this.rowData.tbRegion.reName
          }
        }
      }
    },
    openAddDailog () {
      this.addFlag = !this.addFlag
      this.getRegion()
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
      this.$http.get('http://101.200.147.50/tbCompany/listPage', {
        params: {
          page: this.pager.page,
          size: this.pager.size,
          cpName: this.pager.cpName
        }
      }).then(response => {
        this.pager.total = response.data.data.total
        this.tableData = response.data.data.rows
      }).catch(error => {
        this.$message.error(error)
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
