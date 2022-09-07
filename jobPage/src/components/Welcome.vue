<template>

  <div>
  <el-row :gutter="20">
    <el-col :span="18" :offset="3">
      <el-card>
          <el-carousel :interval="4000" type="card" height="200px">
            <el-carousel-item v-for="item in 6" :key="item">
              <h3 class="medium">{{ item }}</h3>
            </el-carousel-item>
          </el-carousel>
      </el-card>
    </el-col>
  </el-row>


  <el-row :gutter="10">
    <el-col :span="6"><el-card ></el-card></el-col>
    <el-col :span="6"><el-card ></el-card></el-col>
    <el-col :span="6"><el-card ></el-card></el-col>
    <el-col :span="6"><el-card ></el-card></el-col>
  </el-row>
  <el-row :gutter="10">
    <el-col :span="6"><el-card ></el-card></el-col>
    <el-col :span="6"><el-card ></el-card></el-col>
    <el-col :span="6"><el-card ></el-card></el-col>
    <el-col :span="6"><el-card ></el-card></el-col>
  </el-row>

  <el-row :gutter="10">
    <el-col :span="6"><el-card ></el-card></el-col>
    <el-col :span="6"><el-card ></el-card></el-col>
    <el-col :span="6"><el-card ></el-card></el-col>
    <el-col :span="6"><el-card ></el-card></el-col>
  </el-row>

  </div>




</template>

<script>
</script>

<style>

  /* 轮播图 */
  .el-carousel__item h3 {
      color: #475669;
      font-size: 14px;
      opacity: 0.75;
      line-height: 200px;
      margin: 0;
    }

    .el-carousel__item:nth-child(2n) {
      background-color: #99a9bf;
    }

    .el-carousel__item:nth-child(2n+1) {
      background-color: #d3dce6;
    }
</style>
