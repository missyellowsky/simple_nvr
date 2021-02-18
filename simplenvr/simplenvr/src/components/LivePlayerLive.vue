<template>
  <div style="display: flex;flex-wrap: nowrap;">
    <div style="width: 25%;margin-right: 1rem">
      <el-tree :data="cameras" :props="defaultProps" ref="tree" :check-strictly="true" show-checkbox @check-change="checkCameraNode" node-key="ip" check-on-click-node></el-tree>
    </div>
    <div style="display: flex;flex-wrap: wrap;">
      <template v-for="item in videoBoxList">
        <div
          :id="item.id"
          class="video"
          v-bind:style="item.id == index?{border:choose }:{border:notChoose}"
          @click="groupClick(item.id)"
        >
        </div>
      </template>
    </div>
  </div>
</template>

<script>
  import 'xgplayer';
  import FlvPlayer from 'xgplayer-flv';
  import HlsPlayer from 'xgplayer-hls.js';
import http from '../request/http'
import api from '../request/api'
export default {
    name: 'LivePlayerLive',
    components: {
    },
  data(){
      return {
        img: require('../img/xingkong.jpg'),
        cameras:[],
        defaultProps:{
          children: "children",
          label: "label",
          disabled: 'isNotLeaf',
          isLeaf:"isLeaf"
        },
        index:"video1",
        videoBoxList:[{
          id:"video1"
        },{
          id:"video2"
        },{
          id:"video3"
        },{
          id:"video4"
        }],
        choose:"1px solid gold",
        notChoose:"1px solid black",
        url1:"",
        url2:"",
        url3:"",
        url4:"",
        choosedCamera:{},
        player1:null,
        player2:null,
        player3:null,
        player4:null,
        players:{
          "video1": this.player1,
          "video2": this.player2,
          "video3": this.player3,
          "video4": this.player4,
        },
        streamType:"flv-http"
      }
  },
  methods: {
        //销毁播放器并停止已开始的拉流请求
        destroy: function () {
          for (let key in this.players){
            if(this.players[key]){
              this.players[key].pause();
              this.players[key].destroy(true);
              this.players[key] = null;
            }
          }
        },
       //摄像头树勾选事件
        checkCameraNode:function(a, b, c){
          if(!a.isLeaf){
            return
          }
          if(b){
            //选中
            let params = {
              "ip":a.ip,
              "streamType": this.streamType
            }
            let that = this;
            http.post(api.getLiveUrl, params).then(res => {
              if(res.data != null && res.data !=undefined && res.data.code == "1"){
                let url = res.data.obj;
                if(!that.players[that.index]){
                  if(that.streamType == 'flv-http'){
                    that.players[that.index] = new FlvPlayer({
                      id: that.index,
                      url: url,
                      isLive: true,
                      preloadTime: 1,
                      minCachedTime: 1,
                      cors: true,
                      fluid: false,
                      videoInit: true,
                      screenShot: true,
                      closeVideoClick:true,
                      closeVideoPreventDefault: true,
                      closeVideoStopPropagation: true,
                      width: 700,
                      height: 400,
                    })
                  }else if(that.streamType == 'hls'){
                    that.players[that.index] = new HlsPlayer({
                      id: that.index,
                      url: url,
                      isLive: true,
                      fluid: false,
                      preloadTime: 15,
                      minCachedTime: 8,
                      videoInit: false,
                      screenShot: true,
                      closeVideoClick:true,
                      closeVideoPreventDefault: true,
                      closeVideoStopPropagation: true,
                      width: 700,
                      height: 400,
                      autoplay:false,
                      cors:true,
                      poster:that.img
                    })
                  }

                }else{
                  for (let key in that.choosedCamera){
                    if(that.choosedCamera[key] == that.index){
                      let shouldCloseIp = key;
                      that.$refs.tree.setChecked(shouldCloseIp,false);
                      break;
                    }
                  }
                  that.players[that.index].src = url
                }
                that.choosedCamera[a.ip] = that.index;
              }else {
                this.$message.error("该摄像头无法播放,请检查连接: " + res.data.msg);
                that.$refs.tree.setChecked(a.ip,false);
              }
            })

          }else{
            //去除勾选
            let key = this.choosedCamera[a.ip];
            if(key){
              this.players[key].pause();
              this.players[key].destroy(true);
              this.players[key] = null;
            }
          }


          let ip = a.ip
          debugger
        },
        //播放器组选择事件
        groupClick:function (e) {
          debugger
          this.index = e;
        },
        initCameraTree:function () {
          //初始化摄像头
          let params = {
          }
          http.get(api.getCameras, params).then(res =>{
            if(res.data != null && res.data !=undefined && res.data.code == "1"){
              let data = res.data.obj;
              let camerasTree = [{
                label: "onvif",
                children: [],
                isLeaf: false
              }];
              for (let i = 0 ; i<data.length ; i++){
                data[i].label = data[i].ip;
                data[i].isLeaf = true;
                camerasTree[0].children.push(data[i])
              }
              this.cameras = camerasTree;
            }
          })
        }
    },
    mounted () {
      //初始化摄像头
      this.initCameraTree();
    },

  beforeRouteLeave(to, form, next) {
    next()
  },
  destroyed () {
    this.destroy();
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->

<style lang="less" scoped>
  /deep/ .el-tree-node{
    .is-leaf + .el-checkbox .el-checkbox__inner{
      display: inline-block;
    }
    .el-checkbox .el-checkbox__inner{
      display: none;
    }
  }
  .video{
    margin: 4px 7px;
    width: 700px;
    height: 400px;
    background-color: rgba(27,45,23,0.5);
    z-index: 2;
  }
</style>
