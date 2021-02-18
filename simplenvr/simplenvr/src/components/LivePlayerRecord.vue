<template>
  <div style="display: flex;flex-wrap: nowrap;z-index: 0">
    <div style="width: 10%;margin-right: 1rem">
      <el-tree :data="cameras" :props="defaultProps" ref="tree" :check-strictly="true" show-checkbox  @check="checkGroupNode" node-key="ip" check-on-click-node></el-tree>
    </div>
    <div style="width: 15%;margin-right: 1rem">
      <el-tree :data="videoFiles" :props="defaultProps" ref="video" node-key="id" @node-click="playRecord"></el-tree>
    </div>
    <div style="display: flex;flex-wrap: wrap;">
      <div
        id="recordPlay"
        class="video"
      >
      </div>
    </div>
  </div>
</template>

<script>
 import Player from 'xgplayer';
 import FlvPlayer from 'xgplayer-flv';
 import FlvPlayerJS from 'xgplayer-flv.js';
 import HlsPlayer from 'xgplayer-hls.js';
 import http from '../request/http'
 import api from '../request/api'
  export default {
    name: 'LivePlayerRecord',
    data(){
      return{
        recordUrl:null,
        streamType:"flv-http",
        cameras:[],
        videoFiles:[],
        defaultProps:{
          children: "children",
          label: "label",
          disabled: 'isNotLeaf',
          isLeaf:"isLeaf"
        },
        player:null,
        fileLenth:3600
      }
    },
    components: {
    },
    props: {
        msg: String
    },
    methods: {

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
      },
      initVideoFiles:function(ip, date){
        let params = {
        }
        let that = this;
        http.get(api.queryRecordList+"?ip="+ip+"&date="+date, params).then(res =>{
          if(res.data != null && res.data !=undefined && res.data.code == "1"){
            let data = res.data.obj;
            let filesTree = [];
            for (let key in data){
              let leafs = [];
              for (let file in data[key]){
               let leaf = {
                 label:data[key][file],
                 isLeaf:true,
                 id:data[key][file]
               }
                leafs.push(leaf);
              }
              filesTree.push({
                label: key,
                children:leafs,
                isLeaf:false
              })

            }
            that.videoFiles = filesTree

          }
        })
      },
      checkGroupNode: function (a, b) {
        if (b.checkedKeys.length > 0) {
          this.$refs.tree.setCheckedKeys([a.ip]);
        }
        if(!a.isLeaf){
          return
        }
        this.initVideoFiles(a.ip,"1612429202")
      },
      playRecord:function (a, b,c) {
        debugger
        if(!a.isLeaf){
          return;
        }
        let ips = this.$refs.tree.getCheckedKeys(true);
        if(!ips || !ips.length>0){
          return
        }
        let ip = ips[0];
        let date = a.id;
        date = date.substring(0,19);
        date = date.replace(/-/g,'/');
        var timestamp = new Date(date).getTime();
        let endTime = (timestamp + this.fileLenth*1000)/1000
        debugger
        this.destroy()
        this.getRecordUrl(ip,timestamp,endTime)

      },
      getRecordUrl:function (ip, startTime, endTime) {
        let params = {
          ip:ip,
          streamType:this.streamType,
          startTime:startTime,
          endTime:endTime
        }
        let that = this;
        http.post(api.getRecordUrl, params).then(res => {
          if(res.data != null && res.data !=undefined && res.data.code == "1"){
            let url = res.data.obj;
            if(that.streamType == 'flv-http'){
              that.player = new FlvPlayer({
                id: "recordPlay",
                url: url,
                isLive: true,
                preloadTime: 90000,
                minCachedTime: 600,
                cors: false,
                fluid: false,
                videoInit: true,
                screenShot: true,
                closeVideoClick:true,
                closeVideoPreventDefault: true,
                closeVideoStopPropagation: true,
                width: 700,
                height: 400,
                autoplay:true
              })
            }else if(that.streamType == 'hls'){
              that.player = new HlsPlayer({
                id: "recordPlay",
                url: url,
                isLive: true,
                fluid: false,
                preloadTime: 90000,
                minCachedTime: 600,
                videoInit: false,
                width: 700,
                height: 400,
                cors:true,
                poster:that.img,
                autoplay:true
              })
            }else{
              that.player = new FlvPlayerJS({
                id: "recordPlay",
                url: 'http://192.168.80.11:8011/record/10.88.40.250-1612432838.flv',
                flvOptionalConfig: {
                  enableWorker: true,
                  isLive: false
                }
              })
            }
          }
        })
      },
      //销毁播放器并停止已开始的拉流请求
      destroy: function () {
          if(this.player){
            this.player.pause();
            this.player.destroy(true);
            this.player = null;
          }
      },
    },
    mounted () {
      this.initCameraTree();
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
</style>
