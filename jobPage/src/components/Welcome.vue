<template>

  <div>
    <el-row :gutter="20">
      <el-col :span="18" :offset="3">
        <el-card>
          <el-carousel :interval="4000" type="card" height="200px">
            <el-carousel-item>
              <el-link href="http://localhost:8081" target="_blank">
                <img src="../assets/bigdata1.png" />
              </el-link>
            </el-carousel-item>
            <el-carousel-item>
              <el-link href="http://localhost:8081" target="_blank">
                <img src="../assets/bigdata2.png" />
              </el-link>
            </el-carousel-item>
            <el-carousel-item>
              <el-link href="http://localhost:8081" target="_blank">
                <img src="../assets/bigdata3.png" />
              </el-link>
            </el-carousel-item>
          </el-carousel>
        </el-card>
      </el-col>
    </el-row>

    <el-card>
     <div slot="header" class="clearfix">
        <span>就业薪资榜</span>
      </div>
      <el-row :gutter="10">
        <el-card v-for="(item, index) in tableData" :key="index" shadow="hover">
          <el-descriptions>
            <el-descriptions-item label="学生姓名">{{item.sname}}</el-descriptions-item>
            <el-descriptions-item label="学校">{{item.coName}}</el-descriptions-item>
            <el-descriptions-item label="薪资">{{item.jsalary}}</el-descriptions-item>
            <el-descriptions-item label="行业">{{item.diName}}</el-descriptions-item>
            <el-descriptions-item label="工作地点">{{item.reShortname}} {{item.reName}} {{item.cpAddr}}
              {{item.cpName}}
            </el-descriptions-item>
          </el-descriptions>
        </el-card>

      </el-row>
    </el-card>

  </div>

</template>

<script>
export default {
  data () {
    return {
      tableData: []
    }
  },
  methods: {
    getTop5 () {
      this.$http.get('http://101.200.147.50/tbStudent/getTop5')
        .then(response => {
          if (response.data.code === 200) {
            this.tableData = response.data.data
          }
        }).catch(e => {
          this.$message.error('网络错误')
        })
    }

  },
  mounted () {
    this.getTop5()
  }
}
</script>

<style>
  /* 轮播图 */
  .el-carousel__item h3 {
    /* color: #475669; */
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    /* background-color: #99a9bf; */
  }

  .el-carousel__item:nth-child(2n+1) {
    /* background-color: #d3dce6; */
  }

  img {
    width: 70%;
  }
</style>
