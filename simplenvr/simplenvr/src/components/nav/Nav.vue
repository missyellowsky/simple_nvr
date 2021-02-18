<template>
  <div>
    <el-row class="tac" style="z-index: 2">
      <el-col :span="24" >
        <el-menu
          class="el-menu-vertical-demo"
          router
          unique-opened
          @open="handleOpen"
          @close="handleClose"
          background-color="rgba(216,216,216,0.1)"
          text-color="#eee"
          active-text-color="#aaa"
        >

          <el-submenu v-for="item in menu" :index="item.id" :key="item.id" >
            <template slot="title">
              <icon :name="item.icon"></icon>
              <span v-text="item.name"></span>
            </template>
            <el-menu-item-group class="over-hide" v-for="sub in item.sub" :key="sub.componentName" >
              <el-menu-item :index="sub.componentName" v-text="sub.name">
              </el-menu-item>
            </el-menu-item-group>
          </el-submenu>

        </el-menu>
      </el-col>
    </el-row>

  </div>
</template>

<style scoped>
  .over-hide{
    overflow: hidden;
  }

</style>

<script>
  import menu from '@/config/menu-config'

  export default {
    data () {
      return {
        menu: menu
      }
    },
    methods: {
      handleOpen (key, keyPath) {
        console.log(key, keyPath)
      },
      handleClose (key, keyPath) {
        console.log(key, keyPath)
      }
    }
  }
</script>
