<!--
 * @Author: daidai
 * @Date: 2022-02-28 16:16:42
 * @LastEditors: Please set LastEditors
 * @LastEditTime: 2022-04-28 09:46:18
 * @FilePath: \web-pc\src\pages\big-screen\view\indexs\left-center.vue
-->
<template>
  <Echart id="leftCenter" :options="options" class="left_center_inner" v-if="pageflag" ref="charts" />
  <Reacquire v-else @onclick="getData" style="line-height:200px">
    重新获取
  </Reacquire>
</template>

<script>
// import { currentGET } from 'api/modules'
export default {
  data () {
    return {
      options: {},
      countUserNumData: {
        onlineNum: 0,
        offlineNum: 0
      },
      pageflag: true,
      timer: null
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
      // this.pageflag = false
      this.$http.get('http://localhost/bigData/countJobNumberData')
        .then(res => {
          if (res.code === 200) {
            this.countUserNumData = res.data
            this.$nextTick(() => {
              this.init()
            })
          } else {
            this.pageflag = false
            this.$Message({
              text: res.msg,
              type: 'warning'
            })
          }
        })
    },
    init () {
      let total = this.countUserNumData.totalNum
      let colors = ['#56B557', '#ECA444']
      let piedata = {
        name: '就业人数总揽',
        type: 'pie',
        radius: ['42%', '65%'],
        avoidLabelOverlap: false,
        itemStyle: {
          borderRadius: 4,
          borderColor: 'rgba(0,0,0,0)',
          borderWidth: 2
        },

        color: colors,
        data: [
          {
            value: this.countUserNumData.onlineNum,
            name: '已就业',
            label: {
              shadowColor: colors[0]
            }
          },
          {
            value: this.countUserNumData.offlineNum,
            name: '未就业',
            label: {
              shadowColor: colors[1]
            }
          }

        ]
      }
      this.options = {
        title: {
          // zlevel: 0,
          text: ['{value|' + total + '}', '{name|总数}'].join('\n'),
          top: 'center',
          left: 'center',
          textStyle: {
            rich: {
              value: {
                color: '#ffffff',
                fontSize: 24,
                fontWeight: 'bold',
                lineHeight: 20
              },
              name: {
                color: '#ffffff',
                lineHeight: 20
              }
            }
          }
        },
        tooltip: {
          trigger: 'item',
          backgroundColor: 'rgba(0,0,0,.6)',
          borderColor: 'rgba(147, 235, 248, .8)',
          textStyle: {
            color: '#FFF'
          }
        },
        legend: {
          show: false,
          top: '5%',
          left: 'center'
        },
        series: [
          // 展示圆点
          {
            ...piedata,
            tooltip: { show: true },
            label: {
              formatter: '   {b|{b}}   \n   {c|{c}个}   {per|{d}%}  ',
              //   position: "outside",
              rich: {
                b: {
                  color: '#fff',
                  fontSize: 12,
                  lineHeight: 26
                },
                c: {
                  color: '#31ABE3',
                  fontSize: 14
                },
                per: {
                  color: '#31ABE3',
                  fontSize: 14
                }
              }
            },
            labelLine: {
              length: 20, // 第一段线 长度
              length2: 36, // 第二段线 长度
              show: true,
              emphasis: {
                show: true
              }
            }
          },
          {
            ...piedata,
            tooltip: { show: true },
            itemStyle: {},
            label: {
              backgroundColor: 'auto', // 圆点颜色，auto：映射的系列色
              height: 0,
              width: 0,
              lineHeight: 0,
              borderRadius: 2.5,
              shadowBlur: 8,
              shadowColor: 'auto',
              padding: [2.5, -2.5, 2.5, -2.5]
            },
            labelLine: {
              length: 20, // 第一段线 长度
              length2: 36, // 第二段线 长度
              show: false
            }
          }
        ]
      }
    }
  }
}
</script>
<style lang='scss' scoped>
</style>
