<template>

  <div>

    <el-card :body-style="{height: '75px'}">
      <el-row>
        <el-breadcrumb>
          <el-breadcrumb-item :to="{path: '/welcome'}">首页</el-breadcrumb-item>
          <el-breadcrumb-item>学生信息管理</el-breadcrumb-item>
          <el-breadcrumb-item>班级管理</el-breadcrumb-item>
        </el-breadcrumb>
      </el-row>

      <el-row :gutter="10">
        <el-col :span="6">
          <el-input placeholder="输入班级名称搜索" prefix-icon="el-icon-search" v-model="pager.clName" @change="listPage()"
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
        <el-table-column prop="clId" label="班级ID" width="70px">
        </el-table-column>
        <el-table-column prop="clName" label="班级名称">
        </el-table-column>
        <el-table-column prop="clNum" label="班级编号">
        </el-table-column>
        <el-table-column prop="clGrade" label="班级年级">
        </el-table-column>
        <el-table-column prop="tbSpecialty.spName" label="班级专业">
        </el-table-column>
        <el-table-column prop="tbDept.deName" label="所属院系">
        </el-table-column>
        <el-table-column prop="tbCollege.coName" label="所属学校">
        </el-table-column>
      </el-table>
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pager.page"
        :page-sizes="[5, 10, 15, 20]" :page-size="pager.size" layout="total, sizes, prev, pager, next, jumper"
        :total="pager.total">
      </el-pagination>

    </el-card>
    <!-- 添加对话框 -->
    <el-dialog title="添加班级" :visible.sync="addFlag" destroy-on-close>
      <el-form :model="tbClazz" :rules="rules" ref="tbClazz">
        <el-row :gutter="10">
          <el-form-item label="班级名称" prop="clName">
            <el-input v-model="tbClazz.clName"></el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="班级编号" prop="clNum">
            <el-input v-model="tbClazz.clNum"></el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="班级年级" prop="clGrade">
            <el-input v-model="tbClazz.clGrade"></el-input>
          </el-form-item>
        </el-row>
        <el-row :gutter="10">
          <el-form-item label="所属院系">
            <el-select @change="getSelectDept()" style="width: 100%;" v-model="tbClazz.tbCollege.coId"
              placeholder="请选择所属院校">
              <el-option v-for="(item, index) in tbCollege" :key="index" :label="item.coName" :value="item.coId"></el-option>
            </el-select>
            <el-select @change="getSelectSpecialty()" style="width: 100%;" v-model="tbClazz.tbDept.deId"
              placeholder="请选择院系">
              <el-option v-for="(item, index) in tbDept" :key="index" :label="item.deName" :value="item.deId"></el-option>
            </el-select>
            <el-select style="width: 100%;" v-model="tbClazz.tbSpecialty.spId" placeholder="请选择院系">
              <el-option v-for="(item, index) in tbSpecialty" :key="index" :label="item.spName" :value="item.spId"></el-option>
            </el-select>
          </el-form-item>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="closeDailog">取 消</el-button>
        <el-button type="primary" @click="save()">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 修改对话框 -->
    <el-dialog title="修改专业" :visible.sync="editFlag" destroy-on-close>
      <el-form :model="tbClazz" :rules="rules" ref="tbClazz">
        <el-row :gutter="10">
          <el-form-item label="班级名称" prop="clName">
            <el-input v-model="tbClazz.clName"></el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="班级编号" prop="clNum">
            <el-input v-model="tbClazz.clNum"></el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="班级年级" prop="clGrade">
            <el-input v-model="tbClazz.clGrade"></el-input>
          </el-form-item>
        </el-row>
        <el-row :gutter="10">
          <el-form-item label="所属院系">
            <el-select @change="getSelectDept()" style="width: 100%;" v-model="tbClazz.tbCollege.coId"
              placeholder="请选择所属院校">
              <el-option v-for="(item, index) in tbCollege" :key="index" :label="item.coName" :value="item.coId"></el-option>
            </el-select>
            <el-select @change="getSelectSpecialty()" style="width: 100%;" v-model="tbClazz.tbDept.deId"
              placeholder="请选择院系">
              <el-option v-for="(item, index) in tbDept" :key="index" :label="item.deName" :value="item.deId"></el-option>
            </el-select>
            <el-select style="width: 100%;" v-model="tbClazz.tbSpecialty.spId" placeholder="请选择院系">
              <el-option v-for="(item, index) in tbSpecialty" :key="index" :label="item.spName" :value="item.spId"></el-option>
            </el-select>
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
        clName: null
      },
      tbClazz: {
        clId: null,
        clName: null,
        clNum: null,
        clGrade: null,
        tbSpecialty: {
          spId: null,
          spName: null
        },
        tbDept: {
          deId: null,
          deName: null
        },
        tbCollege: {
          coId: null,
          coName: null
        }
      },
      rowData: null,
      addFlag: false,
      editFlag: false,
      options: [],
      tbCollege: [],
      tbDept: [],
      tbSpecialty: [],
      select1: null,
      select2: null,
      selectFlag: 'open',
      isAdmin: false,
      rules: {
        clName: [{
          required: true,
          message: '请输入班级名称',
          trigger: 'blur'
        }],
        clNum: [{
          required: true,
          message: '请输入班级编号',
          trigger: 'blur'
        }],
        clGrade: [{
          required: true,
          message: '请输入年级',
          trigger: 'blur'
        }]
      }
    }
  },
  methods: {
    clearSelect () {
      this.tbDept = null
      this.tbCollege = null
      this.tbSpecialty = null
    },
    getSelectCollege () {
      this.tbDept = null
      this.tbSpecialty = null
      this.$http.get('http://101.200.147.50/tbCollege/list')
        .then(response => {
          this.tbCollege = response.data.data
          this.getSelectDept()
        })
    },
    getSelectDept () {
      this.tbSpecialty = null
      let coId = this.tbClazz.tbCollege.coId
      if (this.selectFlag !== 'open') {
        this.tbClazz.tbDept.deId = null
      }
      this.$http.get('http://101.200.147.50/tbDept/getDeptName?coId=' + coId)
        .then(response => {
          this.tbDept = response.data.data
          this.getSelectSpecialty()
        })
    },
    getSelectSpecialty () {
      let deId = this.tbClazz.tbDept.deId
      if (this.selectFlag !== 'open') {
        this.tbClazz.tbSpecialty.spId = null
      }
      this.selectFlag = 'no'
      this.$http.get('http://101.200.147.50/tbSpecialty/getSpecialtyNames?deId=' + deId)
        .then(response => {
          this.tbSpecialty = response.data.data
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
          let clId = this.rowData.clId
          this.$http.post('http://101.200.147.50/tbClazz/remove?clId=' + clId)
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
      this.$http.post('http://101.200.147.50/tbClazz/update', this.tbClazz)
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
      this.$http.post('http://101.200.147.50/tbClazz/save', this.tbClazz)
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
      this.clearSelect()
      this.tbClazz = {
        clId: null,
        clName: null,
        clNum: null,
        clGrade: null,
        tbSpecialty: {
          spId: null,
          spName: null
        },
        tbDept: {
          deId: null,
          deName: null
        },
        tbCollege: {
          coId: null,
          coName: null
        }
      }
    },
    openEditDailog () {
      if (this.rowData == null) {
        this.$message.error('请选择要修改的一行数据')
      } else {
        this.selectFlag = 'open'
        this.editFlag = !this.editFlag
        this.tbClazz = {
          clId: this.rowData.clId,
          clName: this.rowData.clName,
          clNum: this.rowData.clNum,
          clGrade: this.rowData.clGrade,
          tbSpecialty: {
            spId: this.rowData.tbSpecialty.spId,
            spName: this.rowData.tbSpecialty.spName
          },
          tbDept: {
            deId: this.rowData.tbDept.deId,
            deName: this.rowData.tbDept.deName
          },
          tbCollege: {
            coId: this.rowData.tbCollege.coId,
            coName: this.rowData.tbCollege.coName
          }

        }
        this.getSelectCollege()
      }
    },
    openAddDailog () {
      this.addFlag = !this.addFlag
      this.getSelectCollege()
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
      this.$http.get('http://101.200.147.50/tbClazz/listPage', {
        params: {
          page: this.pager.page,
          size: this.pager.size,
          clName: this.pager.clName
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
