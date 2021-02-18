<template>
  <div id="app" style="background-color: black; height: 100%;">
      <div style="position: relative">
        <div style="position: absolute;width: 100%">
         <el-container style="z-index: 1;">
            <el-header class="header" :style="backgroundDiv" style="filter:alpha(Opacity=50);-moz-opacity:0.5;opacity: 0.5;z-index: 2;">
              <span style="font-family: STKaiti; font-style:oblique;font-size: 24px">simple-nvr</span>
            </el-header>
            <el-container style="z-index: 1;">
              <el-aside width="200px" style="background-color: none">
                <navmenu></navmenu>
              </el-aside>
              <el-main>
                <router-view></router-view>
              </el-main>
            </el-container>
          </el-container>
        </div>
        <div style="position: absolute; width: 100%; height: 960px">
          <vue-particles
            id="particles-js"
            color="#409EFF"
            :particleOpacity="0.4"
            :particlesNumber="40"
            shapeType="circle"
            :particleSize="6"
            linesColor="#409EFF"
            :linesWidth="1"
            :lineLinked="true"
            :lineOpacity="0.4"
            :linesDistance="150"
            :moveSpeed="2"
            :hoverEffect="true"
            hoverMode="grab"
            :clickEffect="true"
            clickMode="repulse"
          >
          </vue-particles>
        </div>
      </div>
  </div>
</template>

<script>
  import Nav from './components/nav/Nav.vue'
export default {
  name: 'app',
  data(){
    return {
      backgroundDiv: {

        backgroundImage:'url(' + require('./img/bbb.jpg') + ')',

        backgroundRepeat:'no-repeat',

        backgroundSize:'100% 100%'

      }
    }
  },
  components: {
    'navmenu': Nav
  },


  mounted(){

  }
}
</script>

<style>
  .header {

    color: #fff;
    line-height: 60px;
  }
  html{
    background: black;
  }
  .backg{
    background: rgba(204,204,204,0)
  }
  .el-tree{
    background:none;
    color: white;
  }
  .el-tree-node__content:hover{
    background: #ffffff !important;
    color: black;
  }
  .el-tree-node:focus > .el-tree-node__content{
    background: #ccc !important;
    color: black;
  }
  #particles-js {
    width: 100%;
    height: calc(100% - 100px);
    position: absolute;
    z-index: 0;
  }
</style>
