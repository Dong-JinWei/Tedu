<!--
 * @Author: daidai
 * @Date: 2022-02-28 16:16:42
 * @LastEditors: Please set LastEditors
 * @LastEditTime: 2022-07-20 17:57:11
 * @FilePath: \web-pc\src\pages\big-screen\view\indexs\left-center.vue
-->
<template>
    <ul class="user_Overview flex" v-if="pageflag">
        <li class="user_Overview-item" style="color: #00fdfa">
            <div class="user_Overview_nums allnum ">
                <dv-digital-flop :config="config" style="width:100%;height:100%;" />
            </div>
            <p>院校数</p>
        </li>
        <li class="user_Overview-item" style="color: #07f7a8">
            <div class="user_Overview_nums online">
                <dv-digital-flop :config="onlineconfig" style="width:100%;height:100%;" />
            </div>
            <p>公司数</p>
        </li>
        <li class="user_Overview-item" style="color: #e3b337">
            <div class="user_Overview_nums offline">
                <dv-digital-flop :config="offlineconfig" style="width:100%;height:100%;" />

            </div>
            <p>学生数</p>
        </li>
        <li class="user_Overview-item" style="color: #f5023d">
            <div class="user_Overview_nums laramnum">
                <dv-digital-flop :config="laramnumconfig" style="width:100%;height:100%;" />
            </div>
            <p>行业数</p>
        </li>
    </ul>
    <Reacquire v-else @onclick="getData" line-height="200px">
        重新获取
    </Reacquire>
</template>

<script>
// import { currentGET } from 'api/modules'
let style = {
  fontSize: 24
}
export default {
  data () {
    return {
      options: {},
      userOverview: {
        companyNum: 0,
        collegeNum: 0,
        studentNum: 0,
        directionNum: 0
      },
      pageflag: true,
      timer: null,
      config: {
        number: [100],
        content: '{nt}',
        style: {
          ...style,
          // stroke: "#00fdfa",
          fill: '#00fdfa'
        }
      },
      onlineconfig: {
        number: [0],
        content: '{nt}',
        style: {
          ...style,
          // stroke: "#07f7a8",
          fill: '#07f7a8'
        }
      },
      offlineconfig: {
        number: [0],
        content: '{nt}',
        style: {
          ...style,
          // stroke: "#e3b337",
          fill: '#e3b337'
        }
      },
      laramnumconfig: {
        number: [0],
        content: '{nt}',
        style: {
          ...style,
          // stroke: "#f5023d",
          fill: '#f5023d'
        }
      }

    }
  },
  filters: {
    numsFilter (msg) {
      return msg || 0
    }
  },
  created () {
    this.getData()
  },
  mounted () {
  },
  beforeDestroy () {
    this.clearData()
  },
  methods: {
    clearData () {
      if (this.timer) {
        clearInterval(this.timer)
        this.timer = null
      }
    },
    getData () {
      this.pageflag = true

      this.$http.get('http://101.200.147.50/bigData/userOverview').then(res => {
        if (res.code === 200) {
          this.userOverview = res.data
          this.onlineconfig = {
            ...this.onlineconfig,
            number: [this.userOverview.companyNum]
          }
          this.config = {
            ...this.config,
            number: [this.userOverview.collegeNum]
          }
          this.offlineconfig = {
            ...this.offlineconfig,
            number: [this.userOverview.studentNum]
          }
          this.laramnumconfig = {
            ...this.laramnumconfig,
            number: [this.userOverview.directionNum]
          }
        } else {
          this.pageflag = false
          this.$Message.warning(res.msg)
        }
      })
    }
  }
}
</script>
<style lang='scss' scoped>
.user_Overview {
    li {
        flex: 1;

        p {
            text-align: center;
            height: 16px;
            font-size: 16px;
        }

        .user_Overview_nums {
            width: 100px;
            height: 100px;
            text-align: center;
            line-height: 100px;
            font-size: 22px;
            margin: 50px auto 30px;
            background-size: cover;
            background-position: center center;
            position: relative;

            &::before {
                content: '';
                position: absolute;
                width: 100%;
                height: 100%;
                top: 0;
                left: 0;
            }

            &.bgdonghua::before {
                animation: rotating 14s linear infinite;
            }
        }

        .allnum {

            // background-image: url("../../assets/img/left_top_lan.png");
            &::before {
                background-image: url("../../assets/img/left_top_lan.png");

            }
        }

        .online {
            &::before {
                background-image: url("../../assets/img/left_top_lv.png");

            }
        }

        .offline {
            &::before {
                background-image: url("../../assets/img/left_top_huang.png");

            }
        }

        .laramnum {
            &::before {
                background-image: url("../../assets/img/left_top_hong.png");

            }
        }
    }
}
</style>
