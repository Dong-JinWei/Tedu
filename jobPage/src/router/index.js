import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'
import Main from '../components/Main.vue'
import Welcome from '../components/Welcome.vue'
import Area from '../components/Area.vue'
import Region from '../components/Region.vue'
import Company from '../components/Company.vue'
import College from '../components/College.vue'
import Dept from '../components/Dept.vue'
import Specialty from '../components/Specialty.vue'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      component: Main,
      redirect: "/welcome",
      children: [{
        path: '/welcome',
        component: Welcome
      },{
        path: '/area',
        component: Area
      },{
        path: '/region',
        component: Region
      },{
        path: '/company',
        component: Company
      },{
        path: '/college',
        component: College
      },{
        path: '/dept',
        component: Dept
      }, {
        path: '/specialty',
        component: Specialty
      }]
    }
  ]
})
