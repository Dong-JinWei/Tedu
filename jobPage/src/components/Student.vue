<template>

  <div>

    <el-card :body-style="{height: '75px'}">
      <el-row>
        <el-breadcrumb>
          <el-breadcrumb-item :to="{path: '/welcome'}">首页</el-breadcrumb-item>
          <el-breadcrumb-item>学生信息管理</el-breadcrumb-item>
          <el-breadcrumb-item>学生信息</el-breadcrumb-item>
        </el-breadcrumb>
      </el-row>

      <el-row :gutter="10">
        <el-col :span="6">
          <el-input placeholder="输入班级名称搜索" prefix-icon="el-icon-search" v-model="pager.sName" @change="listPage()"
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
        <el-table-column prop="sid" label="学生ID" width="70px">
        </el-table-column>
        <el-table-column prop="sname" label="学生姓名">
        </el-table-column>
        <el-table-column prop="snum" label="学生学号">
        </el-table-column>
        <el-table-column prop="ssex" label="学生性别">
        </el-table-column>
        <el-table-column prop="sbirthday" label="出生日期">
        </el-table-column>
        <el-table-column prop="sphoto" label="学生照片">
          <template slot-scope="scope">
            <el-avatar shape="square" :size="50" :src="scope.row.sphoto"></el-avatar>
          </template>
        </el-table-column>
        <el-table-column prop="tbClazz.clName" label="所属班级">
        </el-table-column>
        <el-table-column prop="tbSpecialty.spName" label="所属专业">
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
    <el-dialog title="添加学生" :visible.sync="addFlag" destroy-on-close>
      <el-form>
        <el-row :gutter="10">
          <el-col :span="12">
            <el-form-item label="学生姓名">
              <el-input v-model="tbStudent.sname"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="学生学号">
              <el-input v-model="tbStudent.snum"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col :span="12">
            <el-form-item label="学生性别">
              <el-select style="width: 100%;" v-model="tbStudent.ssex" placeholder="性别">
                <el-option label="男" value="男"></el-option>
                <el-option label="女" value="女"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="出生日期">
              <el-date-picker v-model="tbStudent.sbirthday" type="date" placeholder="选择日期" format="yyyy 年 MM 月 dd 日"
                value-format="yyyy-MM-dd" style="width: 100%;" :picker-options="pickerOptions">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="学生照片">
          <el-upload t class="avatar-uploader" action="http://localhost/tbStudent/getImagesUrl" :data="tbStudent"
            :on-success="handleAvatarSuccess" :show-file-list="false">
            <img v-if="tbStudent.sphoto" :src="tbStudent.sphoto" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-row :gutter="10">
          <el-col :span="12">
            <el-form-item label="院校">
              <el-select @change="getSelectDept()" style="width: 100%;" v-model="tbStudent.tbCollege.coId"
                placeholder="请选择所属院校">
                <el-option v-for="item in tbCollege" :label="item.coName" :value="item.coId"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="院系">
              <el-select @change="getSelectSpecialty()" style="width: 100%;" v-model="tbStudent.tbDept.deId"
                placeholder="请选择院系">
                <el-option v-for="item in tbDept" :label="item.deName" :value="item.deId"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col :span="12">
            <el-form-item label="专业">
              <el-select @change="getSelectClazz()" style="width: 100%;" v-model="tbStudent.tbSpecialty.spId"
                placeholder="请选择专业">
                <el-option v-for="item in tbSpecialty" :label="item.spName" :value="item.spId"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="班级">
              <el-select style="width: 100%;" v-model="tbStudent.tbClazz.clId" placeholder="请选择班级">
                <el-option v-for="item in tbClazz" :label="item.clName" :value="item.clId"></el-option>
              </el-select>
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
    <el-dialog title="修改专业" :visible.sync="editFlag" destroy-on-close>
      <el-form>
        <el-row :gutter="10">
          <el-col :span="12">
            <el-form-item label="学生姓名">
              <el-input v-model="tbStudent.sname"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="学生学号">
              <el-input v-model="tbStudent.snum"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col :span="12">
            <el-form-item label="学生性别">
              <el-select style="width: 100%;" v-model="tbStudent.ssex" placeholder="性别">
                <el-option label="男" value="男"></el-option>
                <el-option label="女" value="女"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="出生日期">
              <el-date-picker v-model="tbStudent.sbirthday" type="date" placeholder="选择日期" format="yyyy 年 MM 月 dd 日"
                value-format="yyyy-MM-dd" style="width: 100%;" :picker-options="pickerOptions">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="学生照片">
          <el-upload t class="avatar-uploader" action="http://localhost/tbStudent/uploadImages" :data="tbStudent"
            :on-success="handleAvatarSuccess" :show-file-list="false">
            <img v-if="tbStudent.sphoto" :src="tbStudent.sphoto" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>

        <el-row :gutter="10">
          <el-col :span="12">
            <el-form-item label="院校">
              <el-select @change="getSelectDept()" style="width: 100%;" v-model="tbStudent.tbCollege.coId"
                placeholder="请选择所属院校">
                <el-option v-for="item in tbCollege" :label="item.coName" :value="item.coId"></el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="院系">
              <el-select @change="getSelectSpecialty()" style="width: 100%;" v-model="tbStudent.tbDept.deId"
                placeholder="请选择院系">
                <el-option v-for="item in tbDept" :label="item.deName" :value="item.deId"></el-option>
              </el-select>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row :gutter="10">
          <el-col :span="12">
            <el-form-item label="专业">
              <el-select @change="getSelectClazz()" style="width: 100%;" v-model="tbStudent.tbSpecialty.spId"
                placeholder="请选择专业">
                <el-option v-for="item in tbSpecialty" :label="item.spName" :value="item.spId"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="班级">
              <el-select style="width: 100%;" v-model="tbStudent.tbClazz.clId" placeholder="请选择班级">
                <el-option v-for="item in tbClazz" :label="item.clName" :value="item.clId"></el-option>
              </el-select>
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
      pickerOptions: {
        disabledDate (time) {
          return time.getTime() > Date.now()
        }
      },
      pager: {
        page: 1,
        size: 10,
        total: 0,
        sName: null
      },
      tbStudent: {
        sid: null,
        sname: null,
        snum: null,
        ssex: null,
        sbirthday: null,
        sphoto: null,
        tbClazz: {
          clId: null,
          clName: null
        },
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
      tbClazz: [],
      select1: null,
      select2: null,
      selectFlag: 'open',
      isAdmin: false
    }
  },
  methods: {
    // 上传成功后，显示图片
    handleAvatarSuccess (res, file) {
      this.tbStudent.sphoto = URL.createObjectURL(file.raw)
    },
    clearSelect () {
      this.tbDept = null
      this.tbCollege = null
      this.tbSpecialty = null
      this.tbClazz = null
    },
    getSelectCollege () {
      this.tbDept = null
      this.tbSpecialty = null
      this.$http.get('http://localhost/tbCollege/list')
        .then(response => {
          this.tbCollege = response.data.data
          this.getSelectDept()
        })
    },
    getSelectDept () {
      this.tbSpecialty = null
      this.tbClazz = null
      let coId = this.tbStudent.tbCollege.coId
      if (this.selectFlag !== 'open') {
        this.tbStudent.tbDept.deId = null
      }
      this.$http.get('http://localhost/tbDept/getDeptName?coId=' + coId)
        .then(response => {
          this.tbDept = response.data.data
          this.getSelectSpecialty()
        })
    },
    getSelectSpecialty () {
      let deId = this.tbStudent.tbDept.deId
      if (this.selectFlag !== 'open') {
        this.tbStudent.tbSpecialty.spId = null
      }
      this.$http.get('http://localhost/tbSpecialty/getSpecialtyNames?deId=' + deId)
        .then(response => {
          this.tbSpecialty = response.data.data
          this.getSelectClazz()
        })
    },
    getSelectClazz () {
      let spId = this.tbStudent.tbSpecialty.spId
      if (this.selectFlag !== 'open') {
        this.tbStudent.tbClazz.clId = null
      }
      this.$http.get('http://localhost/tbClazz/getClazzNames?spId=' + spId)
        .then(response => {
          this.tbClazz = response.data.data
          this.selectFlag = 'no'
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
          let sId = this.rowData.sid
          this.$http.post('http://localhost/tbStudent/remove?sId=' + sId)
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
      this.$http.post('http://localhost/tbStudent/update', this.tbStudent)
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
      this.$http.post('http://localhost/tbStudent/save', this.tbStudent)
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
      this.tbStudent = {
        sid: null,
        sname: null,
        snum: null,
        ssex: null,
        sbirthday: null,
        sphoto: null,
        tbClazz: {
          clId: null,
          clName: null
        },
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
        this.tbStudent = {
          sid: this.rowData.sid,
          sname: this.rowData.sname,
          snum: this.rowData.snum,
          ssex: this.rowData.ssex,
          sbirthday: this.rowData.sbirthday,
          sphoto: this.rowData.sphoto,
          tbClazz: {
            clId: this.rowData.tbClazz.clId,
            clName: this.rowData.tbClazz.clName
          },
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
      this.$http.get('http://localhost/tbStudent/listPage', {
        params: {
          page: this.pager.page,
          size: this.pager.size,
          sName: this.pager.sName
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
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }

  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }

  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
  .el-form-item{
      margin-bottom: 0px;
  }

</style>
