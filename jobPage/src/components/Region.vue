<template>

  <div>

    <el-card :body-style="{height: '75px'}">
      <el-row>
        <el-breadcrumb>
          <el-breadcrumb-item :to="{path: '/welcome'}">首页</el-breadcrumb-item>
          <el-breadcrumb-item>企业管理</el-breadcrumb-item>
          <el-breadcrumb-item>省/市/区管理</el-breadcrumb-item>
        </el-breadcrumb>
      </el-row>

      <el-row :gutter="10">
        <el-col :span="6">
          <el-input placeholder="根据区域或省份名称搜索" prefix-icon="el-icon-search" v-model="pager.reName" @change="listPage()"
            clearable>
          </el-input>
        </el-col>
        <el-col :span="6">
          <el-button plain icon="el-icon-search"></el-button>
          <el-button plain icon="el-icon-plus" @click="openAddDailog()"></el-button>
          <el-button plain icon="el-icon-edit" @click="openEditDailog()"></el-button>
          <!-- <el-button plain icon="el-icon-delete" @click="remove()"></el-button> -->
        </el-col>
      </el-row>
    </el-card>

    <el-card style="margin-top: 20px;">
      <el-table :data="tableData" stripe style="width: 100%" highlight-current-row @current-change="selectOneRow">
        <el-table-column prop="reId" label="省/市/区ID">
        </el-table-column>
        <el-table-column prop="reName" label="地区名称">
        </el-table-column>
        <el-table-column prop="reShortname" label="所属省份">
        </el-table-column>
        <el-table-column prop="reCode" label="行政区域">
        </el-table-column>
        <el-table-column prop="reLevel" label="地区级别">
        </el-table-column>
        <el-table-column prop="reParentId" label="上级地区编号">
        </el-table-column>
        <el-table-column prop="tbArea.arName" label="所在区域">
        </el-table-column>
      </el-table>
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pager.page"
        :page-sizes="[5, 10, 15, 20]" :page-size="pager.size" layout="total, sizes, prev, pager, next, jumper"
        :total="pager.total">
      </el-pagination>

    </el-card>

    <!-- 添加对话框 -->
    <el-dialog title="添加地区" :visible.sync="addFlag" destroy-on-close>

      <el-form :rules="rules" ref="tbRegion" :model="tbRegion">
        <el-row :gutter="10">
          <el-col :span="12">

            <el-form-item label="地区名称" prop="arName">
              <el-input v-model="tbRegion.reName"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">

            <el-form-item label="所属省份" prop="arDesc">
              <el-input v-model="tbRegion.reShortname"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="10">
          <el-col :span="12">
            <el-form-item label="行政区域" prop="reCode">
              <el-input v-model="tbRegion.reCode"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="地区级别" prop="reLevel">
              <el-input v-model="tbRegion.reLevel"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col :span="12">
            <el-form-item label="上级地区编号" prop="reParentId">
              <el-input v-model="tbRegion.reParentId"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="区域编号">
              <el-select v-model="tbRegion.tbArea.arId" placeholder="请选择所在区域">
                <el-option v-for="item in tbAreaData" :label="item.arName" :value="item.arId"></el-option>
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
    <el-dialog title="修改地区" :visible.sync="editFlag" destroy-on-close>

      <el-form :rules="rules" ref="tbRegion" :model="tbRegion">
        <el-row :gutter="10">
          <el-col :span="12">
            <el-form-item label="地区名称" prop="arName">
              <el-input v-model="tbRegion.reName"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="所属省份" prop="arDesc">
              <el-input v-model="tbRegion.reShortname"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col :span="12">
            <el-form-item label="行政区域" prop="reCode">
              <el-input v-model="tbRegion.reCode"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="地区级别" prop="reLevel">
              <el-input v-model="tbRegion.reLevel"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col :span="12">
            <el-form-item label="上级地区编号" prop="reParentId">
              <el-input v-model="tbRegion.reParentId"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="区域编号">
              <el-select v-model="tbRegion.tbArea.arId" placeholder="请选择所在区域">
                <el-option v-for="item in tbAreaData" :label="item.arName" :value="item.arId"></el-option>
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
      tbAreaData: [],
      tableData: [],
      pager: {
        page: 1,
        size: 10,
        total: 0,
        reName: null
      },
      tbRegion: {
        reId: null,
        reName: null,
        reShortname: null,
        recode: null,
        reLevel: null,
        reParentId: null,
        tbArea: {
          arId: null,
          arName: null
        }
      },
      rowData: null,
      addFlag: false,
      editFlag: false,
      rules: {
        reName: [{
          required: true,
          message: '请输入地区名称',
          trigger: 'blur'
        }],
        reShortname: [{
          required: true,
          message: '请输入地区介绍',
          trigger: 'blur'
        }]
      }
    }
  },
  methods: {
    remove () {
    },
    update () {
      this.$refs['tbRegion'].validate(valid => {
        if (valid) {
          this.$http.post('http://localhost/tbRegion/update', this.tbRegion)
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
        } else {
          this.$message.error('请将表单填写完整')
        }
      })
    },
    save () {
      this.$refs['tbRegion'].validate(valid => {
        if (valid) {
          this.$http.post('http://localhost/tbRegion/save', this.tbRegion)
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
        } else {
          this.$message.error('请将表单填写完整')
        }
      })
    },
    closeDailog () {
      this.addFlag = false
      this.editFlag = false
      this.tbRegion = {
        reId: null,
        reName: null,
        reShortname: null,
        recode: null,
        reLevel: null,
        reParentId: null,
        tbArea: {
          arId: null,
          arName: null
        }
      }
    },
    openEditDailog () {
      if (this.rowData == null) {
        this.$message.error('请选择要修改的一行数据')
      } else {
        this.editFlag = !this.editFlag
        this.getTbAreaList()
        this.tbRegion = {
          reId: this.rowData.reId,
          reName: this.rowData.reName,
          reShortname: this.rowData.reShortname,
          reCode: this.rowData.reCode,
          reLevel: this.rowData.reLevel,
          reParentId: this.rowData.reParentId,
          tbArea: {
            arId: this.rowData.tbArea.arId,
            arName: this.rowData.tbArea.arName
          }
        }
      }
    },
    openAddDailog () {
      this.addFlag = !this.addFlag
      this.getTbAreaList()
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
    getTbAreaList () {
      this.$http.get('http://localhost/tbArea/list')
        .then(response => {
          this.tbAreaData = response.data.data
        })
    },
    listPage () {
      this.$http.get('http://localhost/tbRegion/listPage', {
        params: {
          page: this.pager.page,
          size: this.pager.size,
          reName: this.pager.reName
        }
      }).then(response => {
        this.pager.total = response.data.data.total
        this.tableData = response.data.data.rows
      }).catch(error => {
        console.log(error)
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
