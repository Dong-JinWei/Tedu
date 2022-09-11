<template>

  <div>

    <el-card :body-style="{height: '75px'}">
      <el-row>
        <el-breadcrumb>
          <el-breadcrumb-item :to="{path: '/welcome'}">首页</el-breadcrumb-item>
          <el-breadcrumb-item>就业信息管理</el-breadcrumb-item>
          <el-breadcrumb-item>就业信息</el-breadcrumb-item>
        </el-breadcrumb>
      </el-row>

      <el-row :gutter="10">
        <el-col :span="6">
          <el-input placeholder="输入关键字查询" prefix-icon="el-icon-search" v-model="pager.keyword" @change="listPage()"
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
        <el-table-column prop="jid" label="就业ID" width="90">
        </el-table-column>
        <el-table-column prop="tbStudent.sname" label="学生姓名">
        </el-table-column>
        <el-table-column prop="tbCompany.cpName" label="公司名称">
        </el-table-column>
        <el-table-column prop="jsalary" label="薪资">
        </el-table-column>
        <el-table-column prop="tbDirection.diName" label="就业方向">
        </el-table-column>
        <el-table-column prop="tbProtocol.proUrl" label="协议">
          <template slot-scope="scope">
             <el-link :href="scope.row.tbProtocol.proUrl" target="_blank">{{scope.row.tbProtocol.proName}}</el-link>
          </template>
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
          <el-col :span="12">
            <el-form-item label="学生姓名">
              <el-select style="width: 100%;" v-model="tbJob.tbStudent.sid" filterable placeholder="请选择">
                <el-option v-for="item in tbStudent" :key="item.index" :label="item.sname" :value="item.sid">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="公司名称">
              <el-select style="width: 100%;" v-model="tbJob.tbCompany.cpId" filterable placeholder="请选择">
                <el-option v-for="item in tbCompany" :key="item.index" :label="item.cpName" :value="item.cpId">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col :span="12">
            <el-form-item label="薪资">
              <el-input v-model="tbJob.jsalary"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="就业方向">
              <el-select style="width: 100%;" v-model="tbJob.tbDirection.diId" filterable placeholder="请选择">
                <el-option v-for="item in tbDirection" :key="item.index" :label="item.diName" :value="item.diId">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="协议文件">
          <el-row>
            <el-col>
              <el-upload style="margin-bottom: -30px;" drag action="http://localhost/tbJob/upload" multiple>
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
              </el-upload>
            </el-col>
          </el-row>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" style="margin-top: -30px;">
        <el-button @click="closeDailog">取 消</el-button>
        <el-button type="primary" @click="save()">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 修改对话框-->
    <el-dialog title="修改学校" :visible.sync="editFlag" destroy-on-close>
      <el-form>
        <el-row :gutter="10">
          <el-col :span="12">
            <el-form-item label="学生姓名">
              <el-select style="width: 100%;" v-model="tbJob.tbStudent.sid" filterable placeholder="请选择">
                <el-option v-for="item in tbStudent" :key="item.index" :label="item.sname" :value="item.sid">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="公司名称">
              <el-select style="width: 100%;" v-model="tbJob.tbCompany.cpId" filterable placeholder="请选择">
                <el-option v-for="item in tbCompany" :key="item.index" :label="item.cpName" :value="item.cpId">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col :span="12">
            <el-form-item label="薪资">
              <el-input v-model="tbJob.jsalary"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="就业方向">
              <el-select style="width: 100%;" v-model="tbJob.tbDirection.diId" filterable placeholder="请选择">
                <el-option v-for="item in tbDirection" :key="item.index" :label="item.diName" :value="item.diId">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="协议文件">
          <el-row>
            <el-col>
              <el-upload style="margin-bottom: -30px;" drag action="http://localhost/tbJob/upload" multiple>
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
              </el-upload>
            </el-col>
          </el-row>
        </el-form-item>
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
        keyword: null
      },
      rowData: null,
      addFlag: false,
      editFlag: false,
      tbJob: {
        jId: null,
        jSalary: null,
        tbStudent: {
          sId: null,
          sName: null
        },
        tbCompany: {
          cpId: null,
          cpName: null
        },
        tbDirection: {
          diId: null,
          diName: null
        },
        tbProtocol: {
          proId: null,
          proName: null,
          proUrl: null
        }
      },
      tbStudent: [],
      tbCompany: [],
      tbDirection: [],
      options: [],
      isAdmin: false
    }
  },
  methods: {
    getDirection () {
      this.$http.get('http://localhost/tbDirection/list')
        .then(response => {
          this.tbDirection = response.data.data
        })
    },
    getCompanyList () {
      this.$http.get('http://localhost/tbCompany/list')
        .then(response => {
          this.tbCompany = response.data.data
        })
    },
    getStudentList () {
      this.$http.get('http://localhost/tbStudent/list')
        .then(response => {
          this.tbStudent = response.data.data
        })
    },

    remove () {
      if (this.rowData == null) {
        this.$message.error('请选择要删除的一行数据')
      } else {
        this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let jId = this.rowData.jid
          this.$http.post('http://localhost/tbJob/remove?jId=' + jId)
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
      this.$http.post('http://localhost/tbJob/update', this.tbJob)
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
      this.$http.post('http://localhost/tbJob/save', this.tbJob)
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
      this.tbJob = {
        jid: null,
        jsalary: null,
        tbStudent: {
          sid: null,
          sname: null
        },
        tbCompany: {
          cpId: null,
          cpName: null
        },
        tbDirection: {
          diId: null,
          diName: null
        },
        tbProtocol: {
          proId: null,
          proName: null,
          proUrl: null
        }
      }
    },
    openEditDailog () {
      if (this.rowData == null) {
        this.$message.error('请选择要修改的一行数据')
      } else {
        this.editFlag = !this.editFlag
        this.tbJob = {
          jid: this.rowData.jid,
          jsalary: this.rowData.jsalary,
          tbStudent: {
            sid: this.rowData.tbStudent.sid,
            sname: this.rowData.tbStudent.sname
          },
          tbCompany: {
            cpId: this.rowData.tbCompany.cpId,
            cpName: this.rowData.tbCompany.cpName
          },
          tbDirection: {
            diId: this.rowData.tbDirection.diId,
            diName: this.rowData.tbDirection.diName
          },
          tbProtocol: {
            proId: this.rowData.tbProtocol.proId,
            proName: this.rowData.tbProtocol.proName,
            proUrl: this.rowData.tbProtocol.proUrl
          }
        }
      }
    },
    openAddDailog () {
      this.addFlag = !this.addFlag
      this.getStudentList()
      this.getCompanyList()
      this.getDirection()
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
      this.$http.get('http://localhost/tbJob/listPage', {
        params: {
          page: this.pager.page,
          size: this.pager.size,
          keyword: this.pager.keyword
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
</style>
